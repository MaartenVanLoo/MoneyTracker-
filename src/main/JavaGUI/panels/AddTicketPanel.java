package panels;

import controller.TravelController;
import database.DatabaseIterator;
import database.MemberIterator;
import jdk.internal.util.xml.impl.Pair;
import view.Home;

import javax.swing.*;
import java.util.ArrayList;

public class AddTicketPanel extends JPanel {
    private Home home;
    private TravelController travelController;

    //private ArrayList<ArrayList<JCheckBox,JLabel>> list = null;

    public AddTicketPanel(TravelController controller,Home home) {
        this.home = home;
        this.travelController=controller;

        DatabaseIterator members = this.travelController.getMembers();
        while (!members.end()){
            //ArrayList<JCheckBox, JLabel> component = new ArrayList<>(new JCheckBox(), new JLabel());
            //list.add(new ArrayList<JCheckBox>());
        }
    }
}
