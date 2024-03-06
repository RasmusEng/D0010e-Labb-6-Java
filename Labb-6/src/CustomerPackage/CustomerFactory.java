package CustomerPackage;

import StatePackage.StoreState;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * CustomerFactory of the StoreSim
 *
 * */

public class CustomerFactory {
    private static int createdCustomers = 0;

    /**
     * Creates a new Customer
     * @param state used to add the customer in the state
     * @return The new created customer
     */
    public static Customer createCustomer(StoreState state){
        Customer newCustomer = new Customer(createdCustomers);
        state.addCustomer(newCustomer);
        createdCustomers++;
        return newCustomer;
    }

    /**
     * A method that returns the id for the next created Customer
     *
     * @return The id for the next Customer
     *
     * */

    public static int nextCustomerID(){
        return createdCustomers + 1;
    }
}
