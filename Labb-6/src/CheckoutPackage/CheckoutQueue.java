package CheckoutPackage;

import CustomerPackage.Customer;
import FIFO.*;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * CheckoutQueue of the StoreSim
 *
 * */
public class CheckoutQueue {
    private FIFO queue = new FIFO();

    /**
     * A getter method for the next customer
     *
     * @return The next Customer in the CheckoutQueue
     *
     * */
    public Customer getNextCustomer(){
        return queue.removeFirst();
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
     * A method that adds a new Customer to the CheckoutQueue
     *
     * @param newCustomer Takes a new Customer as a parameter to be added to the CheckoutQueue
     *
     * */

    public void addCustomerToQueue(Customer newCustomer){
        queue.add(newCustomer);
    }
}
