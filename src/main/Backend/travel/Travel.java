package travel;


import database.DatabaseIterator;
import database.MemberDatabase;
import database.TicketDatabase;
import org.javamoney.moneta.Money;
import tickets.Ticket;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Travel {
    //private static Travel single_instace = null;
    protected String travelName;
    protected TicketDatabase ticketBase;
    protected MemberDatabase memsDatabase;

    public Travel(){
        this.travelName = "";
        this.ticketBase = TicketDatabase.getInstance();
        this.memsDatabase = MemberDatabase.getInstance();
    }
    public void TravelmetBases(String name, TicketDatabase ticketBase, MemberDatabase memsDatabase){
        this.travelName = name;
        this.ticketBase = ticketBase;
        this.memsDatabase = memsDatabase;
    }
    public Travel(String travelName){
        this.travelName = travelName;
        this.ticketBase = TicketDatabase.getInstance();
        this.memsDatabase = MemberDatabase.getInstance();

    }
    /* public static Travel getInstance(){
        if (single_instace == null){
            single_instace = new Travel();
        }
        return single_instace;
    }*/
    public String getTravelName(){
        return this.travelName;
    }
    public void setTravelName(String name){this.travelName = name;}
    public TicketDatabase getTicketDatabase(){
         return this.ticketBase;
    }
    public MemberDatabase getMemberDatabase(){
        return this.memsDatabase;
    }


    public void compute(){
        DatabaseIterator ticketiterator= this.getTicketDatabase().getIterator();
        ArrayList<String> members = this.getMemberDatabase().getNames();

        //Go over every ticket
        while (!ticketiterator.end()){
            //get next ticket
            Ticket ticket = (Ticket)ticketiterator.next();
            String payer = ticket.getPayer();
            MonetaryAmount totalAmount = ticket.getTotalAmount();

            //Register payer paying all:
            if (this.getMemberDatabase().getEntry(payer).containsKey(payer)){
                this.getMemberDatabase().getEntry(payer).get(payer).add(totalAmount);
            }else{
                this.getMemberDatabase().getEntry(payer).put(payer, totalAmount);
            }

            //Register all others having to pay the payer:
            HashMap<String, MonetaryAmount> distributionKey =  ticket.getDistributionKey();
            for (Map.Entry<String, MonetaryAmount> entry : distributionKey.entrySet()){
                String key = entry.getKey();
                MonetaryAmount value = entry.getValue();
                if (!key.equals(payer)){
                    if (this.getMemberDatabase().getEntry(key).containsKey(payer)){
                        this.getMemberDatabase().getEntry(key).get(payer).subtract(value);
                    }else{
                        this.getMemberDatabase().getEntry(key).put(payer,value.negate());
                    }
                    if (this.getMemberDatabase().getEntry(payer).containsKey(key)){
                        this.getMemberDatabase().getEntry(payer).get(key).add(value);
                    }
                    else{
                        this.getMemberDatabase().getEntry(payer).put(key,value);
                    }
                }
            }

        }
    }



}

