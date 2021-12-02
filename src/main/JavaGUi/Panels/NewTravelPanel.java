package Panels;

import javax.swing.*;
import java.awt.*;

public class NewTravelPanel extends JPanel {
    private JTextField travelName;
    private JButton createTravel;

    public NewTravelPanel() {
        super();
        this.travelName = new JTextField("Enter travel name");
        this.travelName.setPreferredSize(new Dimension(300,100));
        this.createTravel = new JButton("Create travel");
        this.createTravel.setPreferredSize(new Dimension(100,50));
        this.setLayout(new BorderLayout(50,20));
        this.add(this.travelName,BorderLayout.NORTH);
        this.add(this.createTravel,BorderLayout.SOUTH);
    }
}
