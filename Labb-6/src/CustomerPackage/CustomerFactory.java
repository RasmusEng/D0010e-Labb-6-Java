package CustomerPackage;

import StatePackage.StoreState;

public class CustomerFactory {
    private static int createdCustomers = 0;

    /** Creates a new Customer
     * @param state used to add the customer in the state
     * @return the new created customer
     */
    public static Customer createCustomer(StoreState state){
        Customer newCustomer = new Customer(createdCustomers);
        state.addCustomer(newCustomer);
        createdCustomers++;
        return newCustomer;
    }

    public static int nextCustomerID(){
        return createdCustomers + 1;
    }
}
