package JavaGUI.view;

import controller.TravelController;
import JavaGUI.panels.AddTicketPanel;
import JavaGUI.panels.NewMembersPanel;
import JavaGUI.panels.NewTravelPanel;
import JavaGUI.panels.ResultsPanel;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    protected NewTravelPanel newTravel;
    protected NewMembersPanel newMembers;
    protected AddTicketPanel addTicketPanel;
    protected ResultsPanel resultPanel;
    protected TravelController travelController;


    public Home(TravelController travelController) throws InterruptedException {
        super("MoneyTracker");
        this.travelController = travelController;

        this.setSize(500,300);
        this.setMinimumSize(new Dimension(500,300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.newTravel = new NewTravelPanel(travelController,this);

        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        this.add(this.newTravel);
        this.setVisible(true);
    }
    public void setPanel(String panel){
        this.getContentPane().removeAll();
        switch (panel){
            case "NewMemberPanel":
                this.newMembers = new NewMembersPanel(travelController,this);
                this.getContentPane().add(this.newMembers);
                this.travelController.removeObserver(this.newTravel);
                this.travelController.addObserver(this.newMembers);
                this.newTravel = null;
                break;
            case "AddTicketPanel":
                this.addTicketPanel = new AddTicketPanel(travelController,this);
                this.getContentPane().add(this.addTicketPanel);
                this.travelController.removeObserver(this.newMembers);
                this.travelController.addObserver(this.addTicketPanel);
                this.newMembers = null;
                break;
            case "ResultsPanel":
                this.resultPanel = new ResultsPanel(travelController,this);
                this.getContentPane().add(this.resultPanel);
                this.travelController.removeObserver(this.addTicketPanel);
                this.travelController.addObserver(this.resultPanel);
                this.addTicketPanel = null;
                break;
        }
        this.revalidate();
        this.setVisible(true);
        this.pack();
        this.repaint();
        System.out.println("Changed");
    }
}
