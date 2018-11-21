package ui;

import model.CardPack;
import model.ObjectFileStore;

import javax.swing.*;

import static ui.gui.MainPanel.createAndShowGui;

public class Main {

    public static void main(String[] args) {
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
//        ObjectFileStore.storeObject(cp1);
        CardPack cp1 = ObjectFileStore.readObjects();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui(cp1);
            }
        });

        //TODO JFrame opened from run WITH main JPanel-> event listeners to change JPanel view

        //TODO on closing the app, inventory.saveCardPacks
    }
}
