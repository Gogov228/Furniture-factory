import java.util.HashMap;

public class Client {

    private int money;
    public Client(int money){
        this.money=money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void requestOrder(HashMap<String,Integer> request, PreparingRoom preparingRoom, MakeRoom makeRoom){
        makeRoom.doFurniture(this,request, preparingRoom);
    }
}
