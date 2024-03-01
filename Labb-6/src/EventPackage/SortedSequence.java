package EventPackage;

import java.util.ArrayList;

public class SortedSequence {
    private ArrayList<Event> sortedArrayList;
    public ArrayList<Event> sortArrayList(ArrayList<Event> notSorted){
        if(notSorted.size() == 0){
            return notSorted; // Om listan är tom
        }


        //sortedArrayList.add(notSorted.remove(0));

        for(int i = 1; i < notSorted.size(); i++){
            double currentEventTime = notSorted.get(i).getExecuteTime();

            for(int j = i-1; j >= 0 && notSorted.get(j).getExecuteTime() > currentEventTime; j--){

            }
        }

        return notSorted;
    }
}
