package EventPackage;

import java.util.ArrayList;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * EventQueue of the Sim
 *
 * */

public class EventQueue {
    private ArrayList<Event> eventQueue = new ArrayList<>();

    /**
     * A method that checks if the EventQueue has
     * a next Event
     *
     * @return True if the EventQueue has a next Event, false otherwise
     *
     *
     * */

    public boolean hasNextEvent(){
        return !eventQueue.isEmpty();
    }

    /**
     * A getter method to get the next Event in
     * the EventQueue and removes it from the Queue
     *
     * @return Event that is first in the EventQueue
     *
     * */

    public Event getNextEvent(){
        return eventQueue.remove(0); // Returnerar de event som ar nast pa tur och tar bort det ur listan
    }

    /**
     * A  method that adds an Event to the end in the
     * EventQueue
     *
     * @param e An Event that should be added to the EventQueue
     *
     * */

    public void addEventToQueue(Event e){
        eventQueue.add(e); // Lagger till ett event langst bak i listan.
        eventQueue = SortedSequence.sortArrayList(eventQueue); // ser till att eventQueue blir sorterad.
    }

}
