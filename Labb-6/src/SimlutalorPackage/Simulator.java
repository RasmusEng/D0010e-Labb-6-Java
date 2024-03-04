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
    public Simulator (State currentState, EventQueue eventQueue, View view){

        while(!currentState.getStop() || eventQueue.hasNextEvent()){
            Event currentEvent = eventQueue.getNextEvent();
            currentState.addTime(currentEvent.getExecuteTime()); // sätter simulatortiden till rätt tid
            currentEvent.executeMe(currentState); //kör själva eventet.
        }

    }


}
