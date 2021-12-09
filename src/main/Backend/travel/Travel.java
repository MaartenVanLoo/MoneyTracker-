package travel;


import database.MemberDatabase;
import database.TicketDatabase;

public class Travel {
    //private static Travel single_instace = null;
    protected String travelName;
    protected TicketDatabase ticketBase;
    protected MemberDatabase memsDatabase;

    public Travel(){
        this.travelName = "";
        this.ticketBase = TicketDatabase.getInstance();
        this.memsDatabase = MemberDatabase.getInstance();
    }
    public void TravelmetBases(String name, TicketDatabase ticketBase, MemberDatabase memsDatabase){
        this.travelName = name;
        this.ticketBase = ticketBase;
        this.memsDatabase = memsDatabase;
    }
    public Travel(String travelName){
        this.travelName = travelName;
        this.ticketBase = TicketDatabase.getInstance();
        this.memsDatabase = MemberDatabase.getInstance();

    }
    /* public static Travel getInstance(){
        if (single_instace == null){
            single_instace = new Travel();
        }
        return single_instace;
    }*/
    public String getTravelName(){
        return this.travelName;
    }
    public void setTravelName(String name){this.travelName = name;}
    public TicketDatabase getTicketDatabase(){
         return this.ticketBase;
    }
    public MemberDatabase getMemberDatabase(){
        return this.memsDatabase;
    }



}

