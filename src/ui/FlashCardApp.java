package ui;

import ui.gui.SelectPanel;

import javax.swing.*;
import java.awt.*;

public class FlashCardApp extends JFrame {

    private SelectPanel selectPanel;

    public FlashCardApp() {
        super("Flashcard App");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        selectPanel = new SelectPanel();
        getContentPane().add(selectPanel);

        // init size and location
        setPreferredSize(new Dimension(500, 380));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void changePanel(JPanel panel) {
        getContentPane().removeAll();
        invalidate();
        getContentPane().add(panel);
        validate();
        pack();
    }





    public static void main(String[] args) {
        new FlashCardApp();
//        Inventory inv1 = new Inventory();
//        inv1.readCardPacks("data/");

        // Adding new data set
//        CardPack cp1 = new CardPack("Opposites");
//        Card c1 = new Card("useful", "полезный");
//        Card c2 = new Card("sad", "печальный");
//        Card c3 = new Card("explain", "объяснить");
//        Card c4 = new Card("first of all", "прежде всего");
//        Card c5 = new Card("obviously", "очевидно");
//        Card c6 = new Card("generally", "в целом");
//        Card c7 = new Card("follow", "соблюдать");
//        Card c8 = new Card("perfectly", "совершенно");
//
//        cp1.addCard(c1);
//        cp1.addCard(c2);
//        cp1.addCard(c3);
//        cp1.addCard(c4);
//        cp1.addCard(c5);
//        cp1.addCard(c6);
//        cp1.addCard(c7);
//        cp1.addCard(c8);
//
//        ObjectFileStore.storeObject(cp1, "Russian", "data/");


       // Adding new data set
//        CardPack cp1 = new CardPack("Days");
//        Card c1 = new Card("Monday", "1");
//        Card c2 = new Card("Tuesday", "2");
//        Card c3 = new Card("Wednesday", "3");
//        Card c4 = new Card("Thursday", "4");
//        Card c5 = new Card("Friday", "5");
//        Card c6 = new Card("Saturday", "6");
//        Card c7 = new Card("Sunday", "7");
//
//        cp1.addCard(c1);
//        cp1.addCard(c2);
//        cp1.addCard(c3);
//        cp1.addCard(c4);
//        cp1.addCard(c5);
//        cp1.addCard(c6);
//        cp1.addCard(c7);
//
//        ObjectFileStore.storeObject(cp1, "Days", "data/");

//        CardPack cp1 = inv1.storage.get(0);


//        // TODO take argument away, add argument in CardPanel
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGui();
//            }
//        });

    }
}
