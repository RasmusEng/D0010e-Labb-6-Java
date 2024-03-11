package EventPackage;

import CustomerPackage.Customer;
import CustomerPackage.CustomerFactory;
import StatePackage.State;
import StatePackage.StoreState;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * StartSimEvent of the Sim
 *
 * */

public class StartSimEvent extends Event {
    @Override
    public void executeMe(State state, EventQueue queue) {
        StoreState storeState = (StoreState) state; // typecast
        storeState.setStoreOpen();

        Event closeEvent = new StoreCloseEvent(); //skapar event for att affaren skall stanga
        closeEvent.setExecuteTime(storeState.END_TIME);
        queue.addEventToQueue(closeEvent);

        Customer first = CustomerFactory.createCustomer(storeState); //skapar en ny kund
        first.setCurrentEvent(new CustomerArrivalEvent(storeState.getLambda())); //ger den nya kunden ett arrivalevent
        queue.addEventToQueue(first.getCurrentEvent()); //lagger till eventet i kon... sa att det sker.

        Event stopEvent = new StopSimEvent(); //Skapar stopp for simuleringeen
        stopEvent.setExecuteTime(storeState.STOP_TIME);
        queue.addEventToQueue(stopEvent);

        storeState.setLastEvent(this);
        storeState.notifyOB();

    }

    @Override
    public String toString() {
        return "Start";
    }

}
