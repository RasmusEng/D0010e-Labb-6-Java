package ViewPackage;

import StatePackage.State;
import StatePackage.StoreState;


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
    private boolean results = true;
    private DecimalFormat f = new DecimalFormat("0.00");


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
        // vänstershiftar input1
        String formattedInput1 = input1.substring(0, Math.min(input1.length(), length));

        // bstämmer hur många blanksteg som krävs för att högershifta input2
        int spaces = length - formattedInput1.length() - input2.length();
        StringBuilder stringBuilder = new StringBuilder();

        // använder stringbuilder för att bygga en sträng med rätt längd inpu1 till vä och input 2 till hö
        stringBuilder.append(formattedInput1);
        for (int i = 0; i < spaces; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append(input2);

        return stringBuilder.toString();
    }

    // skapar en sträng för alla parametrar i sumulationen
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
        String customersThathaveQueued = formatString(missedCustomers, Integer.toString(storeState.getCustomersAmountThatQueue()), 52);
        String totalQueueTime = formatString(customersThathaveQueued, f.format(storeState.getTotalTimeInQueue()), 60);
        String peopleInQueue = formatString(totalQueueTime, Integer.toString(storeState.getCheckoutQueue().getLastAmountPeopleInQueue()), 65);
        String peopleIdInQueue = peopleInQueue.concat("\t" + storeState.getCheckoutQueue().getLastQueue());
        return peopleIdInQueue;
    }

    private void printResults(StoreState storeState){
        System.out.println("RESULTAT \n ============= \n \n 1) Av " + (storeState.getCheckedOutCustomers() + storeState.getLostCustomers()) + " Kunder handlade " + storeState.getCheckedOutCustomers() + " Medans " + storeState.getLostCustomers() + " missades.");
        System.out.println("2) Total tid " + storeState.getAmountOfRegisters() + " har varit lediga: " + f.format(storeState.getTotalTimeRegEmpty()) + " te.");
        System.out.println("Genomsnittlig ledig kassatid: " + f.format(storeState.getTotalTimeRegEmpty() / storeState.getAmountOfRegisters()));
        System.out.print("(dvs " + f.format(((( storeState.getTotalTimeRegEmpty() / storeState.getAmountOfRegisters() ) / storeState.getLastCheckoutTime()))*100) + "% av tiden öppen till sista kunden betalt");
        System.out.println("3) Total tid " + storeState.getCustomersAmountThatQueue() + " kunder tvingats köa: " +  f.format(storeState.getTotalTimeInQueue()) + " te.");
        System.out.println("  Genomsnittlig kötid: " + f.format(storeState.getTotalTimeInQueue()/storeState.getCustomersAmountThatQueue()) + " te.");
    }


    private String writeLine(State state, StoreState storeState){
        switch (storeState.getLastEvent().toString()){
            case "Start":
                return formatString(f.format(storeState.getTime()) , formatString(storeState.getLastEvent().toString(), "", 13), 20);
            case "Stop":
                results = false;
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
     * and prints out the results of the simulation.
     * */

    @Override
    public void update(Observable o, Object arg) {
        StoreState storeState = ((StoreState) o);
        State state = ((State) o);
        if(huvud == 0){
            printHead(storeState);
            huvud++;
        } else {
            System.out.println(writeLine(state, storeState));
            if (storeState.getStop()){
                printResults(storeState);
            }
        }



    }

}
