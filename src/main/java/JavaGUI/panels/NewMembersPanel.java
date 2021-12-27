package JavaGUI.panels;

import controller.TravelController;
import observers.Observer;
import JavaGUI.view.Home;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class NewMembersPanel extends JPanel implements Observer {
    private Home home;
    private JTextField nextMember;
    private JButton addMember;
    private JButton submit;
    private JList<String> members;
    private DefaultListModel<String> memberList;
    private TravelController travelController;

    public NewMembersPanel(TravelController controller, JFrame home) {
        super();
        this.travelController = controller;

        this.home = (Home)home;
        this.nextMember = new JTextField("Member Name");
        this.nextMember.setPreferredSize(new Dimension(300,100));

        this.addMember = new JButton("Add member");
        this.addMember.setPreferredSize(new Dimension(100,50));

        this.submit = new JButton("submit");
        this.submit.setPreferredSize(new Dimension(100,50));

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
        buttons.add(addMember);
        buttons.add(Box.createRigidArea(new Dimension(5, 0)));
        buttons.add(submit);

        this.memberList = new DefaultListModel<>();
        this.members = new JList<>(this.memberList);
        this.members.setAutoscrolls(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(nextMember);
        this.add(buttons);
        this.add(members);

        addCreateActionlistener();
    }
    public void addCreateActionlistener(){
        this.addMember.addActionListener(listener->{
            String name = this.nextMember.getText();
            this.travelController.addMember(name);
            this.nextMember.setText("");
            System.out.println("New member: " + name);
        });

        this.submit.addActionListener(listener->{
            this.home.setPanel("AddTicketPanel");
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt + " recieved");
        memberList.addElement((String)evt.getOldValue());
    }
}
