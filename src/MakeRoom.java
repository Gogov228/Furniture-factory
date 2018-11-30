import MoneyFurniture.Furniture;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MakeRoom {

    private List<Furniture> furniture = new ArrayList<>();
    HashMap<String,Integer> totalMaterial = new HashMap<>();
    HashMap<String,Integer> finishMaterial = new HashMap<>();
    HashMap<String,Integer> help = new HashMap<>();


    public MakeRoom(List<Furniture> furniture){
        this.furniture=furniture;
    }//Запрашивает какая мебель существует для изготовления

    public void doFurniture(Client client, HashMap<String, Integer> request, PreparingRoom preparing){

            AtomicInteger total = new AtomicInteger(0);//сумма всего заказа
            request.forEach((name, count) -> {
                Furniture furneture_1 = furniture
                        .stream()
                        .filter(it -> it.getName().equals(name))
                        .findFirst()
                        .orElse(null);
                if (furneture_1 == null) {
                    System.out.println("Данной мебели нет " + name);
                    return;
                }


                //Создаём общее количество материалов----------------------------------------------------------

                HashMap<String, Integer> material = new HashMap<>();
                material = furneture_1.getMaterial();

                for (HashMap.Entry<String, Integer> elem : material.entrySet()) {
                    help.put(name, count);
                    if (totalMaterial.containsKey(elem.getKey())) {
                        int value = totalMaterial.get(elem.getKey());
                        totalMaterial.put(elem.getKey(), value + elem.getValue() * count);
                    } else
                        totalMaterial.put(elem.getKey(), elem.getValue());
                }

                //----------------------------------------------------------------------------------------------
                total.set((total.get() + count * furneture_1.getPrice()));

            });
        System.out.println("Сумма всего заказа: "+total);
            if (client.getMoney() < total.get()) {
                System.out.println("У вас нет денег");
                return ;
            }


            System.out.println(totalMaterial);
            finishMaterial = preparing.doMaterial(totalMaterial);
            if (finishMaterial == totalMaterial) {
                System.out.println("Материалы которые нужны для производства: " + totalMaterial);
                System.out.println("Такая мебель была выдана: " + help);
                System.out.println("С вашего счёта списано " + total);
                client.setMoney(-total.get());
            } else {
                totalMaterial.clear();
                finishMaterial.clear();
                request.forEach((name, count) -> {
                    Furniture furneture_1 = furniture
                            .stream()
                            .filter(it -> it.getName().equals(name))
                            .findFirst()
                            .orElse(null);
                    HashMap<String, Integer> material = new HashMap<>();
                    material = furneture_1.getMaterial();

                    for (HashMap.Entry<String, Integer> elem : material.entrySet()) {
                        if (finishMaterial.containsKey("Ничего"))
                            break;
                        else {
                            help.put(name, count);
                            if (totalMaterial.containsKey(elem.getKey())) {
                                int value = totalMaterial.get(elem.getKey());
                                totalMaterial.put(elem.getKey(), value + elem.getValue() * count);

                            } else {
                                totalMaterial.put(elem.getKey(), elem.getValue());
                            }
                        }
                        finishMaterial = preparing.doMaterial(totalMaterial);
                    }
                });
                total.set(0);

                request.forEach((name, count) -> {
                    Furniture furneture_1 = furniture
                            .stream()
                            .filter(it -> it.getName().equals(name))
                            .findFirst()
                            .orElse(null);
                    for (Map.Entry<String, Integer> elem : help.entrySet()) {
                        if (elem.getKey() == furneture_1.getName())
                            total.set((total.get() + count * furneture_1.getPrice()));
                    }
                });

                System.out.println("Материалы которые были произведены: " + totalMaterial);
                System.out.println("Такая мебель была выдана: " + help);
                System.out.println("С вашего счёта списано " + total);
                client.setMoney(-total.get());

            }
        }
}
