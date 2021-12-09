package database;

import java.util.Set;

public class MemberIterator implements DatabaseIterator {
    private MemberDatabase mDb;
    Set keys = null;

    public MemberIterator() {
        this.mDb = MemberDatabase.getInstance();
        this.keys = mDb.getKeys();

    }



    @Override
    public boolean end() {
        return false;
    }

    @Override
    public Object first() {
        return null;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public Object last() {
        return mDb;
    }
}
