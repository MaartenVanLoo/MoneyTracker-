package view;

import controller.TravelController;
import panels.AddTicketPanel;
import panels.NewMembersPanel;
import panels.NewTravelPanel;
import panels.ResultsPanel;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    protected NewTravelPanel newTravel;
    protected NewMembersPanel newMembers;
    protected AddTicketPanel addTicketPanel;
    protected TravelController travelController;
    protected ResultsPanel resultPanel;

    public Home(TravelController travelController) throws InterruptedException {
        super("MoneyTracker");
        this.travelController = travelController;

        this.setSize(500,300);
        this.setMinimumSize(new Dimension(500,300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.newTravel = new NewTravelPanel(travelController,this);
        this.newMembers = new NewMembersPanel(travelController,this);

        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        this.add(this.newTravel);
        this.setVisible(true);
    }
    public void setPanel(String panel){
        this.getContentPane().removeAll();
        switch (panel){
            case "NewMemberPanel":
                this.getContentPane().add(this.newMembers);
                break;
            case "AddTicketPanel":
                this.addTicketPanel = new AddTicketPanel(travelController,this);
                this.getContentPane().add(this.addTicketPanel);
                break;
            case "ResultsPanel":
                this.resultPanel = new ResultsPanel(travelController,this);
                this.getContentPane().add(this.resultPanel);
                break;
        }
        this.revalidate();
        this.setVisible(true);
        this.pack();
        this.repaint();
        System.out.println("Changed");
    }
}
