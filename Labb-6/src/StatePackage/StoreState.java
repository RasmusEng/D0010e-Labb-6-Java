package StatePackage;
import CheckoutPackage.CheckoutQueue;
import EventPackage.Event;
import Optimize.K;
import ViewPackage.*;
import CustomerPackage.Customer;

import java.util.ArrayList;


public class StoreState extends State implements K {

    private int openReg, amountOfRegisters = 2, customersInStore, checkedOutCustomers, lostCustomers;
    private double totalTimeInQueue, totalTimeRegEmpty;
    private boolean isOpen;
    private CheckoutQueue checkoutQueue = new CheckoutQueue();

    private Event lastEvent;
    private ArrayList<Customer> customerArrayList = new ArrayList<Customer>();


    public StoreState(View view){
        super(view);
    }


    public CheckoutQueue getCheckoutQueue() {
        return checkoutQueue;
    }

    public boolean storeFull(){
        if(customersInStore >= M){
            return true;
        }
        return false;
    }

    public boolean isRegEmpty(){
        if(openReg < amountOfRegisters){
            return true;
        }
        return false;
    }
    public int getAmountOfRegisters(){
        return amountOfRegisters;
    }

    public void addOpenReg(){
        openReg +=1;
    }
    public void removeOpenReg(){
        openReg -=1;
    }
    public void addCustomerInStore(){
        customersInStore += 1;
    }
    public void removeCustomerInStore(){
        customersInStore -=1;
    }
    public int getCustomersInStore(){
        return customersInStore;
    }
    public void addLostCustomer(){
        lostCustomers +=1;
    }

    public void addCheckCustByOne(){
        checkedOutCustomers += 1;
    }

    public void setStoreClosed(){
        isOpen = false;
    }

    public void setStoreOpen(){
        isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void addCustomer(Customer e){
        customerArrayList.add(e);
    }

    public Customer getCustomerWithID(int id){
        if (id < customerArrayList.size()){
            throw new RuntimeException();
        }
        for (Customer e : customerArrayList){
            if (e.getID() == id){
                return e;
            }
        }
        return null;
    }

    public Customer getCustomerWithEvent(Event e){
        for (Customer c : customerArrayList){
            if(c.getCurrentEvent().equals(e)){
                return c;
            }
        }
        return null;
    }

    public void setLastEvent(Event o){
        lastEvent = o;
    }

    public Event getLastEvent(){
        return lastEvent;
    }

    public void addTotalTimeInQueue(double moreTime){
        totalTimeInQueue += moreTime;
    }

    public void addTotalTimeRegEmpty(double moreTime){
        totalTimeRegEmpty += moreTime;
    }

    public double getTotalTimeInQueue() {
        return totalTimeInQueue;
    }

    public double getTotalTimeRegEmpty() {
        return totalTimeRegEmpty;
    }
    public double getPickupTime() {
        return (LOW_COLLECTION_TIME + HIGH_COLLECTION_TIME) / 2;
    }

    public double getCheckoutTime(){
        return (LOW_PAYMENT_TIME + HIGH_PAYMENT_TIME) / 2;
    }
}
