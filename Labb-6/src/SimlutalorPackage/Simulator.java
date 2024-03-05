package SimlutalorPackage;

import StatePackage.State;
import EventPackage.*;
import ViewPackage.*;

public class Simulator {

    /**
     * creates a new simulatorobject
     * @param currentState allows the simulator to store values
     * @param eventQueue feeds the simulator with stuff to happen
     * @param view looks at what is happening
     */

    private State currentState;
    private EventQueue eventQueue;
    private View view;

    public Simulator (State currentState, EventQueue eventQueue, View view){
        this.currentState = currentState;
        this.eventQueue = eventQueue;
        this.view = view;

        eventQueue.addEventToQueue(new StartSimEvent());
        currentState.notifyOB();
    }

    public void Run(){

        while(!currentState.getStop()){
            Event currentEvent = eventQueue.getNextEvent();
            currentState.addTime(currentEvent.getExecuteTime()); // sätter simulatortiden till rätt tid
            currentEvent.executeMe(currentState, eventQueue);//kör själva eventet.

        }
    }

}
