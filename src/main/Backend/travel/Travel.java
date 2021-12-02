package travel;


import database.MemberDatabase;
import database.TicketDatabase;

public class Travel {
    protected String name;
    protected TicketDatabase ticketBase;
    protected MemberDatabase memsDatabase;

    public Travel(){};
    public Travel(String name, TicketDatabase ticketBase, MemberDatabase memsDatabase){
        this.name = name;
        this.ticketBase = ticketBase;
        this.memsDatabase = memsDatabase;

    }
}

