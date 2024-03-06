package EventPackage;

import java.util.ArrayList;

/**
 *
 * @author Rasmus, Albin, Walter, Alex
 * SortedSequence of the Sim
 *
 * */

public class SortedSequence {

    /**
     * A static method that is used to sort an
     * ArrayList instance
     * <br>
     * Note: Meant to be used as a sorter for Events
     *
     * @param list An ArrayList to be sorted <br>
     *             Note: Meant to take a EventQueue
     *
     * @return The list after sorting it
     *
     * */

    public static ArrayList<Event> sortArrayList(ArrayList<Event> list){
        if(list.size() == 0){
            return list; // Om listan är tom
        }
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Event key = list.get(i);
            int j = i - 1;
            // Move elements of list[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && list.get(j).getExecuteTime() > key.getExecuteTime()) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        // BubbleSort algoritm som sorterar EventQueue
        // Den är inte speciellt effektiv men duger
/*        int arrSize = list.size();
        for(int i = 0; i < arrSize; i++){
            for(int j = 0; j < arrSize-i-1; j++){
                if(list.get(j).getExecuteTime() > list.get(i).getExecuteTime()){
                    Event tempEvent =  list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, tempEvent);
                }

            }
        }*/

        return list;
    }
}
