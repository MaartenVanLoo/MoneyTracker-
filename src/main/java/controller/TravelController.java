package controller;

import javax.money.MonetaryAmount;

import factory.TicketFactory;
import observers.Observer;
import org.javamoney.moneta.Money;
import tickets.EventTickets;
import tickets.Ticket;
import travel.Travel;

import java.util.ArrayList;
import java.util.HashMap;

public class TravelController {
    private Travel travelObject;
    private TicketFactory factory = new TicketFactory();
    public TravelController(Travel travel){
        this.travelObject = travel;
    };

    public void newTravel(String name){
        this.travelObject.setTravelName(name);
    };
    public String getTravelName(){return this.travelObject.getTravelName();}
    public void addMember(String name){
        this.travelObject.getMemberDatabase().addEntry(name,new HashMap<String, MonetaryAmount>());
    };
    public void removeMember(String name){
        this.travelObject.getMemberDatabase().removeEntry(name);
    };
    public ArrayList<String> getMembers(){
        return this.travelObject.getMemberDatabase().getNames();
    }
    public boolean isEven(EventTickets type){return factory.isEven(type);}
    public Integer addTicket(Ticket t){
        Integer ID = this.travelObject.getTicketDatabase().size();
        this.travelObject.getTicketDatabase().addEntry(ID,t);
        return ID;
    };
    public Ticket makeTicket(EventTickets type){return factory.getTicket(type,"",Money.of(0, "EUR"),new ArrayList<String>(),new ArrayList<MonetaryAmount>() );}

    public void addObserver(Observer o){
        this.travelObject.getMemberDatabase().addObserver(o);
        this.travelObject.getTicketDatabase().addObserver(o);
    };
    public void removeObserver(Observer o){
        this.travelObject.getMemberDatabase().removeObserver(o);
        this.travelObject.getTicketDatabase().removeObserver(o);
    }
    public void compute(){
        travelObject.compute();
    };
    public MonetaryAmount getBalance(String name){
        return this.travelObject.getMemberDatabase().getBalance(name);
    }
    public HashMap<String,MonetaryAmount> getDebts(String name){
        return this.travelObject.getMemberDatabase().getEntry(name);
    }


}
