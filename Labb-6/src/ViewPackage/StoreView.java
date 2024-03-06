package ViewPackage;

import StatePackage.State;
import StatePackage.StoreState;

import java.util.Observable;

public class StoreView extends View{

    private int huvud = 0;
    private void printHead(StoreState state)
    {
        System.out.println("PARAMETRAR");
        System.out.println("==========");
        System.out.println("Antal kassor............: " + state.getAmountOfRegisters());
        System.out.println("Vad som ryms............: " + state.M);
        System.out.println("Ankomsthastighet........: " + state.L);
        System.out.println("Plocktider, [Pmin..Pmax]: " + "[" + state.LOW_COLLECTION_TIME + ".." +state.HIGH_COLLECTION_TIME+"]");
        System.out.println("Betaltider, [Kmin..Kmax]: " + "[" + state.LOW_PAYMENT_TIME + ".." +state.HIGH_PAYMENT_TIME+"]");
        System.out.println("Frö.....................: " + state.SEED);
        System.out.println("FÖRLOPP");
        System.out.println("=======");
        System.out.println("Tid\t<Händelse\t?\tKund\t?\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");
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
