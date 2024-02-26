package EventPackage;

import java.util.ArrayList;

abstract public class Event {
    abstract void executeMe(ArrayList<Event> eventQueue);
}

class StartSimEvent{
    public void executeMe(){

    }

}

class StopSimEvent{

}

class CustomerArrivalEvent{

}

class CustomerPickedEvent{

}

class CustomerCheckoutEvent{

}
class StoreCloseEvent{

}