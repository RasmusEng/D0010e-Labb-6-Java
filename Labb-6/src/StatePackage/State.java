package StatePackage;

import ViewPackage.View;

import java.util.Observable;

public class State extends Observable {

    private boolean stop = false;
    private double calculatedTime;

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

    private void addTime(double time){
        if (time < calculatedTime){
            throw new RuntimeException();
        }
        calculatedTime = time;
    }
    private void notifyOB(){
        setChanged();
        notifyObservers();
    }
}
