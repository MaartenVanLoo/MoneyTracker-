package view;

import Panels.NewTravelPanel;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    private NewTravelPanel newTravel;
    public Home(){
        super("MoneyTracker");
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.newTravel = new NewTravelPanel();

        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        this.add(this.newTravel);
        this.setVisible(true);
    }
}
