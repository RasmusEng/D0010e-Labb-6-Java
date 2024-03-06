package CustomerPackage;

import EventPackage.Event;

/**
 *
 * @author Rasmus, Albin, Walter, Axel
 * Customer of the StoreSim
 *
 * */

public class Customer {
    private final int customerID;
    private Event currentEvent;

    private double timeGetInLine = 0;
    private double timeGetOutOfLine = 0;

    /**
     * The constructor that creates a new Customer
     * with an id and a start event
     *
     * @param id An Integer that represents the specific Customer
     * */
    protected Customer(int id){
        this.customerID = id;
    }
    /**
     * A method that sets the time when the Customer gets put
     * in the CheckoutQueue
     *
     * @param timeGetInLine A representation of the time
     *
     * */

    public void setTimeGetInLine(double timeGetInLine) {
        this.timeGetInLine = timeGetInLine;
    }

    /**
     * A method that sets the time when the Customer leaves
     * the CheckoutQueue
     *
     * @param timeGetOutOfLine A representation of the time
     *
     * */

    public void setTimeGetOutOfLine(double timeGetOutOfLine) {
        this.timeGetOutOfLine = timeGetOutOfLine;
    }

    /**
     * A getter method that gets the time that was spent in
     * the CheckoutQueue
     *
     * @return The time spent in the CheckoutQueue
     *
     * */

    public double getTimeSpentInQueue(){
        return timeGetOutOfLine - timeGetInLine;
    }

    /**
     * A getter method to get the current Event the Customer has
     *
     * @return The current Event
     *
     * */

    public Event getCurrentEvent() {
        return currentEvent;
    }

    /**
     * A setter method to set the current Event the Customer has
     *
     * @param currentEvent The new Event to set the old one to
     *
     * */

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    /**
     * A getter method to get the id the Customer has
     *
     * @return The Customers id
     *
     * */

    public int getID() {
        return customerID;
    }
}
