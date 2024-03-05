package CheckoutPackage;

import CustomerPackage.Customer;
import FIFO.*;

public class CheckoutQueue {
    private FIFO queue = new FIFO();

    public Customer getNextCustomer(){
        return queue.removeFirst();
    }

    public boolean hasNextCustomer(){
        return (!queue.isEmpty());
    }

    public String customersInQueue(){
        return queue.toString();
    }

    public void addCustomerToQueue(Customer newCustomer){
        queue.add(newCustomer);
    }
}
