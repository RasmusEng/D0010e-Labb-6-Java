
package Optimize;
import Random.*;
import EventPackage.*;
import SimlutalorPackage.RunSim;
import SimlutalorPackage.Simulator;
import StatePackage.*;
import ViewPackage.*;

import java.util.Random;

/**
 *
 * @author Rasmus, Albin, Walter, Axel
 * Optimize of the Sim
 *
 */

public class Optimieze {

    /**
     * A method that runs an iteration of the Store Simulation
     * and returns the last state of the Store
     *
     * @param newState Is used to run the Simulator
     * @param view Is used to run the Simulator
     * @return The last StoreState of the iteration of the Simulation
     *
     * */

    public static StoreState Optimize1(StoreState newState, View view) {
        EventQueue eventQueue = new EventQueue();
        Simulator store = new Simulator(newState, eventQueue, view);
        store.Run();

        return (StoreState) store.getCurrentState();
    }


    /**
     * A method that runs the Optimize1 method as many times
     * as the max amount of Customers that are allowed in the
     * Store
     *
     * @param seed The seed that is used to run the Optimize1 method
     * @return The most optimal amount of registers for a given seed and
     *         the least amount of lost customers
     *
     * */

    public static int getOptimalAmountOfKassor(long seed) {
        View startview = new View();
        StoreState startState = new StoreState(startview);
        int lowestsofar = startState.M;
        int retKassor = 1;

        for(int kassor = 1; kassor <= startState.M; kassor++){
            StoreState newState = new StoreState(startview);
            newState.setSeed(seed);
            newState.setAmountOfRegisters(kassor);

            StoreState store = Optimize1(newState, startview);

            if(store.getLostCustomers() < lowestsofar){
                retKassor = kassor;
                lowestsofar = store.getLostCustomers();
            }
        }


        return retKassor;
    }


    /**
     * A method that runs the getOptimalAmountOfKassor method 100 times
     * */

    public static void getAbsoluteAmountOfOptimalaHakanSeed(long seed){
        Random f = new Random(seed);
        int previousLowestRegisters = 0;
        int count = 0;

        View bestView = new OptimizeView();
        StoreState SuperStore = new StoreState(bestView);

        while(count <= 100){
            long seed1 = f.nextLong();
            int opti = getOptimalAmountOfKassor(seed1);
            
            if(opti > previousLowestRegisters){
               previousLowestRegisters = opti;
               SuperStore.setSeed(seed1);
               SuperStore.setAmountOfRegisters(opti);
               count = 0;
            }
            else {
                count++;
            }
            //Optimize1(SuperStore, bestView);
        }

        Optimize1(SuperStore, bestView);
    }

   public static void main(String[] args) {
        int vilen = 2;
       switch(vilen){
           case 2:
               System.out.println(
                       getOptimalAmountOfKassor(42));
               break;
           case 3:
               getAbsoluteAmountOfOptimalaHakanSeed(1234);
               break;
       }

    }
}
