package EventPackage;

import CustomerPackage.Customer;
import CustomerPackage.CustomerFactory;
import StatePackage.State;
import StatePackage.StoreState;

public class StartSimEvent extends Event {
    @Override
    public void executeMe(State state, EventQueue queue) {
        StoreState storeState = (StoreState) state; // typecast
        Event stopEvent = new StopSimEvent(); //Skapar stopp för simuleringeen
        stopEvent.setExecuteTime(999);
        queue.addEventToQueue(stopEvent);

        Event closeEvent = new StoreCloseEvent(); //skapar event för att affären skall stänga
        closeEvent.setExecuteTime(storeState.getCloseTime());
        queue.addEventToQueue(closeEvent);

        Customer first = CustomerFactory.createCustomer(storeState); //skapar en ny kund
        first.setCurrentEvent(new CustomerArrivalEvent(0)); //ger den nya kunden ett arrivalevent
        queue.addEventToQueue(first.getCurrentEvent()); //lägger till eventet i kön... så att det sker.

        storeState.setLastEvent(this);
        storeState.notifyOB();

    }

}
