package EventPackage;

import java.util.ArrayList;

public class EventQueue {
    private ArrayList<Event> eventQueue = new ArrayList<>();

    public boolean hasNextEvent(){
        return !eventQueue.isEmpty();
    }
    public Event getNextEvent(){
        return eventQueue.remove(0); // Returnerar de event som är näst på tur och tar bort det ur listan
    }

    public void addEventToQueue(Event e){
        eventQueue.add(e); // Lägger till ett event längst bak i listan.
        eventQueue = SortedSequence.sortArrayList(eventQueue); // ser till att eventQueue blir sorterad.
    }

}
