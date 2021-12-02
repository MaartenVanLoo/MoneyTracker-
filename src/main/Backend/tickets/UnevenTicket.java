package tickets;

import javax.money.MonetaryAmount;
import java.util.ArrayList;

public class UnevenTicket extends Ticket{
    public UnevenTicket() {
        super();
    }

    public UnevenTicket(String payer, EventTickets event, MonetaryAmount totalAmount, ArrayList<String> debtors, ArrayList<MonetaryAmount> debts) {
        super(payer, event, totalAmount, debtors, debts);
    }
}
