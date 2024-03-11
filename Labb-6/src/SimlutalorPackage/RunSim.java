package SimlutalorPackage;


import EventPackage.*;
import StatePackage.*;
import ViewPackage.*;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * Runs the Simulator
 *
 * */

public class RunSim {


    /**
     * The main method that runs the Simulator
     * */

    public static void main(String[] args){
        EventQueue eventQueue = new EventQueue();
        View view = new StoreView();
        State newState = new StoreState(view);
        ((StoreState)newState).setAmountOfRegisters(2);
        Simulator store = new Simulator(newState, eventQueue, view);

        store.Run();
    }

}
