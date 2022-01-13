package database;

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
        if (index != tDb.size()){
            index++;
        }
        return tDb.getEntry(index-1);
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
        return tDb.getEntry(tDb.size()-1);
    }
}
