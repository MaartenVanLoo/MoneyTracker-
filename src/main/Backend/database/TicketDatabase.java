package database;

import observers.Observer;
import org.javamoney.moneta.Money;
import tickets.Ticket;

import javax.money.MonetaryAmount;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

public class TicketDatabase implements Database<Integer, Ticket>{
    private static TicketDatabase single_instace = null;
    private final ArrayList<Ticket> db = new ArrayList<>();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private TicketDatabase(){}

    public static TicketDatabase getInstance(){
        if (single_instace == null){
            single_instace = new TicketDatabase();
        }
        return single_instace;
    }
    @Override
    public void addEntry(Integer k,Ticket v) {
        support.firePropertyChange("AddEntry",k,v);
        this.db.add(v);
    }

    @Override
    public Ticket getEntry(Integer k) {
       if (k >= this.db.size() ||  k < 0){
           return new Ticket();
       }
       else{
           return this.db.get(k);
       }
    }

    @Override
    public void removeEntry(Integer k) {
        this.db.remove((int)k);
    }

    @Override
    public int size() {
        return this.db.size();
    }

    @Override
    public DatabaseIterator getItterator() {
        return new TicketItterator(this);
    }

    @Override
    public void addObserver(Observer o) {
        support.addPropertyChangeListener(o);
    }

    @Override
    public void removeObserver(Observer o) {
        support.removePropertyChangeListener(o);
    }
}
