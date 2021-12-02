package view;

import javax.swing.*;

public class Home extends JFrame {
    private JTextField travelName;
    private JButton createTravel;
    public Home(){
        this.travelName = new JTextField("Enter travel name");
        this.createTravel = new JButton("Create travel");
    }
}
