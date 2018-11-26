package ui.gui;

import model.Inventory;
import ui.FlashCardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPanel extends JPanel {
    private JList nameList;
    private Inventory inventory;

    // constructor
    public SelectPanel() {
        createJList();
        setBackground(Color.black);
    }

    private void createJList() {
        inventory = new Inventory();
        inventory.readCardPacks("data/");
        String[] cardPackNames = inventory.getCardPackNames();

        // Make a mutable model for the JList
        DefaultListModel listModel = new DefaultListModel();
        for (String name : cardPackNames) {
            listModel.addElement(name);
        }

        // Create the JList with the model
        nameList = new JList(listModel);
        add(nameList);
        nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nameList.setLayoutOrientation(JList.VERTICAL);
        nameList.setVisibleRowCount(10);

        // Add the Scroller
        JScrollPane listScroller = new JScrollPane(nameList);
        add(listScroller);
        listScroller.setPreferredSize(new Dimension(290, 200));

        createSelecCardPacktButton();
    }

    private void createSelecCardPacktButton() {
        JButton selectCardPackButton = new JButton("Select Card Pack");

        selectCardPackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                CardPanel cardPanel = new CardPanel(inventory.getCardPackByName(element));
                openCardPanel();
            }
        });
        add(selectCardPackButton);
    }

    private void openCardPanel() {
        try {
            String selected = this.nameList.getSelectedValue().toString();
            CardPanel cardPanel = new CardPanel(this.inventory.getCardPackByName(selected));
            FlashCardApp topFrame = (FlashCardApp) SwingUtilities.windowForComponent(this);

            topFrame.changePanel(cardPanel);
        } catch (NullPointerException ex) {

        }

    }
}
