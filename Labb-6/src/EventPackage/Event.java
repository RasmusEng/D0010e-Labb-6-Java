package EventPackage;

import CheckoutPackage.CheckoutQueue;
import CustomerPackage.Customer;
import CustomerPackage.CustomerFactory;
import StatePackage.*;
import ViewPackage.StoreView;


/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * <br>
 * Event of the Sim
 * <br>
 * Note: Is abstract because of the abstract method executeMe()
 *
 * */
public abstract class Event {
    private double executeTime;

    /**
     * The abstract method executeMe that executes the different Events
     *
     * @param state The state that the method should update
     * @param queue The EventQueue that the Event will be put into
     *
     * */

    public abstract void executeMe(State state, EventQueue queue);

    /**
     * A getter method that gets the time it takes for the
     * Event to execute itself
     *
     * @return The execute time of the Event
     *
     * */

    public double getExecuteTime(){
        return executeTime;
    }

    /**
     * A setter method that gets the time it takes for the
     * Event to execute itself
     *
     * @param executeTime The execute time the Event should be set to
     *
     * */

    public void setExecuteTime(double executeTime){
        this.executeTime = executeTime;
    }

    /**
     * An abstract method that should return a String specific to the Event
     * */

    public abstract String toString();
}

/**
 * The StopSimEvent class that extends Event
 * */

class StopSimEvent extends Event{ //TODO "KLAR"


    @Override
    public void executeMe(State state, EventQueue queue) {
        StoreState storeState = (StoreState) state;
        storeState.setStop(); //bör göra rätt grej
        storeState.setLastEvent(this);
        storeState.notifyOB();
    }

    @Override
    public String toString() {
        return "stop";
    }
}


/**
 * The CustomerArrivalEvent class that extends Event
 * */

class CustomerArrivalEvent extends Event{


    /**
     * The constructor of CustomerArrivalEvent that sets the
     * executeTime of the Event
     *
     * @param exeTime The execute time to be set
     *
     * */
    CustomerArrivalEvent(double exeTime){
        this.setExecuteTime(exeTime);
    }

    @Override
    public void executeMe(State state, EventQueue queue) {
        StoreState storeState = (StoreState) state;
        storeState.addTime(this.getExecuteTime());
        updateTimeRegisterEmpty(storeState);
        if (storeState.storeFull()){
            storeState.addLostCustomer();
        } else {
            storeState.addCustomerInStore();
            storeState.getCustomerWithEvent(this).setCurrentEvent(new CustomerPickedEvent(storeState.getTime() + storeState.getShoppingTimeGenerator().getPickupTime()));
        }

        Customer cust = CustomerFactory.createCustomer(storeState);
        Event newArrival = new CustomerArrivalEvent(storeState.getTime() + storeState.getLambda());
        cust.setCurrentEvent(newArrival);
        queue.addEventToQueue(newArrival);

        storeState.setLastEvent(this);
        storeState.notifyOB();

    }

    @Override
    public String toString() {
        return "Arrival";
    }


    /**
     * Förklara gärna denna
     *
     * */

    private void updateTimeRegisterEmpty(StoreState storeState){
        if (storeState.isRegEmpty()){
            double timeEmpty = storeState.getTime() - storeState.getLastEvent().getExecuteTime();
            storeState.addTotalTimeRegEmpty(timeEmpty);
        }
    }
}


/**
 * The CustomerPickedEvent class that extends Event and
 * makes the Customers pick "items" in the store
 * <br>
 * OBS: The "items" don't actually exist
 * */

class CustomerPickedEvent extends Event{

    /**
     * The constructor of CustomerPickedEvent that sets the
     * executeTime of the Event
     *
     * @param exeTime The execute time to be set
     *
     * */
    CustomerPickedEvent(double exeTime){
        this.setExecuteTime(exeTime);
    }
    @Override
    public void executeMe(State state, EventQueue queue) {
        StoreState storeState = (StoreState) state;
        storeState.addTime(this.getExecuteTime());
        updateTimeRegisterEmpty(storeState);

        if (storeState.isRegEmpty()){
            storeState.addOpenReg();
            Event checkout = new CustomerCheckoutEvent(this.getExecuteTime() + storeState.getShoppingTimeGenerator().getCheckoutTime());
            storeState.getCustomerWithEvent(this).setCurrentEvent(checkout);
            queue.addEventToQueue(checkout);
        } else {

            storeState.addCustomerToQueue(storeState.getCustomerWithEvent(this));
            storeState.getCustomerWithEvent(this).setTimeGetInLine(storeState.getTime());
            storeState.getCustomerWithEvent(this).setCurrentEvent(null);

        }
        storeState.setLastEvent(this);
        storeState.notifyOB();

    }

    @Override
    public String toString() {
        return "Picked";
    }

    /**
     * Förklara gärna denna
     *
     * */

    private void updateTimeRegisterEmpty(StoreState storeState){
        if (storeState.isRegEmpty()){
            double timeEmpty = storeState.getTime() - storeState.getLastEvent().getExecuteTime();
            storeState.addTotalTimeRegEmpty(timeEmpty);
        }
    }
}


/**
 * The CustomerCheckoutEvent class that extends Event and makes
 * the Customers check out
 * */

class CustomerCheckoutEvent extends Event{

    /**
     * The constructor of CustomerCheckoutEvent that sets the
     * executeTime of the Event
     *
     * @param exeTime The execute time to be set
     *
     * */
    public CustomerCheckoutEvent(double exeTime) {
        this.setExecuteTime(exeTime);
    }

    @Override
    public void executeMe(State state, EventQueue queue) {
        StoreState storeState = (StoreState) state;
        storeState.addTime(this.getExecuteTime());
        updateTimeRegisterEmpty(storeState);

        if (storeState.getCheckOutQueue().hasNextCustomer()){
            double nexttime = storeState.getTime() + storeState.getShoppingTimeGenerator().getCheckoutTime();
            Event newCheckout = new CustomerCheckoutEvent(nexttime);
            Customer nextCustomerToCheckout = storeState.getCheckOutQueue().getNextCustomer();
            nextCustomerToCheckout.setTimeGetOutOfLine(storeState.getTime());
            storeState.addTotalTimeInQueue(nextCustomerToCheckout.getTimeSpentInQueue());
            nextCustomerToCheckout.setCurrentEvent(newCheckout);
            queue.addEventToQueue(newCheckout);
        } else {
            storeState.removeOpenReg();
        }

        storeState.addCheckCustByOne();
        storeState.removeCustomerInStore();

        storeState.setLastEvent(this);
        storeState.notifyOB();


    }

    @Override
    public String toString() {
        return "Checkout";
    }

    /**
     * Förklara gärna denna
     *
     * */

    private void updateTimeRegisterEmpty(StoreState storeState){
        if (storeState.isRegEmpty()){
            double timeEmpty = storeState.getTime() - storeState.getLastEvent().getExecuteTime();
            storeState.addTotalTimeRegEmpty(timeEmpty);
        }
    }
}



/**
 * The StoreCloseEvent class that extends Event
 * */

class StoreCloseEvent extends Event{ //TODO: "KLAR"
    @Override
    public void executeMe(State state, EventQueue queue){
        StoreState storeState = (StoreState) state;
        storeState.addTime(this.getExecuteTime());
        storeState.setStoreClosed();
        updateTimeRegisterEmpty(storeState);

        storeState.setLastEvent(this);
        storeState.notifyOB();
    }

    @Override
    public String toString() {
        return "Close";
    }


    /**
     * Förklara gärna denna
     *
     * */

    private void updateTimeRegisterEmpty(StoreState storeState){
        if (storeState.isRegEmpty()){
            double timeEmpty = storeState.getTime() - storeState.getLastEvent().getExecuteTime();
            storeState.addTotalTimeRegEmpty(timeEmpty);
        }
    }
}