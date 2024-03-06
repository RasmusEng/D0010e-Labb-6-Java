package SimlutalorPackage;


import EventPackage.*;
import StatePackage.*;
import ViewPackage.*;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * Optimize of the Sim
 *
 * */

public class RunSim {

    /**
     * The main method that runs the Simulator
     *
     * */

    public static void main(String[] args){
        EventQueue eventQueue = new EventQueue();
        View view = new StoreView();
        State newState = new StoreState(view);
        Simulator store = new Simulator(newState, eventQueue, view);
        store.Run();
    }

}
