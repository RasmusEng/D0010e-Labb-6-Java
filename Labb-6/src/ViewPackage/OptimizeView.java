package ViewPackage;

import StatePackage.StoreState;

import java.util.Observable;
@SuppressWarnings("NonAsciiCharacters")
public class OptimizeView extends View{

    private void printHead(StoreState state)
    {
        System.out.println("PARAMETRAR");
        System.out.println("==========");
        System.out.println("Antal kassor............: " + state.getAmountOfRegisters());
        System.out.println("Vad som ryms............: " + state.M);
        System.out.println("Ankomsthastighet........: " + state.L);
        System.out.println("Plocktider, [Pmin..Pmax]: " + "[" + state.LOW_COLLECTION_TIME + ".." +state.HIGH_COLLECTION_TIME+"]");
        System.out.println("Betaltider, [Kmin..Kmax]: " + "[" + state.LOW_PAYMENT_TIME + ".." +state.HIGH_PAYMENT_TIME+"]");
        System.out.println("Fro.....................: " + state.getSeed());
        System.out.println("=======");
    }

    private void whatToPrint(StoreState o){
        printHead((StoreState) o);
        System.out.println("Stangning sker tiden " + ((StoreState) o).END_TIME);
        System.out.print(" och stophandelsen sker " + ((StoreState) o).STOP_TIME);
        System.out.print(" minsta antal kassor som ger minimalt antal missade " + ((StoreState) o).getLostCustomers());
        System.out.print(":" + ((StoreState) o).getAmountOfRegisters());
    }
    
    @Override
    public void update(Observable o, Object arg) {
        StoreState store = (StoreState) o;
        if (((StoreState) o).getStop()){
            whatToPrint(store);
        }
    }
}
