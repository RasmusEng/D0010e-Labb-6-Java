package EventPackage;

import StatePackage.State;

abstract public class Event {
    private double executeTime;

    public abstract void executeMe(State state);

    public double getExecuteTime(){
        return executeTime;
    }
}

class StartSimEvent extends Event{
    @Override
    public void executeMe(State state) {

    }

}

class StopSimEvent extends Event{

    @Override
    public void executeMe(State state) {

    }
}

class CustomerArrivalEvent extends Event{
    @Override
    public void executeMe(State state) {

    }
}

class CustomerPickedEvent extends Event{

    @Override
    public void executeMe(State state) {

    }
}

class CustomerCheckoutEvent extends Event{

    @Override
    public void executeMe(State state) {

    }
}
class StoreCloseEvent extends Event{

    @Override
    public void executeMe(State state) {

    }
}