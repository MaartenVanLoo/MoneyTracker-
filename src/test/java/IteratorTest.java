
import database.TicketIterator;
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
    public void iterate() throws Exception{ // test edgecases voor de functies in ticketiterator

        //create mock
        TicketDatabase mock_TDB = Mockito.mock(TicketDatabase.class);
        MemberDatabase mock_MDB = Mockito.mock(MemberDatabase.class);
        EvenTicket mock_ET = Mockito.mock(EvenTicket.class);
        UnevenTicket mock_UT = Mockito.mock(UnevenTicket.class);
        MemberDatabase mock_mdb = MemberDatabase.getInstance();
        TicketDatabase mock_tdb = TicketDatabase.getInstance();

        // mock constructors

        TicketIterator iteratorUnderTest = new TicketIterator(mock_tdb);
        iteratorUnderTest.end();
        iteratorUnderTest.first();
        iteratorUnderTest.next();
        iteratorUnderTest.prev();

    }
}
