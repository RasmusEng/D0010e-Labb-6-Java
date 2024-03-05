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
}

class StopSimEvent extends Event{ //TODO "KLAR"

    @Override
    public void executeMe(State state, EventQueue queue) {
        StoreState storeState = (StoreState) state;
        storeState.setStop(); //bör göra rätt grej
        storeState.notifyOB();
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
}

class CustomerPickedEvent extends Event{

    CustomerPickedEvent(double exeTime){
        this.setExecuteTime(exeTime);
    }
    @Override
    public void executeMe(State state, EventQueue queue) {
        StoreState storeState = (StoreState) state;
        storeState.addTime(this.getExecuteTime());

        if (storeState.isRegEmpty()){
            storeState.addOpenReg();
            Event checkout = new CustomerCheckoutEvent(this.getExecuteTime() + storeState.getShoppingTimeGenerator().getCheckoutTime());
            storeState.getCustomerWithEvent(this).setCurrentEvent(checkout);
            queue.addEventToQueue(checkout);
        } else {

            storeState.addCustomerToQueue(storeState.getCustomerWithEvent(this));
            storeState.getCustomerWithEvent(this).setCurrentEvent(null);

        }
        storeState.setLastEvent(this);
        storeState.notifyOB();

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
        storeState.addCheckCustByOne();

        if (storeState.getCheckOutQueue().hasNextCustomer()){
            double nexttime = storeState.getTime() + storeState.getShoppingTimeGenerator().getCheckoutTime();
            Event newCheckout = new CustomerCheckoutEvent(nexttime);
            storeState.getCheckOutQueue().getNextCustomer().setCurrentEvent(newCheckout);
            queue.addEventToQueue(newCheckout);
        } else {
            storeState.removeOpenReg();
        }

        storeState.notifyOB();

    }
}
class StoreCloseEvent extends Event{ //TODO: "KLAR"
    @Override
    public void executeMe(State state, EventQueue queue){
        StoreState storeState = (StoreState) state;
        storeState.addTime(this.getExecuteTime());
        storeState.setStoreClosed();

        storeState.notifyOB();
    }
}