package factory;

import tickets.*;

import javax.money.MonetaryAmount;
import java.util.ArrayList;

public class TicketFactory {
    public Ticket getTicket(EventTickets type, String payer, MonetaryAmount amount, ArrayList<String> debtors, ArrayList<MonetaryAmount>debts){
        switch (type){
            case AirplaneTicket:    return new Ticket(payer,type,amount,debtors,debts);
            case HotelTicket:       return new UnevenTicket(payer,type,amount,debtors,debts);
            case RestaurantTicket:  return new UnevenTicket(payer,type,amount,debtors,debts);
            case TaxiTicket:        return new EvenTicket(payer,type,amount,debtors,debts);
            case ConcertTicket:     return new UnevenTicket(payer,type,amount,debtors,debts);
            case MuseumTicket:      return new UnevenTicket(payer,type,amount,debtors,debts);
            case Custom:            return new CustomTicket(payer,"",amount,debtors,debts);
        }
        return new Ticket();
    }
    public Ticket getTicket(String type, String payer, MonetaryAmount amount, ArrayList<String> debtors, ArrayList<MonetaryAmount>debts){
        return new CustomTicket(payer,type,amount,debtors,debts);
    }
}
