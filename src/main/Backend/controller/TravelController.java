package controller;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import tickets.Ticket;
import travel.Travel;

import javax.money.MonetaryAmount;

public class TravelController {
    private Travel travel;
    public TravelController(Travel travel){
        this.travel = travel;
    };

    public void newTravel(String name){
        this.travel.setTravelName(name);
    };
    public void addMember(String name){
        this.travel.getMemberDatabase().addEntry(name,Money.of(0, "EUR"));
    };
    public void removeMember(String name){
        this.travel.getMemberDatabase().removeEntry(name);
    };
    public void getMembers(){
        this.travel.getMemberDatabase().getItterator();
    }
    public Integer addTicket(Ticket t){
        Integer ID = this.travel.getTicketDatabase().size();
        this.travel.getTicketDatabase().addEntry(ID,t);
        return ID;
    };
    public void removeTicket(Integer k){
        this.travel.getTicketDatabase().removeEntry(k);
    };
    public void getTickets(){
        this.travel.getTicketDatabase().getItterator();
    }

}
