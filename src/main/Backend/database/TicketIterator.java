package database;

public class TicketIterator implements DatabaseIterator{
    private  TicketDatabase tDb;
    public TicketIterator(TicketDatabase tDb){
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
    public Object prev() {
        return null;
    }

    @Override
    public Object last() {
        return null;
    }
}
