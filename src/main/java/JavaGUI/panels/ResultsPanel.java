package JavaGUI.panels;

import controller.TravelController;
import observers.Observer;
import JavaGUI.view.Home;

import javax.money.MonetaryAmount;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

public class ResultsPanel extends JPanel implements Observer {
    private Home home;
    private TravelController travelController;

    private JComboBox members;
    private JLabel balance;
    private JList<String> toReceive;
    private DefaultListModel<String> toReceiveList;
    private JList<String> toPay;
    private DefaultListModel<String> toPayList;

    public ResultsPanel(TravelController controller,Home home) {
        this.home = home;
        this.travelController=controller;

        this.members = new JComboBox(travelController.getMembers().toArray(new String[0]));
        this.balance = new JLabel("Balance");
        this.toPayList = new DefaultListModel<>();
        this.toPay = new JList<>(toPayList);
        this.toReceiveList = new DefaultListModel<>();
        this.toReceive = new JList<>(toReceiveList);

        //header panel
        JPanel p_ticketHeader = new JPanel();
        p_ticketHeader.setLayout(new BoxLayout(p_ticketHeader,BoxLayout.Y_AXIS));
        p_ticketHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        p_ticketHeader.add(this.members);
        p_ticketHeader.add(Box.createRigidArea(new Dimension(0, 5)));
        p_ticketHeader.add(this.balance);
        p_ticketHeader.add(Box.createRigidArea(new Dimension(0, 5)));


        //Body panel
        JPanel p_ticketBody = new JPanel();
        p_ticketBody.setLayout(new BoxLayout(p_ticketBody,BoxLayout.Y_AXIS));
        p_ticketBody.setAlignmentX(Component.CENTER_ALIGNMENT);
        p_ticketBody.add(new JLabel("You have to pay:"));
        p_ticketBody.add(toPay);
        p_ticketBody.add(new JLabel("You have to receive:"));
        p_ticketBody.add(toReceive);


        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(p_ticketHeader);
        this.add(p_ticketBody);

        addCreateActionlistener();
        showResults();
    }
    void addCreateActionlistener(){
        this.members.addActionListener(listener->{
            showResults();
        });
    }
    void showResults(){
        String name = (String)this.members.getSelectedItem();
        //get balance
        this.balance.setText("Balance: " + this.travelController.getBalance(name).toString());

        HashMap<String, MonetaryAmount> debts = this.travelController.getDebts(name);

        this.toPayList.clear();
        this.toReceiveList.clear();
        for (Map.Entry<String,MonetaryAmount> debt : debts.entrySet()){
            if (!(debt.getKey().equals(name))) {
                if (debt.getValue().isNegative()) {
                    //Get negative balances => 'To pay'
                    this.toPayList.addElement(String.format("%s: => %s", debt.getKey(), debt.getValue().negate().toString()));
                } else {
                    //Get positive balances => 'To receive
                    this.toReceiveList.addElement(String.format("%s: => %s", debt.getKey(), debt.getValue().toString()));
                }
            }
        }

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt + " received");
    }
}
