package panels;

import controller.TravelController;
import tickets.EventTickets;
import jdk.internal.util.xml.impl.Pair;
import view.Home;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class AddTicketPanel extends JPanel {
    private Home home;
    private TravelController travelController;
    private JLabel title = new JLabel("TICKET");
    private JLabel name = new JLabel("Name");
    private JTextField ticketName;
    private JLabel cost = new JLabel("Total");
    private JTextField ticketCost;
    private JPanel memberOptions = new JPanel();
    private JComboBox ticketType;
    private EventTickets selectedTicket;
    private ArrayList<ArrayList<Object>> list = new ArrayList<>();
    private JButton submitTicket;


    public AddTicketPanel(TravelController controller,Home home) {
        this.home = home;
        this.travelController=controller;

        this.ticketName = new JTextField("d");
        this.ticketCost = new JTextField("d");

        this.ticketType = new JComboBox(EventTickets.values());
        this.selectedTicket = (EventTickets) this.ticketType.getSelectedItem();
        this.submitTicket = new JButton("submit new Ticket");

        //https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
        NumberFormat longFormat = NumberFormat.getInstance();
        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
        numberFormatter.setValueClass(Double.class); //optional, ensures you will always get a long value
        numberFormatter.setAllowsInvalid(false); //this is the key!!
        numberFormatter.setMinimum(0.0); //Optional

        ArrayList<String> members = this.travelController.getMembers();
        for (String name: members){
            //Create objects
            JCheckBox checkBox = new JCheckBox(name);
            int height = checkBox.getPreferredSize().height;
            checkBox.setSelected(true);
            checkBox.setPreferredSize(new Dimension(100,height));

            JFormattedTextField parts = new JFormattedTextField(numberFormatter);
            parts.setPreferredSize(new Dimension(40,height));
            parts.setEnabled(false);
            parts.setBackground(Color.lightGray);
            ArrayList<Object> component = new ArrayList<>(Arrays.asList(
                    (Object)(checkBox),(Object)(parts)
            ));
            list.add(component);

            JPanel member = new JPanel();
            member.add(checkBox);
            member.add(parts);
            member.setLayout(new BoxLayout(member,BoxLayout.X_AXIS));
            memberOptions.add(member);
        }
        memberOptions.setLayout(new BoxLayout(memberOptions,BoxLayout.Y_AXIS));
        JScrollPane scrollPaneMembers = new JScrollPane(memberOptions);
        scrollPaneMembers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneMembers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneMembers.setMaximumSize(new Dimension(3000, 300));
        scrollPaneMembers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        JPanel p_ticketName = new JPanel();
        p_ticketName.setLayout(new BoxLayout(p_ticketName,BoxLayout.X_AXIS));
        p_ticketName.add(name);
        p_ticketName.add(Box.createRigidArea(new Dimension(5, 0)));
        p_ticketName.add(ticketName);
        JPanel p_ticketValue = new JPanel();
        p_ticketValue.setLayout(new BoxLayout(p_ticketValue,BoxLayout.X_AXIS));
        p_ticketValue.add(cost);
        p_ticketValue.add(Box.createRigidArea(new Dimension(5, 0)));
        p_ticketValue.add(ticketCost);
        JPanel p_ticketHeader = new JPanel();
        p_ticketHeader.setLayout(new BoxLayout(p_ticketHeader,BoxLayout.Y_AXIS));
        p_ticketHeader.add(title);
        p_ticketHeader.add(Box.createRigidArea(new Dimension(0, 5)));
        p_ticketHeader.add(p_ticketName);
        p_ticketHeader.add(Box.createRigidArea(new Dimension(0, 5)));
        p_ticketHeader.add(p_ticketValue);
        p_ticketHeader.add(Box.createRigidArea(new Dimension(0, 5)));
        p_ticketHeader.add(ticketType);
        p_ticketHeader.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel p_ticketBody = new JPanel();
        p_ticketBody.setLayout(new BoxLayout(p_ticketBody,BoxLayout.Y_AXIS));
        p_ticketBody.add(scrollPaneMembers);
        p_ticketBody.add(Box.createRigidArea(new Dimension(0, 5)));
        p_ticketBody.add(submitTicket);
        p_ticketBody.add(Box.createRigidArea(new Dimension(0, 5)));

        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(p_ticketHeader);
        this.add(p_ticketBody);

        addCreateActionlistener();

        this.setVisible(true);
    }
    void addCreateActionlistener(){
        this.ticketType.addActionListener(listener-> {
            this.selectedTicket = (EventTickets) this.ticketType.getSelectedItem();
            if (this.travelController.isEven(this.selectedTicket)){
                disabelePartBoxes();
            }else{
                enablePartBoxes();
            }
        });
    }
    void disabelePartBoxes(){
        //https://stackoverflow.com/questions/21387856/how-to-enable-disable-textbox-in-java-depending-on-user-input
        for (ArrayList<Object> member: list){
            JFormattedTextField field = (JFormattedTextField)member.get(1);
            field.setEnabled(false);
            field.setBackground(Color.lightGray);
        }
    }
    void enablePartBoxes(){
        for (ArrayList<Object> member: list){
            JFormattedTextField field = (JFormattedTextField)member.get(1);
            field.setEnabled(true);
            field.setBackground(Color.white);
        }
    }
}
