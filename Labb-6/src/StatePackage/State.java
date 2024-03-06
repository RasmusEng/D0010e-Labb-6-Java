package StatePackage;

import ViewPackage.View;

import java.util.Observable;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * State of the Sim
 *
 * */

public class State extends Observable {

    private boolean stop = false;
    private double calculatedTime = 0; //simulatorn startar alltid vid tiden 0

    /**
     * The constructor of the State
     * @param view The view that observes
     *             the State of the sim
     *
     * */

    public State(View view){
        this.addObserver(view);
    }

    public boolean getStop(){
        return stop;
    }

    public void setStop()
    {
        stop = true;
    }

    public double getTime(){
        return calculatedTime;
    }

    /**
     * A method that adds the time to the current time
     *
     * @param time The time that should now be the
     *             current time
     * @throws RuntimeException If the time is less than the current time
     *
     * */

    public void addTime(double time){
        if (time < calculatedTime){
            throw new RuntimeException();
        }
        calculatedTime = time;
    }
    public void notifyOB(){
        setChanged();
        notifyObservers();
    }
}
