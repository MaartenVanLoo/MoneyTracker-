package database;


import java.util.HashMap;

public interface DatabaseIterator {

    int index = 0;
    boolean end();
    Object first();
    Object next();
    Object last();
}

