import controller.TravelController;
import database.*;
import org.javamoney.moneta.Money;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import tickets.*;
import travel.Travel;

import javax.money.MonetaryAmount;
import java.util.HashMap;


// Run with PowerMock, an extended version of Mockito
@RunWith(PowerMockRunner.class)
public class IntegrationTest {

    public static void IntegrationTest(String[] args){

        IntegrationTest test = new IntegrationTest();
        test.run();
    }

    public IntegrationTest(){

    }
    @Test
    public void run(){// Travel.compute testen
        Travel travel = new Travel();
        TravelController controller = new TravelController(travel);

        controller.newTravel("TestTravel");
        controller.addMember("A");
        controller.addMember("B");
        controller.addMember("C");

        //Airplane ticket = even ticket
        Ticket airplaneTicket = controller.makeTicket(EventTickets.AirplaneTicket);
        airplaneTicket.setPayer("A");
        airplaneTicket.setTotalAmount(Money.of(15,"EUR"));
        airplaneTicket.addDebtor("A",Money.of(5,"EUR"));
        airplaneTicket.addDebtor("B",Money.of(5,"EUR"));
        airplaneTicket.addDebtor("C",Money.of(5,"EUR"));
        controller.addTicket(airplaneTicket);

        //HotelTicket = Uneven ticket
        Ticket hotelTicket = controller.makeTicket(EventTickets.HotelTicket);
        hotelTicket.setPayer("B");
        hotelTicket.setTotalAmount(Money.of(35,"EUR"));
        hotelTicket.addDebtor("A",Money.of(20,"EUR"));
        hotelTicket.addDebtor("B",Money.of(10,"EUR"));
        hotelTicket.addDebtor("C",Money.of(5,"EUR"));
        controller.addTicket(hotelTicket);

        controller.compute();
        // Expected results:
        /*
        + = has already paid to much
        - = has to pay others more (is in debt)
        A: 15 paid, debt: 5+20 = 25, balance = -10
        B: 35 paid, debt: 5+10 = 15, balance = 20
        C: 0 paid , debt: 5+5  = 10, balance = -10

        debts:
        - = has to pay
        + = has to recieve
        A: B = 5-20=-15    C = 5
        B: A = -5+20=15    C = 5
        C: A = -5          B = -5
        */

        System.out.println(controller.getBalance("A"));
        System.out.println(controller.getBalance("B"));
        System.out.println(controller.getBalance("C"));
        Assert.assertEquals(Money.of(-10,"EUR"),controller.getBalance("A"));
        Assert.assertEquals(Money.of(20,"EUR"),controller.getBalance("B"));
        Assert.assertEquals(Money.of(-10,"EUR"),controller.getBalance("C"));

        //Check debts of A
        HashMap<String, MonetaryAmount> debt = controller.getDebts("A");
        Assert.assertEquals(Money.of(-10,"EUR"),debt.get("A")); // = balance
        Assert.assertEquals(Money.of(-15,"EUR"),debt.get("B"));
        Assert.assertEquals(Money.of(5,"EUR"),debt.get("C"));

        //Check debts of B
        debt = controller.getDebts("B");
        Assert.assertEquals(Money.of(15,"EUR"),debt.get("A"));
        Assert.assertEquals(Money.of(20,"EUR"),debt.get("B")); // = balance
        Assert.assertEquals(Money.of(5,"EUR"),debt.get("C"));

        //Check debts of C
        debt = controller.getDebts("C");
        Assert.assertEquals(Money.of(-5,"EUR"),debt.get("A"));
        Assert.assertEquals(Money.of(-5,"EUR"),debt.get("B"));
        Assert.assertEquals(Money.of(-10,"EUR"),debt.get("C")); // = balance

    }
}
