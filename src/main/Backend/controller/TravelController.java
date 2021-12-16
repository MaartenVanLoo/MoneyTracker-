package controller;

import javax.money.MonetaryAmount;

import database.DatabaseIterator;
import factory.TicketFactory;
import observers.Observer;
import org.javamoney.moneta.Money;
import tickets.EventTickets;
import tickets.Ticket;
import travel.Travel;

import javax.money.MonetaryAmount;
import java.util.ArrayList;

public class TravelController {
    private Travel travel;
    private TicketFactory factory = new TicketFactory();
    public TravelController(Travel travel){
        this.travel = travel;
    };

    public void newTravel(String name){
        this.travel.setTravelName(name);
    };
    public String getTravelName(){return this.travel.getTravelName();}
    public void addMember(String name){
        this.travel.getMemberDatabase().addEntry(name,Money.of(0, "EUR"));
    };
    public void removeMember(String name){
        this.travel.getMemberDatabase().removeEntry(name);
    };
    public ArrayList<String> getMembers(){
        return this.travel.getMemberDatabase().getNames();
    }
    public boolean isEven(EventTickets type){return factory.isEven(type);}
    public Integer addTicket(Ticket t){
        Integer ID = this.travel.getTicketDatabase().size();
        this.travel.getTicketDatabase().addEntry(ID,t);
        return ID;
    };
    public Ticket makeTicket(EventTickets type){return factory.getTicket(type,"",Money.of(0, "EUR"),new ArrayList<String>(),new ArrayList<MonetaryAmount>() );}
    public void removeTicket(Integer k){
        this.travel.getTicketDatabase().removeEntry(k);
    };
    public DatabaseIterator getTickets(){
        return this.travel.getTicketDatabase().getItterator();
    }

    public void addObserver(Observer o){
        this.travel.getMemberDatabase().addObserver(o);
        this.travel.getTicketDatabase().addObserver(o);
    };
    public void removeObserver(Observer o){
        this.travel.getMemberDatabase().removeObserver(o);
        this.travel.getTicketDatabase().removeObserver(o);
    }
    public void compute(){};



}
