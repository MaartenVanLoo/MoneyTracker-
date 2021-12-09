package panels;

import controller.TravelController;
import view.Home;

import javax.swing.*;
import java.awt.*;

public class NewMembersPanel extends JPanel{
    private Home home;
    private JTextField nextMember;
    private JButton addMember;
    private JButton submit;
    private JList<String> members;
    private TravelController travelController;

    public NewMembersPanel(TravelController controller, JFrame home) {
        super();
        this.travelController = controller;

        this.home = (Home)home;
        this.nextMember = new JTextField("test");
        this.nextMember.setPreferredSize(new Dimension(300,100));

        this.addMember = new JButton("test");
        this.addMember.setPreferredSize(new Dimension(100,50));

        this.submit = new JButton("submit");
        this.submit.setPreferredSize(new Dimension(100,50));

        this.members = new JList<String>();
        this.members.setAutoscrolls(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(nextMember);
        this.add(addMember);
        this.add(submit);
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
}
