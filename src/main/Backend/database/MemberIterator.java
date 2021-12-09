package database;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class MemberIterator implements DatabaseIterator {
    private MemberDatabase mDb;
    private ArrayList<String> keys;
    private int index;


    public MemberIterator() {
        super();
        this.mDb = MemberDatabase.getInstance();
        for (Object key : mDb.getKeys()) {
            keys.add((String)key);
        }
        this.index = 0;
    }
    @Override
    public boolean end() {
        return this.index == keys.size();
    }

    @Override
    public Object first() {
        return (Object)mDb.getEntry(keys.get(0));
    }

    @Override
    public Object next() {
        if (index != keys.size()){
            index++;
        }
        return (Object)mDb.getEntry(keys.get(index));
    }
    @Override
    public Object prev() {
        if (index > 0){
            index--;
        }
        return (Object)mDb.getEntry(keys.get(index));
    }

    @Override
    public Object last() {
        return (Object)mDb.getEntry(keys.get(keys.size()));
    }
}
