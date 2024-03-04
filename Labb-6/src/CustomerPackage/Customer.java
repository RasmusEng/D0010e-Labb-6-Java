package CustomerPackage;

import EventPackage.Event;

public class Customer {
    private int customerID;
    private Event currentEvent;

    protected Customer(int id, Event startevent){
        this.customerID = id;
        this.currentEvent = startevent;

    }

    protected Customer(int id){
        this.customerID = id;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    public int getID() {
        return customerID;
    }
}
