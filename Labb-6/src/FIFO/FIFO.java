
package FIFO;

import CustomerPackage.Customer;

import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * @author Rasmus Engström
 */

public class FIFO implements Queue{
    private ArrayList<Object> queue = new ArrayList<Object>();
    private int maxSize = 0;
    @Override
    public void add(Object o) {
        queue.add(o);
        if(queue.size() >= maxSize){
            maxSize = queue.size();
        }
    }

    @Override
    public Object removeFirst(){
        if(queue.size() == 0){
            throw new NoSuchElementException();
        }
        return queue.remove(0);
    }

    @Override
    public Object first(){
        if(queue.size() > 0){
            return queue.get(0);
        }
        throw new NoSuchElementException();
    }

    @Override
    public int maxSize() {
        return maxSize;
    }

    @Override
    public boolean isEmpty() {
        if(queue.size() != 0){
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return queue.size();
    }

    public Object get(int index){
        return queue.get(index);
    }

    public String toString(){
        String stringQueue = "Queue: ";
        for(int i = 0; i < queue.size(); i++){
            stringQueue += "(" + String.valueOf(queue.get(i)) + ") ";
        }
        return stringQueue;
    }

    public boolean equals(Object o){
        if(!(o instanceof FIFO)){
            throw new ClassCastException();
        }
        //((FIFO)o) betyder att man typecastar o till en FIFO
        if(queue.size() != ((FIFO)o).size()){
            return false;
        }
        // Loopar igenom queue och o.queue och kollar om varje element är lika
        for(int i = 0; i < queue.size(); i++){
            if(queue.get(i) == null){
                if(((FIFO)o).queue.get(i) != null){
                    return false;
                }
            }else {
                if(!(queue.get(i).equals(((FIFO)o).queue.get(i)))){
                    return false;
                }
            }
        }
        return true;
    }
}

