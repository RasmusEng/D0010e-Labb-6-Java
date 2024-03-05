package EventPackage;

import CheckoutPackage.CheckoutQueue;
import CustomerPackage.Customer;
import CustomerPackage.CustomerFactory;
import StatePackage.*;
import ViewPackage.StoreView;

public abstract class Event {
    private double executeTime;

    public abstract void executeMe(State state, EventQueue queue);

    public double getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(double executeTime){
        this.executeTime = executeTime;
    }

    public abstract String toString();
}

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

class CustomerArrivalEvent extends Event{

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

    private void updateTimeRegisterEmpty(StoreState storeState){
        if (storeState.isRegEmpty()){
            double timeEmpty = storeState.getTime() - storeState.getLastEvent().getExecuteTime();
            storeState.addTotalTimeRegEmpty(timeEmpty);
        }
    }
}

class CustomerPickedEvent extends Event{

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

    private void updateTimeRegisterEmpty(StoreState storeState){
        if (storeState.isRegEmpty()){
            double timeEmpty = storeState.getTime() - storeState.getLastEvent().getExecuteTime();
            storeState.addTotalTimeRegEmpty(timeEmpty);
        }
    }
}

class CustomerCheckoutEvent extends Event{


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

    private void updateTimeRegisterEmpty(StoreState storeState){
        if (storeState.isRegEmpty()){
            double timeEmpty = storeState.getTime() - storeState.getLastEvent().getExecuteTime();
            storeState.addTotalTimeRegEmpty(timeEmpty);
        }
    }
}
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
    

    private void updateTimeRegisterEmpty(StoreState storeState){
        if (storeState.isRegEmpty()){
            double timeEmpty = storeState.getTime() - storeState.getLastEvent().getExecuteTime();
            storeState.addTotalTimeRegEmpty(timeEmpty);
        }
    }
}