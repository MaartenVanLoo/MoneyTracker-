package database;

import observers.Observer;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Set;

public class MemberDatabase implements Database<String, MonetaryAmount>{
    private static MemberDatabase single_instace = null;
    private final HashMap<String, MonetaryAmount> db = new HashMap<>();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private MemberDatabase(){}

    public static MemberDatabase getInstance(){
        if (single_instace == null){
            single_instace = new MemberDatabase();
        }
        return single_instace;
    }
    public Set getKeys(){
        return this.db.keySet();
    }
    @Override
    public void addEntry(String k, MonetaryAmount v) {
        support.firePropertyChange("AddEntry",k,v);
        this.db.put(k,v);
    }

    @Override
    public DatabaseIterator getItterator() {
        return new MemberIterator(this);
    }

    @Override
    public int size(){
        return this.db.size();
    }

    @Override
    public MonetaryAmount getEntry(String k) {
        return this.db.getOrDefault(k, Money.of(0, "EUR"));
    }

    @Override
    public void removeEntry(String k) {
        this.db.remove(k);
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


