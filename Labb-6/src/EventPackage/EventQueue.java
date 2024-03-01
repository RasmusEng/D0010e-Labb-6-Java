package EventPackage;

import java.util.ArrayList;

public class EventQueue {
    private ArrayList<Event> eventQueue;

    public EventQueue(){

    }
    public Event getNextEvent(){
        return eventQueue.remove(0);
    }

    public void addEventToQueue(Event e){
        eventQueue.add(eventQueue.size(), e);

    }

}
