package tickets;

import javax.money.MonetaryAmount;
import java.util.ArrayList;

public class EvenTicket extends Ticket{
    public EvenTicket() {
        super();
    }

    public EvenTicket(String payer, EventTickets event, MonetaryAmount totalAmount, ArrayList<String> debtors, ArrayList<MonetaryAmount>debts) {
        super(payer, event, totalAmount, debtors, debts);
    }
}
