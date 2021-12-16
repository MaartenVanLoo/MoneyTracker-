package panels;

import controller.TravelController;
import org.javamoney.moneta.Money;
import tickets.EventTickets;
import jdk.internal.util.xml.impl.Pair;
import tickets.Ticket;
import view.Home;

import javax.money.MonetaryAmount;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class AddTicketPanel extends JPanel {
    private Home home;
    private TravelController travelController;
    private JLabel title = new JLabel("TICKET");
    private JLabel name = new JLabel("Name");
    private JTextField ticketName;
    private JLabel cost = new JLabel("Total  €");
    private JFormattedTextField ticketCost;
    private JPanel memberOptions = new JPanel();
    private JComboBox ticketType;
    private EventTickets selectedTicket;
    private ArrayList<ArrayList<Object>> list = new ArrayList<>();
    private JButton submitTicket;


    public AddTicketPanel(TravelController controller,Home home) {
        this.home = home;
        this.travelController=controller;

        //https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
        NumberFormatter numberFormatter = new NumberFormatter(NumberFormat.getInstance());
        numberFormatter.setValueClass(Double.class); //optional, ensures you will always get a double value
        numberFormatter.setAllowsInvalid(false); //this is the key!!
        numberFormatter.setMinimum(0.0); //Optional

        NumberFormatter currencyFormatter = new NumberFormatter(NumberFormat.getCurrencyInstance(Locale.GERMANY));
        currencyFormatter.setAllowsInvalid(false); //this is the key!!
        currencyFormatter.setMinimum(0.0); //Optional

        this.ticketName = new JTextField("New Ticket");
        this.ticketCost = new JFormattedTextField(numberFormatter);

        this.ticketType = new JComboBox(EventTickets.values());
        this.selectedTicket = (EventTickets) this.ticketType.getSelectedItem();
        this.submitTicket = new JButton("submit new Ticket");



        ArrayList<String> members = this.travelController.getMembers();
        for (String name: members){
            //Create objects
            JCheckBox checkBox = new JCheckBox(name);
            int height = checkBox.getPreferredSize().height;
            checkBox.setSelected(true);
            checkBox.setPreferredSize(new Dimension(100,height));

            JLabel currency = new JLabel("€");
            currency.setPreferredSize(new Dimension(10,height));
            currency.setHorizontalAlignment(JLabel.LEFT);

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
            member.add(currency);
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
        p_ticketHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        p_ticketBody.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        this.submitTicket.addActionListener(listener->{
            Ticket ticket = travelController.makeTicket(selectedTicket);
            //get amount
            double amount = Double.parseDouble(ticketCost.getText());
            ticket.setTotalAmount(Money.of(amount,"EUR"));
            System.out.println("New Ticket: " + amount);

            //loop over all members to ticket
            int memberCount = 0;
            double TotalParts = 0;
            for (ArrayList<Object> member:list){
                if (((JCheckBox)(member.get(0))).isSelected()){
                    memberCount++;
                    TotalParts+= Double.parseDouble(((JFormattedTextField)member.get(1)).getText());
                }
            }
            for (ArrayList<Object> member:list) {
                if (((JCheckBox)(member.get(0))).isSelected()) {
                    String name = ((JCheckBox)(member.get(0))).getText();
                    MonetaryAmount debt;
                    if (this.travelController.isEven(this.selectedTicket)) {
                        //even ticket
                       debt = Money.of(amount/(double)memberCount,"EUR");
                    }else{
                        double parts = Double.parseDouble(((JFormattedTextField)member.get(1)).getText());
                        debt = Money.of(amount * parts/TotalParts,"EUR");
                    }
                    ticket.addDeptor(name,debt);
                    System.out.println(String.format("Name: %8s, debt: ",name)+debt);
                }
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
