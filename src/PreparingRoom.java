import MaterialWood.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


//Заготовочный цех
public class PreparingRoom {
    private float wood;
    private List<Material> material = new ArrayList<>();
    private HashMap<String,Integer> requestBad= new HashMap<>();

    public PreparingRoom(List<Material> material){
        this.material=material;
    }//зарашивает все существующие материалы
    AtomicInteger total = new AtomicInteger(0);
    //пробегаемся по заказу и считаем количество предметов и древесины для изготовления заказа
    public HashMap<String,Integer> doMaterial(HashMap<String, Integer> request){
        request.forEach((name,count)->{
            Material material_1 = material
                    .stream()
                    .filter(it-> it.getName().equals(name))
                    .findFirst()
                    .orElse(null);
            if(material_1 == null) {
                System.out.println("Нет такого материала!" + name);
                return;
            }
            //requestBad.put(name,count);
            total.set((total.get()+count*material_1.getWood()));
        });


        System.out.println("Для изготовления нужно "+total+" дерева");
        if(total.get()> wood) {
            System.out.println("Дерева для изготовления данной мебели не хватает");
            requestBad.put("Ничего",0);
            total.set(0);
            return requestBad;
        }
        else


        System.out.println("Дерева хватает");
        //requestBad.put(name,count);
        wood-=total.get();
        total.set(0);
    return request;
    }

    public HashMap<String, Integer> getRequestBad() {
        return requestBad;
    }

    public void setWood(float wood) {
        this.wood = wood;
    }
}
