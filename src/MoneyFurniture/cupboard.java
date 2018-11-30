package MoneyFurniture;

public class cupboard extends Furniture{
    public cupboard(){
        this.name="Шкаф";
        this.price=5000;

        material.put("ДСП",2);
        material.put("Ножка",4);
        material.put("Доска",6);
        material.put("Брус",2);
    }
}
