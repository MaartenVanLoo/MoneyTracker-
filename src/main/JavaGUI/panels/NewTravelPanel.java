package panels;

import controller.TravelController;
import view.Home;

import javax.swing.*;
import java.awt.*;

public class NewTravelPanel extends JPanel {
    private JTextField travelName;
    private JButton createTravel;
    private Home home;
    private TravelController travelController;
    public NewTravelPanel(TravelController controller, JFrame home) {
        super();
        this.travelController = controller;
        this.home = (Home)home;
        this.travelName = new JTextField("Enter travel name");
        this.travelName.setPreferredSize(new Dimension(300,100));
        this.createTravel = new JButton("Create travel");
        this.createTravel.setPreferredSize(new Dimension(100,50));
        this.setLayout(new BorderLayout(50,20));
        this.add(this.travelName,BorderLayout.NORTH);
        this.add(this.createTravel,BorderLayout.SOUTH);

        addCreateActionlistener();
    }

    public void addCreateActionlistener(){
        this.createTravel.addActionListener(listener->{
            this.travelController.newTravel(this.travelName.getText());
            System.out.println("New Travel: " + this.travelController.getTravelName());
            this.home.setPanel("NewMemberPanel");
            });
    }
}
