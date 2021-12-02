package view;

import panels.NewMembersPanel;
import panels.NewTravelPanel;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    protected NewTravelPanel newTravel;
    protected NewMembersPanel newMembers;

    public Home() throws InterruptedException {
        super("MoneyTracker");
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.newTravel = new NewTravelPanel(this);
        this.newMembers = new NewMembersPanel();

        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        this.add(this.newTravel);
        this.setVisible(true);
    }

    public void changeToNewMembers(){
        this.removeAll();
        this.add(this.newMembers);
        System.out.println("Changed");
    }
}
