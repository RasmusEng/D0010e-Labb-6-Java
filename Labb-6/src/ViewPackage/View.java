package ViewPackage;

import StatePackage.State;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * <br>
 * View of the Sim
 * <br>
 * The View class implements the Observer interface
 * and observes the State
 *
 * */

public class View implements Observer {
    @Override
    public void update(Observable o, Object arg)
    {
        System.out.println("Tjohej");
    }
}
