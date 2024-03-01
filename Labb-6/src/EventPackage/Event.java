package EventPackage;

import java.util.ArrayList;

abstract public class Event {
    private double executeTime;

    abstract void executeMe(ArrayList<Event> eventQueue);

    public double getExecuteTime(){
        return executeTime;
    }
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