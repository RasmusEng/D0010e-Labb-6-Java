package StatePackage;

import ViewPackage.View;

import java.util.Observable;

public class State extends Observable {

    private boolean stop = false;
    private double calculatedTime = 0; //simulatorn startar alltid vid tiden 0

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
