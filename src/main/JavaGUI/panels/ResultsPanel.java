package panels;

import controller.TravelController;
import observers.Observer;
import view.Home;

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
    private JList<String> toRecieve;
    private DefaultListModel<String> toRecieveList;
    private JList<String> toPay;
    private DefaultListModel<String> toPayList;

    public ResultsPanel(TravelController controller,Home home) {
        this.home = home;
        this.travelController=controller;

        this.members = new JComboBox(travelController.getMembers().toArray(new String[0]));
        this.balance = new JLabel("Balance");
        this.toPayList = new DefaultListModel<>();
        this.toPay = new JList<>(toPayList);
        this.toRecieveList = new DefaultListModel<>();
        this.toRecieve = new JList<>(toRecieveList);

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
        p_ticketBody.add(toPay);
        p_ticketBody.add(toRecieve);


        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(p_ticketHeader);
        this.add(p_ticketBody);

        addCreateActionlistener();
    }
    void addCreateActionlistener(){
        this.members.addActionListener(listener->{
            showResults();
        });
    }
    void showResults(){
        String name = (String)this.members.getSelectedItem();
        //get balance
        this.balance.setText(this.travelController.getBalance(name).toString());

        HashMap<String, MonetaryAmount> debts = this.travelController.getDebts(name);

        this.toPayList.clear();
        this.toRecieveList.clear();
        for (Map.Entry<String,MonetaryAmount> debt : debts.entrySet()){
            if (!(debt.getKey().equals(name))) {
                if (debt.getValue().isNegative()) {
                    //Get negative balances => 'To pay'
                    this.toPayList.addElement(String.format("%s: => %s", debt.getKey(), debt.getValue().negate().toString()));
                } else {
                    //Get positive balances => 'To recieve
                    this.toRecieveList.addElement(String.format("%s: => %s", debt.getKey(), debt.getValue().negate().toString()));
                }
            }
        }

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt + " recieved");
    }
}
