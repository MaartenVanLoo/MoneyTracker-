package database;

public class MemberIterator implements DatabaseIterator {
    private MemberDatabase mDb;

    public MemberIterator(MemberDatabase mDb) {
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
        return null;
    }
}
