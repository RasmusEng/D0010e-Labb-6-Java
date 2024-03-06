package StatePackage;
import CheckoutPackage.CheckoutQueue;
import EventPackage.Event;
import EventPackage.*;
import Optimize.K;
import Random.ExponentialRandomStream;
import Random.UniformRandomStream;
import ViewPackage.*;
import CustomerPackage.Customer;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * StoreState of the Sim
 *
 * */

public class StoreState extends State implements K {

    private int openReg, amountOfRegisters = 2, customersInStore, checkedOutCustomers, lostCustomers = 0, lastID, customersThatQueued = 0;
    private double totalTimeInQueue, totalTimeRegEmpty;
    private boolean isOpen;
    private CheckoutQueue checkoutQueue = new CheckoutQueue();
    private Event lastEvent;
    private ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
    private ExponentialRandomStream lambda = new ExponentialRandomStream(L, SEED);

    private UniformRandomStream checkoutTime = new UniformRandomStream(LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, SEED);
    private UniformRandomStream pickupTime = new UniformRandomStream(LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME, SEED);
    private DecimalFormat f = new DecimalFormat("0.00");

    /**
     * The constructor of the StoreState which
     * has the same implementation as in State
     * */

    public StoreState(View view){
        super(view);
    }

    public CheckoutQueue getCheckoutQueue() {
        return checkoutQueue;
    }

    public double getLambda(){
        return lambda.next();
    }

    public double getPickupTime(){
        return pickupTime.next();
    }

    public double getCheckoutTime(){
        return Double.parseDouble(f.format(checkoutTime.next()));
    }

    public int getEmptyRegisters(){
        return amountOfRegisters - openReg;
    }

    public void addCustomerThatQueue(){
        customersThatQueued++;
    }

    public int getCustomersAmountThatQueue(){
        return customersThatQueued;
    }

    /**
     * A method that checks if the store is full
     * @return True if the store is full, false otherwise
     * */

    public boolean storeFull(){
        if(customersInStore >= M){
            return true;
        }
        return false;
    }

    /**
     * A method that checks if registers is empty or not
     *
     * @return True if the registers are empty, false otherwise
     *
     * */

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
        lostCustomers ++;
    }

    public int getLostCustomers(){
        return lostCustomers;
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

    /**
     * A method that gets a Customer with the
     * specified id
     *
     * @return The specified Customer if it exists and
     *         returns null if the Customer does not exist
     *
     * @throws RuntimeException If the id is less than
     *                          the size of the CustomerArray
     *
     * */

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

    /**
     * A method that gets a Customer with the
     * specified Event
     *
     * @return The Customer if it exists, null otherwise
     *
     * */

    public Customer getCustomerWithEvent(Event e){
        for (Customer c : customerArrayList){
            if (c.getCurrentEvent() == null){
                continue;
            }
            if(c.getCurrentEvent().equals(e)){
                return c;
            }
        }
        return null;
    }

    public void setLastEvent(Event o){
        lastEvent = o;
    }

    public void setLastCustomerID(int id){
        lastID = id;
    }

    public int getLastCustomerID(){
        return lastID;
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

    public int getCheckedOutCustomers(){
        return checkedOutCustomers;
    }


}
