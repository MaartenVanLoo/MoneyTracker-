package tickets;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomTicket extends Ticket{
    protected String name;
    public CustomTicket(String payer, String name, MonetaryAmount totalAmount, ArrayList<String> debtors, ArrayList<MonetaryAmount>debts) {
        super(payer, EventTickets.Custom, totalAmount, debtors, debts);
        this.name = name;
    }
}
