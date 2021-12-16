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
        this.distributionKey = new HashMap<String,MonetaryAmount>();
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public MonetaryAmount getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(MonetaryAmount totalAmount) {
        this.totalAmount = totalAmount;
    }

    public HashMap<String, MonetaryAmount> getDistributionKey() {
        return distributionKey;
    }

    public void addDeptor(String debtor, MonetaryAmount debt ) {
        this.distributionKey.put(debtor,debt );
    }
}
