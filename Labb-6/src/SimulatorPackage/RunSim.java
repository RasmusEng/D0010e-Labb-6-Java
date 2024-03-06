package SimulatorPackage;


import EventPackage.*;
import StatePackage.*;
import ViewPackage.*;

public class RunSim {


    public static void main(String[] args){
        EventQueue eventQueue = new EventQueue();
        View view = new StoreView();
        State newState = new StoreState(view);
        Simulator store = new Simulator(newState, eventQueue, view);
        store.Run();
    }

}
