package ViewPackage;

import StatePackage.State;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    public View(State state){
        System.out.println();// Huvudet för programmet(Speciferar alla variabler)
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("testcall");
    }
}
