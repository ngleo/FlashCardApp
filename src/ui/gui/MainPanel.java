package ui.gui;

import model.CardPack;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private CardPanel cardPanel;

    // constructor
    public MainPanel(CardPack cardPack) {
        this.setBackground(Color.white);
        cardPanel = new CardPanel(cardPack);
        add(cardPanel);
    }

    // Create JFrame and instantiate JPanel and components
    public static void createAndShowGui(CardPack cardPack) {
        MainPanel mainPanel = new MainPanel(cardPack);

        JFrame frame = new JFrame("Flashcard App");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(680, 340));
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
