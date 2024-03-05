package StatePackage;
import CheckoutPackage.CheckoutQueue;
import EventPackage.Event;
import ViewPackage.*;
import CustomerPackage.Customer;
import StatePackage.ShoppingTimeGenerator;

import java.util.ArrayList;


public class StoreState extends State {

    private int openReg, amountOfRegisters, maxCustomers, customersInStore, checkedOutCustomers, lostCustomers;
    private double lambda, closeTime;
    private boolean isOpen;

    private Event lastEvent;
    private ArrayList<Customer> customerArrayList = new ArrayList<Customer>();


    public StoreState(View view){
        super(view);
    }

    public void addCustomerToQueue(Customer Customer){


    }

    public ShoppingTimeGenerator getShoppingTimeGenerator()
    {
        return new ShoppingTimeGenerator();
    }

    public double getLambda(){
        return lambda;
    }

    public boolean storeFull(){
        if(customersInStore >= maxCustomers){
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

    public int getMaxCustomers(){
        return maxCustomers;
    }

    public double getCloseTime(){
        return closeTime;
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
    public void addLostCustomer(){
        lostCustomers +=1;
    }

    public void addCheckCustByOne(){
        checkedOutCustomers += 1;
    }

    public void setStoreClosed(){
        isOpen = false;
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

    public CheckoutQueue getCheckOutQueue(){
        return new CheckoutQueue();
    }

}
