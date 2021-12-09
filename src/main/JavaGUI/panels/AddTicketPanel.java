package panels;

import controller.TravelController;
import database.DatabaseIterator;
import database.MemberIterator;
import jdk.internal.util.xml.impl.Pair;
import view.Home;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AddTicketPanel extends JPanel {
    private Home home;
    private TravelController travelController;
    private JTextField ticketCost;
    private JLabel label;
    private ArrayList<ArrayList<Object>> list = new ArrayList<>();


    public AddTicketPanel(TravelController controller,Home home) {
        this.home = home;
        this.travelController=controller;
        this.label = new JLabel("NEW TICKET");
        this.ticketCost = new JTextField("");

        ArrayList<String> members = this.travelController.getMembers();
        JPanel memberOptions = new JPanel();
        for (String name: members){
            //Create objects
            JCheckBox checkBox = new JCheckBox(name);
            checkBox.setSelected(true);

            ArrayList<Object> component = new ArrayList<>(Arrays.asList(
                    (Object)(checkBox)));
            list.add(component);
            //Add object to flow layout


            memberOptions.add(checkBox);
        }
        memberOptions.setLayout(new BoxLayout(memberOptions,BoxLayout.Y_AXIS));

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(label);
        this.add(ticketCost);
        this.add(memberOptions);

        this.setVisible(true);
    }
}
