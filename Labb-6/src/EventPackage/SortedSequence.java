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
        // skall vara insertionSort
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Event key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getExecuteTime() > key.getExecuteTime()) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }


        return list;
    }
}
