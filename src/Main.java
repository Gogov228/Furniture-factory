import MaterialWood.Board;
import MaterialWood.DSP;
import MaterialWood.Timber;
import MaterialWood.WoodLeg;
import MoneyFurniture.*;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
    MakeRoom makeRoom = new MakeRoom(Arrays.asList(
            new cupboard(),
            new Table(),
            new Nightstand(),
            new VeryGood_Stool(),
            new Stool()
    ));

    PreparingRoom preparingRoom =new PreparingRoom(Arrays.asList(
            new DSP(),
            new Board(),
            new Timber(),
            new WoodLeg()
    ));

    Client client=new Client(18000);



    preparingRoom.setWood(4411);

        HashMap<String,Integer> order=new HashMap<>();
        order.put("Тумбочка",2);
        order.put("Шкаф",1);
        order.put("Стул",5);

        client.requestOrder(order,preparingRoom,makeRoom);




    }
}
