package FIFO;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.NoSuchElementException;

public interface Queue {
    void add(Object var1);

    void removeFirst() throws NoSuchElementException;

    Object first() throws NoSuchElementException;

    int maxSize();

    boolean isEmpty();

    int size();

    String toString();

    boolean equals(Object var1);

    int hashCode();
}

