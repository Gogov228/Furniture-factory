package MoneyFurniture;

import MaterialWood.Material;

import java.util.HashMap;

public class Furniture {
    protected int price;
    protected String name;
    protected HashMap<String,Integer> material = new HashMap<>();

    public String getName(){
        return this.name;
    }
    public int getPrice(){
        return this.price;
    }
    public HashMap<String,Integer> getMaterial(){
        return this.material;
    }

}
