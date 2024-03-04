package CheckoutPackage;

import CustomerPackage.Customer;
import FIFO.*;

public class CheckoutQueue {
    private FIFO queue;

    public CheckoutQueue(){

    }

    public Customer getNextCustomer(){
        return queue.removeFirst();
    }

    public String customersInQueue(){
        return queue.toString();
    }

    public addCustomerToQueue(Customer newCustomer){
        queue.add(newCustomer);
    }
}
