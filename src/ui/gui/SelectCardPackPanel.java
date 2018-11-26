package ui.gui;

import model.CardPack;
import model.Inventory;
import ui.FlashCardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.Font.PLAIN;

public class SelectCardPackPanel extends JPanel {
    private JLabel title;
    private JList cardPackList;
    private DefaultListModel listModel;
    private Inventory inventory;

    // constructor
    public SelectCardPackPanel() {
        setBackground(Color.black);

        createTitleLabel();
        createCardPackJList();
        createSelectCardPackButton();
        createManageCardPackButton();
        createAddCardPackButton();
        createRemoveCardPackButton();
    }


    private void createTitleLabel() {
        title = new JLabel("\"Card Sets\"");
        title.setFont(new Font("San Serif", PLAIN, 20));
        title.setForeground(Color.white);
        title.setBackground(Color.black);
        title.setOpaque(true);
        add(title);
    }

    private void createCardPackJList() {
        inventory = new Inventory();
        inventory.readCardPacks("data/");
        String[] cardPackNames = inventory.getCardPackNames();
        Integer[] cardPackSizes = inventory.getCardPackSizes();

        // Make a mutable model for the JList
        listModel = new DefaultListModel();
        for (int i = 0; i < cardPackNames.length; i++) {
            listModel.addElement(cardPackNames[i] + " [" + cardPackSizes[i] + " cards]");
        }

        // Create the JList with the model
        cardPackList = new JList(listModel);
        add(cardPackList);
        cardPackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cardPackList.setLayoutOrientation(JList.VERTICAL);

        // Add the Scroller
        JScrollPane listScroller = new JScrollPane(cardPackList);
        add(listScroller);
        listScroller.setPreferredSize(new Dimension(450, 280));
    }

    private void createSelectCardPackButton() {
        JButton selectCardPackButton = new JButton("Start Flash Cards");

        selectCardPackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCardPanel();
            }
        });
        add(selectCardPackButton);
    }

    private void createManageCardPackButton() {
        JButton manageCardPackButton = new JButton("Manage Cards");

        manageCardPackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openManageCardPackPanel();
            }
        });
        add(manageCardPackButton);
    }

    private void createAddCardPackButton() {
        JButton addCardPackButton = new JButton("Add New Card Pack");

        addCardPackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCardPack();
            }
        });
        add(addCardPackButton);
    }

    private void createRemoveCardPackButton() {
        JButton removeCardPackButton = new JButton("Remove Card Pack");

        removeCardPackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCardPack();
            }
        });
        add(removeCardPackButton);
    }

    private void openCardPanel() {
        try {
            int index = cardPackList.getSelectedIndex();
            CardPanel cardPanel = new CardPanel(inventory.storage.get(index));
            FlashCardApp topFrame = (FlashCardApp) SwingUtilities.windowForComponent(this);

            topFrame.changePanel(cardPanel);
        } catch (NullPointerException ex) {

        }
    }

    private void openManageCardPackPanel() {
        try {
            int index = cardPackList.getSelectedIndex();
            ManageCardPackPanel mcpPanel = new ManageCardPackPanel(inventory.storage.get(index));
            FlashCardApp topFrame = (FlashCardApp) SwingUtilities.windowForComponent(this);

            topFrame.changePanel(mcpPanel);
        } catch (Exception ex) {

        }
    }

    // MODIFIES: this
    // EFFECTS: Creates new cardPack in inventory, save it to data file
    private void newCardPack() {
        String cardPackName = JOptionPane.showInputDialog(null, "Card Pack Name");

        // Only accept unique names
        for (String s : inventory.getCardPackNames()) {
            if (cardPackName.equals(s)) {
                JOptionPane.showMessageDialog(null, "Unique name required");
                return;
            }
        }

        // If unique name, add the CardPack
        CardPack newCardPack = new CardPack(cardPackName);

        inventory.storage.add(newCardPack);
        listModel.addElement(cardPackName);
        cardPackList.setModel(listModel);
        inventory.saveCardPacks("data/");
    }

    // REQUIRES: file names are unique
    // MODIFIES: this
    // EFFECTS: Remove cardPack in inventory,remove data file
    private void removeCardPack() {
        try {
            int index = cardPackList.getSelectedIndex();
            String name = inventory.storage.get(index).getName();

            inventory.storage.remove(index);
            listModel.remove(index);
            cardPackList.setModel(listModel);

            // List data files in data folder, find matching file and delete it
            File path = new File("data/");
            File[] fileList = path.listFiles();

            for (File f : fileList) {
                if (f.getName().equals(name + ".txt")) {
                    f.delete();
                }
            }
        } catch (Exception ex) {

        }
    }
}
