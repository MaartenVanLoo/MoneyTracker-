package database;

import observers.Observer;

public interface Database<key,value> {
    void addEntry(key k,value v);
    value getEntry(key k);
    void removeEntry(key k);
    int size();
    DatabaseIterator getItterator();

    void addObserver(Observer o);
    void removeObserver(Observer o);
}
