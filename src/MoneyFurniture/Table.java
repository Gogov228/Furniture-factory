package MoneyFurniture;

public class Table extends Furniture {
    public Table(){
        this.name="Стол";
        this.price=4500;

        material.put("Брус",4);
        material.put("Ножка",4);
        material.put("Доска",2);
        material.put("ДСП",2);
    }
}

