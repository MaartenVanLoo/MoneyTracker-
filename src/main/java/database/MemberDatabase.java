package database;

import observers.Observer;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MemberDatabase implements Database<String, HashMap<String,MonetaryAmount>>{
    private static MemberDatabase single_instace = null;
    private final HashMap<String, HashMap<String,MonetaryAmount>> db = new HashMap<>();
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
    public ArrayList<String> getNames(){
        ArrayList<String> names=new ArrayList<String>();
        for (Object key : db.keySet()) {
            names.add((String)key);
        }
        return names;
    }

    @Override
    public void addEntry(String k, HashMap<String,MonetaryAmount> v) {
        support.firePropertyChange("AddEntry",k,v);
        this.db.put(k, v);
    }

    public MonetaryAmount getBalance(String name){
        return this.db.get(name).getOrDefault(name, Money.of(0,"EUR"));
    }

    @Override
    public DatabaseIterator getIterator() {
        return new MemberIterator();
    }

    @Override
    public int size(){
        return this.db.size();
    }

    @Override
    public HashMap<String,MonetaryAmount> getEntry(String k) {
        return this.db.getOrDefault(k, new HashMap<String,MonetaryAmount>());
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


