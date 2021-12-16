package panels;

import controller.TravelController;
import observers.Observer;
import view.Home;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class ResultsPanel extends JPanel implements Observer {
    private Home home;
    private TravelController travelController;

    public ResultsPanel(TravelController controller,Home home) {
        this.home = home;
        this.travelController=controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt + " recieved");
    }
}
