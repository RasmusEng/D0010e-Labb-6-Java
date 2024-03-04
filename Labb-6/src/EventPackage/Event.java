package EventPackage;

import StatePackage.State;

import java.util.ArrayList;

abstract public class Event {
    private double executeTime;

    abstract void executeMe(ArrayList<Event> eventQueue, State state);

    public double getExecuteTime(){
        return executeTime;
    }
}

class StartSimEvent extends Event{
    @Override
    void executeMe(ArrayList<Event> eventQueue, State state) {

    }

}

class StopSimEvent extends Event{

    @Override
    void executeMe(ArrayList<Event> eventQueue, State state) {

    }
}

class CustomerArrivalEvent extends Event{
    @Override
    void executeMe(ArrayList<Event> eventQueue, State state) {

    }
}

class CustomerPickedEvent extends Event{

    @Override
    void executeMe(ArrayList<Event> eventQueue, State state) {

    }
}

class CustomerCheckoutEvent extends Event{

    @Override
    void executeMe(ArrayList<Event> eventQueue, State state) {

    }
}
class StoreCloseEvent extends Event{

    @Override
    void executeMe(ArrayList<Event> eventQueue, State state) {

    }
}