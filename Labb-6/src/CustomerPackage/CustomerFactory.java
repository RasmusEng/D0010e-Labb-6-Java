package CustomerPackage;

public class CustomerFactory {
    private int createdCustomers = -1;

    public CustomerFactory(){

    }

    public Customer createCustomer(){
        createdCustomers++;
        return new Customer(createdCustomers);
    }

    public int nextCustomerID(){
        return createdCustomers++;
    }
}
