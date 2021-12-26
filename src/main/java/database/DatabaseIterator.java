package database;


public interface DatabaseIterator {

    boolean end();
    Object first();
    Object next();
    Object prev();
    Object last();
}

