import database.*;
import org.javamoney.moneta.Money;
import tickets.*;
import travel.Travel;




public class IntegrationTest {

    public static void IntegrationTest(String[] args){

        IntegrationTest test = new IntegrationTest();
        test.run();
    }

    public IntegrationTest(){

    }

    public void run(){// Travel.compute testen
        Travel travel = new Travel();
        MemberDatabase memDB = MemberDatabase.getInstance();

        TicketDatabase ticketDB = TicketDatabase.getInstance();
        Ticket first = new Ticket("a", EventTickets.AirplaneTicket, Money.of(1,"EUR"),memDB, 0);

        travel.compute();


    }
}
