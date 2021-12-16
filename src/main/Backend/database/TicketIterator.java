package database;

import java.util.ArrayList;

public class TicketIterator implements DatabaseIterator{
    private  TicketDatabase tDb;
    private int index;
    public TicketIterator(TicketDatabase tDb){
        this.index = 0;
        this.tDb = tDb;
    }

    @Override
    public boolean end() {
        return this.index == tDb.size();
    }

    @Override
    public Object first() {
        return tDb.getEntry(0);
    }

    @Override
    public Object next() {
        Object entry = tDb.getEntry(index);
        if (index != tDb.size()){
            index++;
        }
        return entry;
    }
    @Override
    public Object prev() {
        if (index > 0){
            index--;
        }
        return tDb.getEntry(index);
    }

    @Override
    public Object last() {
        return tDb.getEntry(tDb.size());
    }
}
