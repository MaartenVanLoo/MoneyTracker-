package travel;


import database.MemberDatabase;
import database.TicketDatabase;

public class Travel {
    //private static Travel single_instace = null;
    protected String name;
    protected TicketDatabase ticketBase;
    protected MemberDatabase memsDatabase;

    public Travel(){}
    public Travel(String name, TicketDatabase ticketBase, MemberDatabase memsDatabase){
        this.name = name;
        this.ticketBase = ticketBase;
        this.memsDatabase = memsDatabase;

    }
    /* public static Travel getInstance(){
        if (single_instace == null){
            single_instace = new Travel();
        }
        return single_instace;
    }*/
    public String getName(){
        return this.name;
    }
    public TicketDatabase getTicketDatabase(){
         return this.ticketBase;
    }
    public MemberDatabase getMemberDatabase(){
        return this.memsDatabase;
    }
}

