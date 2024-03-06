package ViewPackage;

import StatePackage.State;
import StatePackage.StoreState;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.text.DecimalFormat;
import java.util.Observable;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * <br>
 * StoreView of the Sim
 * <br>
 * The StoreView class implements the Observer interface
 * and observes the StoreState
 *
 * */

public class StoreView extends View{

    private int huvud = 0;
    private DecimalFormat f = new DecimalFormat("0.00");

    /**
     * A method that prints the parameters of the StoreSim
     *
     * @param state Takes a StoreState so that the variables are accessable
     *
     * */

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
        System.out.println(headWriting());
    }
    private String formatString(String input1, String input2, int length) {
        // Left shift input1 to fit within 15 characters
        String formattedInput1 = input1.substring(0, Math.min(input1.length(), length));

        // Determine the number of spaces needed for input2
        int spaces = length - formattedInput1.length() - input2.length();
        StringBuilder stringBuilder = new StringBuilder();

        // Append input1, spaces, and input2 to the StringBuilder
        stringBuilder.append(formattedInput1);
        for (int i = 0; i < spaces; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append(input2);

        // Return the formatted string
        return stringBuilder.toString();
    }

    private String headWriting(){
        String händelse = formatString("Händelse", "Kund", 13);
        String tid = formatString("Tid", händelse,20);
        String question = formatString(tid, "?",25);
        String ledigakassor = formatString(question, "led", 30);
        String ledT = formatString(ledigakassor, "ledT", 35);
        String inneiaffären = formatString(ledT, "I", 40);
        String betaladekunder = formatString(inneiaffären, "$", 45 );
        String missadekunder = formatString(betaladekunder, ":-(", 50);
        String köadekunder = formatString(missadekunder, "köat", 55);
        String totalkötid = formatString(köadekunder, "köT", 60);
        String köar = formatString(totalkötid, "köar", 65);
        String kassakö = formatString(köar, "[Kassakö]", 70);
        return kassakö;

    }

    private String moreWriting(String input1, StoreState storeState){
        String open = formatString(input1, Boolean.toString(storeState.isOpen()), 25);
        String openRegisters = formatString(open,  Integer.toString(storeState.getEmptyRegisters()), 30);
        String timeFreeRegisters = formatString(openRegisters, f.format(storeState.getTotalTimeRegEmpty()), 35);
        String customersInStore = formatString(timeFreeRegisters, Integer.toString(storeState.getCustomersInStore()), 40);
        String checkedOutCustomers = formatString(customersInStore, Integer.toString(storeState.getCheckedOutCustomers()), 45);
        String missedCustomers = formatString(checkedOutCustomers, Integer.toString(storeState.getLostCustomers()), 50);
        String customersThathaveQueued = formatString(missedCustomers, Integer.toString(storeState.getCustomersAmountThatQueue()), 55);
        String totalQueueTime = formatString(customersThathaveQueued, f.format(storeState.getTotalTimeInQueue()), 60);
        String peopleInQueue = formatString(totalQueueTime, Integer.toString(storeState.getCheckoutQueue().customerQueueSize()), 65);
        String peopleIdInQueue = peopleInQueue.concat("\t" + storeState.getCheckoutQueue().idInQueue());
        return peopleIdInQueue;
    }


    private String writeLine(State state, StoreState storeState){
        switch (storeState.getLastEvent().toString()){
            case "Start":
            case "Stop":
                return formatString(f.format(storeState.getTime()) , formatString(storeState.getLastEvent().toString(), "", 13), 20);
            case "Close":
                String closeString = formatString(f.format(storeState.getTime()), formatString(storeState.getLastEvent().toString(), "---", 13), 20);
                return moreWriting(closeString, storeState);
            default:
                String defaultString = formatString(f.format(storeState.getTime()),formatString(storeState.getLastEvent().toString(), Integer.toString(storeState.getLastCustomerID()), 13),20);
                return moreWriting(defaultString, storeState);
        }
    }

    /**
     * A method that overrides its parent method
     * and runs the different print methods of the
     * StoreView class
     * */

    @Override
    public void update(Observable o, Object arg) {
        StoreState storeState = ((StoreState) o);
        State state = ((State) o);
        if(huvud == 0){
            printHead(storeState);
            huvud++;
        }else{
            //System.out.println(f.format(state.getTime()) + "\t" + storeState.getLastEventString() + "\t" + storeState.isOpen() +"\t" + f.format(storeState.getTotalTimeRegEmpty()) +"\t" + storeState.getCustomersInStore() + "\t" + storeState.getCustomersAmountThatQueue() + "\t");
            System.out.println(writeLine(state, storeState));
        }

    }

}
