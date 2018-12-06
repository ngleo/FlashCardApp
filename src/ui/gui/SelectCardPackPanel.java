package ui.gui;

import model.CardPack;
import model.Inventory;
import ui.FlashCardApp;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static java.awt.Font.PLAIN;

/**
 * The SelectCardPackPanel class creates UI for the user to select, manage,
 * add and remove CardPack.
 */
public class SelectCardPackPanel extends JPanel {
  private static final String FILE_PATH = "resources/";

  private JList cardPackList;
  private DefaultListModel listModel;
  private Inventory inventory;

  public SelectCardPackPanel() {
    setBackground(Color.black);
    createTitleLabel();
    createCardPackJList();
    createButtons();
  }

  private void createTitleLabel() {
    JLabel title = new JLabel("\"Card Sets\"");

    title.setFont(new Font("San Serif", PLAIN, 20));
    title.setForeground(Color.white);
    title.setBackground(Color.black);
    title.setOpaque(true);
    add(title);
  }

  private void createCardPackJList() {
    inventory = new Inventory();

    inventory.readCardPacks(FILE_PATH);
    String[] cardPackNames = inventory.getCardPackNames();
    Integer[] cardPackSizes = inventory.getCardPackSize();

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

  private void createButtons() {
    createSelectCardPackButton();
    createManageCardPackButton();
    createAddCardPackButton();
    createRemoveCardPackButton();
  }

  private void createSelectCardPackButton() {
    JButton selectCardPackButton = new JButton("Start Flash Cards");

    selectCardPackButton.addActionListener(e -> openCardPanel());
    add(selectCardPackButton);
  }

  private void createManageCardPackButton() {
    JButton manageCardPackButton = new JButton("Manage Cards");

    manageCardPackButton.addActionListener(e -> openManageCardPackPanel());
    add(manageCardPackButton);
  }

  private void createAddCardPackButton() {
    JButton addCardPackButton = new JButton("Add New Card Pack");

    addCardPackButton.addActionListener(e -> newCardPack());
    add(addCardPackButton);
  }

  private void createRemoveCardPackButton() {
    JButton removeCardPackButton = new JButton("Remove Card Pack");

    removeCardPackButton.addActionListener(e -> removeCardPack());
    add(removeCardPackButton);
  }

  private void openCardPanel() {
    try {
      int index = cardPackList.getSelectedIndex();
      CardPanel cardPanel = new CardPanel(inventory.getCardPackByIndex(index));
      FlashCardApp topFrame = (FlashCardApp) SwingUtilities.windowForComponent(this);

      topFrame.changePanel(cardPanel);
    } catch (NullPointerException ex) {
      // It is fine if it is null, button just has no effect, or alert can be implemented
    }
  }

  private void openManageCardPackPanel() {
    try {
      int index = cardPackList.getSelectedIndex();
      ManageCardPackPanel mcpPanel = new ManageCardPackPanel(inventory.getCardPackByIndex(index));
      FlashCardApp topFrame = (FlashCardApp) SwingUtilities.windowForComponent(this);

      topFrame.changePanel(mcpPanel);
    } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
      // No action has to be taken.
    }
  }

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

    inventory.addCardPack(newCardPack);
    listModel.addElement(cardPackName);
    cardPackList.setModel(listModel);
    inventory.saveCardPacks(FILE_PATH);
  }

  private void removeCardPack() {
    try {
      int index = cardPackList.getSelectedIndex();
      String name = inventory.getCardPackByIndex(index).getName();

      inventory.removeCardPackByIndex(index);
      listModel.remove(index);
      cardPackList.setModel(listModel);

      // List data files in data folder, find matching file and delete it
      File path = new File(FILE_PATH);
      File[] fileList = path.listFiles();

      for (File f : fileList) {
        if (f.getName().equals(name + ".txt")) {
          f.delete();
        }
      }
    } catch (NullPointerException ex) {
        // It is fine if there is an exception.
    }
  }
}
