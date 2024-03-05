package ViewPackage;

import EventPackage.Event;
import StatePackage.State;
import StatePackage.StoreState;
import StatePackage.ShoppingTimeGenerator;
import java.util.Observable;

public class StoreView extends View{

    private int huvud = 0;
    private void printHead(StoreState state)
    {
        System.out.println("PARAMETRAR");
        System.out.println("==========");
        System.out.println("Antal kassor............: " + state.getAmountOfRegisters());
        System.out.println("Vad som ryms............: " + state.getMaxCustomers());
        System.out.println("Ankomsthastighet........: " + state.getLambda());
        System.out.println("Plocktider, [Pmin..Pmax]: " + "[" + state.getShoppingTimeGenerator().getpMin() + ".." +state.getShoppingTimeGenerator().getpMax()+"]");
        System.out.println("Betaltider, [Kmin..Kmax]: " + "[" + state.getShoppingTimeGenerator().getkMin() + ".." +state.getShoppingTimeGenerator().getkMax()+"]");
        System.out.println("Frö.....................: " + "variabel\n");
        System.out.println("FÖRLOPP");
        System.out.println("=======");
        System.out.println("Tid\tHändelse\t?\tKund\t?\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");
    }

    @Override
    public void update(Observable o, Object arg) {
        StoreState storeState = ((StoreState) o);
        State state = ((State) o);
        if(huvud == 0){
            printHead(storeState);
            huvud++;
        }else{
            System.out.println(state.getTime() + "\t" + storeState.getLastEvent().toString() + "\t" + storeState.isOpen() +"\t" + storeState.getTotalTimeRegEmpty() +"\t" + storeState.getCustomersInStore());
        }
    }

}
