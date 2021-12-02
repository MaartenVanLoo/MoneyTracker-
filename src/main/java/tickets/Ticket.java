package tickets;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.HashMap;

public class Ticket {
    //fields
    protected String payer;
    protected MonetaryAmount totalAmount;
    protected HashMap<String, MonetaryAmount> distributionKey;
    protected EventTickets event;

    public Ticket(){};
    public Ticket(String payer, EventTickets event, MonetaryAmount totalAmount, ArrayList<String> debtors, ArrayList<MonetaryAmount>debts){
        this.payer = payer;
        this.event= event;
        this.totalAmount = totalAmount;
    }


}
