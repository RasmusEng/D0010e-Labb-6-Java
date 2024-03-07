package SimlutalorPackage;

import StatePackage.State;
import EventPackage.*;
import ViewPackage.*;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * Simulator of the Sim
 *
 * */

public class Simulator {

    private EventQueue eventQueue;
    private View view;
    private State currentState;

    public State getCurrentState() {
        return currentState;
    }

    /**
     * Creates a new Simulator object
     * @param currentState Allows the simulator to store values
     * @param eventQueue Feeds the simulator with stuff to happen
     * @param view Looks at what is happening
     */

    public Simulator (State currentState, EventQueue eventQueue, View view){
        this.currentState = currentState;
        this.eventQueue = eventQueue;
        this.view = view;

        eventQueue.addEventToQueue(new StartSimEvent());
        currentState.notifyOB();
    }

    /**
     * The Run method of the Simulator
     * <br>
     * Runs the Simulator and the Events
     * */

    public void Run(){

        while(!currentState.getStop()){
            Event currentEvent = eventQueue.getNextEvent();
            currentState.addTime(currentEvent.getExecuteTime()); // sätter simulatortiden till rätt tid
            currentEvent.executeMe(currentState, eventQueue);//kör själva eventet.

        }
    }

}
