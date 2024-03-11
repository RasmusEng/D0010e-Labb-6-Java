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
@SuppressWarnings("NonAsciiCharacters")
public class StoreState extends State implements K {

    private int openReg, amountOfRegisters, customersInStore, checkedOutCustomers, lostCustomers = 0, lastID, customersThatQueued = 0;
    private double totalTimeInQueue, totalTimeRegEmpty, lastCheckoutTime = 0;
    private boolean isOpen;
    private CheckoutQueue checkoutQueue = new CheckoutQueue();
    private Event lastEvent;
    private ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
    //creates objects for getting new random numbers

    private long seed;
    private ExponentialRandomStream lambda = new ExponentialRandomStream(L, SEED);
    private UniformRandomStream checkoutTime = new UniformRandomStream(LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, SEED);
    private UniformRandomStream pickupTime = new UniformRandomStream(LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME, SEED);
    //creates a new decimalformat for rounding
    private DecimalFormat f = new DecimalFormat("0.00");

    /**
     * The constructor of the StoreState which
     * has the same implementation as in State
     * */

    public StoreState(View view){
        super(view);
    }

    public long getSeed(){
        return seed;
    }


    public void setSeed(long seed){
        lambda = new ExponentialRandomStream(L, seed);
        checkoutTime = new UniformRandomStream(LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, seed);
        pickupTime = new UniformRandomStream(LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME, seed);
        this.seed = seed;
    }
    public double getLastCheckoutTime(){
        return lastCheckoutTime;
    }

    public void setLastCheckoutTime(double lastCheckoutTime){
        this.lastCheckoutTime = lastCheckoutTime;
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
        return checkoutTime.next();
    }

    public int getEmptyRegisters(){
        return amountOfRegisters - openReg;
    }

    public void addCustomerThatQueue(){

        customersThatQueued++;
    }

    public void setAmountOfRegisters(int value)
    {
        amountOfRegisters = value;
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
    public void removeCustomerInStore(Event e){
        customerArrayList.remove(getCustomerWithEvent(e));
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


    /**
     *
     * @return the last executed event
     */
    public Event getLastEvent(){
        return lastEvent;
    }

    private void addTotalTimeInQueue(double moreTime){
        totalTimeInQueue += moreTime;
    }

    /**
     * A method that updates the amount of time people have spent in queue
     */
    public void updateTotalTimeInQueue(){
        double previousTime = this.getLastEvent().getExecuteTime();
        int peopleInQueue = checkoutQueue.customerQueueSize();
        double nowTime = this.getTime();
        double moreTime = (nowTime - previousTime) * peopleInQueue;
        addTotalTimeInQueue(moreTime);
    }

    /**
     * A method that adds time to how much time the registers have been empty
     * @param moreTime how much time to be added
     */
    public void addTotalTimeRegEmpty(double moreTime){
        totalTimeRegEmpty += moreTime;
    }

    /**
     *
     * @return the total time the total time that customers have queued
     */
    public double getTotalTimeInQueue() {
        return totalTimeInQueue;
    }

    /**
     *
     * @return the total time that the registers have been empty
     */
    public double getTotalTimeRegEmpty() {
        return totalTimeRegEmpty;
    }

    /**
     *
     * @return the amount of customers that have payed for their items
     */
    public int getCheckedOutCustomers(){
        return checkedOutCustomers;
    }


}
