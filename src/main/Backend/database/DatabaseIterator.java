package database;


import java.util.HashMap;

public interface DatabaseIterator {

    boolean end();
    Object first();
    Object next();
    Object prev();
    Object last();
}

