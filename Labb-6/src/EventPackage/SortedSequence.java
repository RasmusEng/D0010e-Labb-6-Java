package EventPackage;

import java.util.ArrayList;

public class SortedSequence {
    public static ArrayList<Event> sortArrayList(ArrayList<Event> list){
        if(list.size() == 0){
            return list; // Om listan är tom
        }

        // BubbleSort algoritm som sorterar EventQueue
        // Den är inte speciellt effektiv men duger
        int arrSize = list.size();
        for(int i = 0; i < arrSize; i++){
            for(int j = 0; j < arrSize-i-1; j++){
                if(list.get(j).getExecuteTime() > list.get(i).getExecuteTime()){
                    Event tempEvent =  list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, tempEvent);
                }

            }
        }

        return list;
    }
}
