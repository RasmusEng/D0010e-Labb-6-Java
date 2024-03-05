package CustomerPackage;

import EventPackage.Event;

public class Customer {
    private final int customerID;
    private Event currentEvent;

    private double timeGetInLine = 0;
    private double timeGetOutOfLine = 0;

    protected Customer(int id, Event startevent){
        this.customerID = id;
        this.currentEvent = startevent;

    }

    public void setTimeGetInLine(double timeGetInLine) {
        this.timeGetInLine = timeGetInLine;
    }

    public void setTimeGetOutOfLine(double timeGetOutOfLine) {
        this.timeGetOutOfLine = timeGetOutOfLine;
    }

    public double getTimeSpentInQueue(){
        return timeGetOutOfLine - timeGetInLine;
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
