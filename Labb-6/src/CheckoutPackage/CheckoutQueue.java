package CheckoutPackage;

import CustomerPackage.Customer;
import FIFO.*;
import sun.misc.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rasmus, Albin, Walter, Axel
 * CheckoutQueue of the StoreSim
 *
 * */

public class CheckoutQueue {
    private String lastQueue = "[]";
    private int lastAmountPeopleInQueue;
   private FIFO queue = new FIFO();

    /**
     * A getter method for the next customer
     *
     * @return The next Customer in the CheckoutQueue
     *
     * */

    public Customer getNextCustomer(){
        return (Customer) queue.removeFirst();
    }

    /**
     * A method that checks if a next customer exists
     *
     * @return True if the CheckoutQueue is not empty, false otherwise
     *
     * */


    public boolean hasNextCustomer(){
        return (!queue.isEmpty());
    }

    /**
     * A method that returns the CheckoutQueue in String format
     *
     * @return The CheckoutQueue as a String
     *
     * */

    public String customersInQueue(){
        return queue.toString();
    }

    /**
     *  A method that returns the current size of the queue
     *
     * @return The current size of the queue
     */
    public int customerQueueSize(){
        return queue.size();
    }

    /**
     *  A method that gets the previous amount of customers in the queue
     *
     * @return last amount of people in the queue
     */
    public int getLastAmountPeopleInQueue(){
        int temp = lastAmountPeopleInQueue;
        lastAmountPeopleInQueue = customerQueueSize();
        return temp;
    }

    /**
     * A method that returns the current customer ID:s in the queue
     * @return String with the current queue:s customerID:s
     */
    public String idInQueue(){
        String idinQueue = "[";
        for(int i = 0; i < queue.size(); i++){
            Customer cust = (Customer) queue.get(i);
            if( i == queue.size()-1){
                idinQueue = idinQueue.concat(Integer.toString(cust.getID()));
            }else {
                idinQueue = idinQueue.concat(Integer.toString(cust.getID()) + ",");
            }
        }
        idinQueue = idinQueue.concat("]");
        return idinQueue;
    }

    /**
     * A method that returns the last queue:s customer ID:s
     * @return String with the previous queue:s customerID:s
     */

    public String getLastQueue(){
        String tempQueue = lastQueue;
        lastQueue = idInQueue();
        return tempQueue;
    }

    /**
     * A method that adds a new Customer to the CheckoutQueue
     *
     * @param newCustomer Takes a new Customer as a parameter to be added to the CheckoutQueue
     *
     * */
    public void addCustomerToQueue(Customer newCustomer){
        queue.add(newCustomer);
    }

}
