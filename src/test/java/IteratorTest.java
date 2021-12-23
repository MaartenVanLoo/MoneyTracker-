
import database.TicketIterator;
import org.javamoney.moneta.Money;
import tickets.*;
import org.junit.Assert;
import org.junit.runner.RunWith;
import database.TicketDatabase;
import database.MemberDatabase;
import travel.Travel;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.HashMap;

@RunWith(PowerMockRunner.class)
// Prepare class RegistrationController for testing by injecting mocks
@PrepareForTest(TicketIterator.class)
public class IteratorTest {

    public IteratorTest(){

    }
    @Before
    public void initialize(){

    }

    @Test
    public void first() throws Exception{ // test edgecases voor de functies in ticketiterator

        //create mock
        TicketDatabase mock_TDB = Mockito.mock(TicketDatabase.class);
        MemberDatabase mock_MDB = Mockito.mock(MemberDatabase.class);
        EvenTicket mock_ET = Mockito.mock(EvenTicket.class);
        UnevenTicket mock_UT = Mockito.mock(UnevenTicket.class);

        // mock constructors
        HashMap<String , MonetaryAmount> mock_Hash = (HashMap<String, MonetaryAmount>)Mockito.mock(HashMap.class);
        ArrayList<String> test_debtors = (ArrayList<String>)Mockito.mock(ArrayList.class);
        test_debtors.add("a");
        test_debtors.add("b");
        test_debtors.add("c");
        ArrayList<MonetaryAmount> test_debts = (ArrayList<MonetaryAmount>)Mockito.mock(ArrayList.class);
        test_debts.add(Money.of(10,"EUR"));
        test_debts.add(Money.of(20,"EUR"));
        test_debts.add(Money.of(30,"EUR"));
        mock_MDB.addEntry("a",mock_Hash);
        Ticket testtick = new Ticket("a", EventTickets.AirplaneTicket, Money.of(10,"EUR"),test_debtors, test_debts );
        mock_TDB.addEntry(0,testtick);
        MemberDatabase mock_mdb = MemberDatabase.getInstance();
        TicketDatabase mock_tdb = TicketDatabase.getInstance();


        TicketIterator iteratorUnderTest = new TicketIterator(mock_tdb);
        Assert.assertEquals(testtick, iteratorUnderTest.first());

    }
    @Test
    public void end() throws Exception{ // test edgecases voor de functies in ticketiterator

        //create mock
        TicketDatabase mock_TDB = Mockito.mock(TicketDatabase.class);
        MemberDatabase mock_MDB = Mockito.mock(MemberDatabase.class);
        EvenTicket mock_ET = Mockito.mock(EvenTicket.class);
        UnevenTicket mock_UT = Mockito.mock(UnevenTicket.class);

        // mock constructors
        HashMap<String , MonetaryAmount> mock_Hash = (HashMap<String, MonetaryAmount>)Mockito.mock(HashMap.class);
        ArrayList<String> test_debtors = (ArrayList<String>)Mockito.mock(ArrayList.class);
        test_debtors.add("a");
        test_debtors.add("b");
        test_debtors.add("c");
        ArrayList<MonetaryAmount> test_debts = (ArrayList<MonetaryAmount>)Mockito.mock(ArrayList.class);
        test_debts.add(Money.of(10,"EUR"));
        test_debts.add(Money.of(20,"EUR"));
        test_debts.add(Money.of(30,"EUR"));
        mock_MDB.addEntry("a",mock_Hash);
        Ticket testtick = new Ticket("a", EventTickets.AirplaneTicket, Money.of(1,"EUR"),test_debtors, test_debts );
        mock_TDB.addEntry(0,testtick);
        MemberDatabase mock_mdb = MemberDatabase.getInstance();
        TicketDatabase mock_tdb = TicketDatabase.getInstance();


        TicketIterator iteratorUnderTest = new TicketIterator(mock_tdb);
        Assert.assertTrue(iteratorUnderTest.end());
        //Assert.assertEquals(testtick, iteratorUnderTest.first());


    }
    @Test
    public void next() throws Exception{ // test edgecases voor de functies in ticketiterator

        //create mock
        TicketDatabase mock_TDB = Mockito.mock(TicketDatabase.class);
        MemberDatabase mock_MDB = Mockito.mock(MemberDatabase.class);
        EvenTicket mock_ET = Mockito.mock(EvenTicket.class);
        UnevenTicket mock_UT = Mockito.mock(UnevenTicket.class);

        // mock constructors
        HashMap<String , MonetaryAmount> mock_Hash = (HashMap<String, MonetaryAmount>)Mockito.mock(HashMap.class);
        ArrayList<String> test_debtors = (ArrayList<String>)Mockito.mock(ArrayList.class);
        test_debtors.add("a");
        test_debtors.add("b");
        test_debtors.add("c");
        ArrayList<MonetaryAmount> test_debts = (ArrayList<MonetaryAmount>)Mockito.mock(ArrayList.class);
        test_debts.add(Money.of(10,"EUR"));
        test_debts.add(Money.of(20,"EUR"));
        test_debts.add(Money.of(30,"EUR"));
        mock_MDB.addEntry("a",mock_Hash);
        Ticket testtick = new Ticket("a", EventTickets.AirplaneTicket, Money.of(1,"EUR"),test_debtors, test_debts );
        Ticket testtick2 = new Ticket("a", EventTickets.AirplaneTicket, Money.of(2,"EUR"),test_debtors, test_debts );
        mock_TDB.addEntry(0,testtick);
        mock_TDB.addEntry(1,testtick2);
        MemberDatabase mock_mdb = MemberDatabase.getInstance();
        TicketDatabase mock_tdb = TicketDatabase.getInstance();


        TicketIterator iteratorUnderTest = new TicketIterator(mock_tdb);
        Assert.assertEquals(testtick2, iteratorUnderTest.next());


    }
    @Test
    public void prev() throws Exception{ // test edgecases voor de functies in ticketiterator

        //create mock
        TicketDatabase mock_TDB = Mockito.mock(TicketDatabase.class);
        MemberDatabase mock_MDB = Mockito.mock(MemberDatabase.class);
        EvenTicket mock_ET = Mockito.mock(EvenTicket.class);
        UnevenTicket mock_UT = Mockito.mock(UnevenTicket.class);

        // mock constructors
        HashMap<String , MonetaryAmount> mock_Hash = (HashMap<String, MonetaryAmount>)Mockito.mock(HashMap.class);
        ArrayList<String> test_debtors = (ArrayList<String>)Mockito.mock(ArrayList.class);
        test_debtors.add("a");
        test_debtors.add("b");
        test_debtors.add("c");
        ArrayList<MonetaryAmount> test_debts = (ArrayList<MonetaryAmount>)Mockito.mock(ArrayList.class);
        test_debts.add(Money.of(10,"EUR"));
        test_debts.add(Money.of(20,"EUR"));
        test_debts.add(Money.of(30,"EUR"));
        mock_MDB.addEntry("a",mock_Hash);
        Ticket testtick = new Ticket("a", EventTickets.AirplaneTicket, Money.of(10,"EUR"),test_debtors, test_debts );
        mock_TDB.addEntry(0,testtick);
        MemberDatabase mock_mdb = MemberDatabase.getInstance();
        TicketDatabase mock_tdb = TicketDatabase.getInstance();


        TicketIterator iteratorUnderTest = new TicketIterator(mock_tdb);
        iteratorUnderTest.last();
        Assert.assertEquals(testtick, iteratorUnderTest.prev());


    }
    @Test
    public void last() throws Exception{ // test edgecases voor de functies in ticketiterator

        //create mock
        TicketDatabase mock_TDB = Mockito.mock(TicketDatabase.class);
        MemberDatabase mock_MDB = Mockito.mock(MemberDatabase.class);
        EvenTicket mock_ET = Mockito.mock(EvenTicket.class);
        UnevenTicket mock_UT = Mockito.mock(UnevenTicket.class);

        // mock constructors
        HashMap<String , MonetaryAmount> mock_Hash = (HashMap<String, MonetaryAmount>)Mockito.mock(HashMap.class);
        ArrayList<String> test_debtors = (ArrayList<String>)Mockito.mock(ArrayList.class);
        test_debtors.add("a");
        test_debtors.add("b");
        test_debtors.add("c");
        ArrayList<MonetaryAmount> test_debts = (ArrayList<MonetaryAmount>)Mockito.mock(ArrayList.class);
        test_debts.add(Money.of(10,"EUR"));
        test_debts.add(Money.of(20,"EUR"));
        test_debts.add(Money.of(30,"EUR"));
        mock_MDB.addEntry("a",mock_Hash);
        Ticket testtick = new Ticket("a", EventTickets.AirplaneTicket, Money.of(10,"EUR"),test_debtors, test_debts );
        Ticket testtick2 = new Ticket("a", EventTickets.AirplaneTicket, Money.of(20,"EUR"),test_debtors, test_debts );
        mock_TDB.addEntry(0,testtick);
        mock_TDB.addEntry(1,testtick2);
        MemberDatabase mock_mdb = MemberDatabase.getInstance();
        TicketDatabase mock_tdb = TicketDatabase.getInstance();


        TicketIterator iteratorUnderTest = new TicketIterator(mock_tdb);
        Assert.assertEquals(testtick2, iteratorUnderTest.last());


    }
}
