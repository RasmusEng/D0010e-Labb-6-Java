
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
     * @return The most optimal amount of registers for a given seed
     *
     * */

    public static int Optimize2(long seed) {
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
     * A method that runs the Optimize2 method 100 times
     * */

    public static void Optimize3(long seed){

        Random r = new Random(seed);
        int count = 0;





        //long prevseed = r.nextLong();
        int bestkassasofar = 999999;
        long bestseedsofar = 0;
        int currentkassa;
        while(count <= 100){
            seed = r.nextLong();
            currentkassa = Optimize2(seed);

            if(currentkassa < bestkassasofar){
                bestkassasofar= currentkassa;
                bestseedsofar = seed;
                count = 0;
            }
           count++;
        }

        View bestView = new OptimizeView();
        StoreState bestStore = new StoreState(bestView);
        bestStore.setSeed(bestseedsofar);
        bestStore.setAmountOfRegisters(Optimize2(bestseedsofar));
        Optimize1(bestStore,bestView);
    }

    public static void optimize(long seed) {
        int bestRegisters = Integer.MAX_VALUE;
        long bestSeed = seed;

        for (int i = 0; i < 100; i++) {
            int registers = Optimize2(seed);
            if (registers < bestRegisters) {
                bestRegisters = registers;
                bestSeed = seed;
            }
            seed = new Random().nextLong(); // Generate a new seed
        }

        View bestView = new OptimizeView();
        StoreState bestStore = new StoreState(bestView);
        bestStore.setSeed(bestSeed);
        bestStore.setAmountOfRegisters(bestRegisters);
        Optimize1(bestStore, bestView);
    }

    public static void opti3(long seed){
        Random f = new Random(seed);
        int previousLowest = Integer.MAX_VALUE;
        int count = 0;

        View view = new View();
        View bestView = new OptimizeView();
        StoreState Store = new StoreState(view);
        StoreState SuperStore = new StoreState(bestView);

        while(count < 100){
            long seed1 = f.nextLong();
            Store.setSeed(seed1);
            Store.setAmountOfRegisters(Optimize2(seed1));
            Optimize1(Store, view);
            if(Optimize1(Store, view).getLostCustomers() <= previousLowest){
               previousLowest = Optimize1(Store, view).getLostCustomers();
               SuperStore.setSeed(seed1);
               SuperStore.setAmountOfRegisters(Optimize2(seed1));
               count++;
            }
            else {
                count = 0;
            }
        }
        Optimize1(SuperStore, bestView);
    }


   public static void main(String[] args) {
        opti3(1);
    }
}
