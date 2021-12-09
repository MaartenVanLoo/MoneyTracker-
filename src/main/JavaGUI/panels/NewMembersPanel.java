package panels;

import javax.swing.*;
import java.awt.*;

public class NewMembersPanel extends JPanel{
    private JTextField nextMember;
    private JButton addMember;
    private JList<String> members;

    public NewMembersPanel() {
        super();
        this.nextMember = new JTextField("test");
        this.nextMember.setPreferredSize(new Dimension(300,100));

        this.addMember = new JButton("test");
        this.addMember.setPreferredSize(new Dimension(100,50));

        this.members = new JList<String>();
        this.members.setAutoscrolls(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(nextMember);
        this.add(addMember);
        this.add(members);
    }
}
