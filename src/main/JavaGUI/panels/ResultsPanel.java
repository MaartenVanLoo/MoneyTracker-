package panels;

import controller.TravelController;
import observers.Observer;
import view.Home;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class ResultsPanel extends JPanel implements Observer {
    private Home home;
    private TravelController travelController;

    private JComboBox members;
    private JLabel balance;
    private JList<String> toRecieve;
    private DefaultListModel<String> toRecieveList;
    private JList<String> toPay;
    private DefaultListModel<String> toPayList;

    public ResultsPanel(TravelController controller,Home home) {
        this.home = home;
        this.travelController=controller;

        this.members = new JComboBox(travelController.getMembers().toArray(new String[0]));
        this.toPayList = new DefaultListModel<>();
        this.toPay = new JList<>(toPayList);
        this.toRecieveList = new DefaultListModel<>();
        this.toRecieve = new JList<>(toRecieveList);

        //header panel
        JPanel p_ticketHeader = new JPanel();
        p_ticketHeader.setLayout(new BoxLayout(p_ticketHeader,BoxLayout.Y_AXIS));
        p_ticketHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        p_ticketHeader.add(this.members);

        //Body panel
        JPanel p_ticketBody = new JPanel();
        p_ticketBody.setLayout(new BoxLayout(p_ticketBody,BoxLayout.Y_AXIS));
        p_ticketBody.setAlignmentX(Component.CENTER_ALIGNMENT);
        p_ticketBody.add(toPay);
        p_ticketBody.add(toRecieve);


        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(p_ticketHeader);
        this.add(p_ticketBody);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt + " recieved");
    }
}
