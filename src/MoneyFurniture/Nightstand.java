package MoneyFurniture;

public class Nightstand extends Furniture{
    public Nightstand(){
        this.name="Тумбочка";
        this.price=2000;

        material.put("ДСП",1);
        material.put("Доска",2);
        material.put("Ножка",4);
    }
}
