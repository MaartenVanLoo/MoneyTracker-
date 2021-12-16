package panels;

import controller.TravelController;
import view.Home;

import javax.swing.*;

public class ResultsPanel extends JPanel {
    private Home home;
    private TravelController travelController;

    public ResultsPanel(TravelController controller,Home home) {
        this.home = home;
        this.travelController=controller;
    }
}
