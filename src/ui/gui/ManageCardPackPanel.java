package ui.gui;

import model.Card;
import model.CardPack;
import tools.TextPrompt;
import ui.FlashCardApp;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.PLAIN;

/**
 * The ManageCardPackPanel class creates a Panel for viewing, adding and removing
 * cards in a cardPack,
 */
public class ManageCardPackPanel extends JPanel {
  private JLabel title;
  private JList cardList;
  private DefaultListModel listModel;
  private CardPack cardPack;
  private JTextField cardFrontTextField;
  private JTextField cardBackTextField;

  public ManageCardPackPanel(CardPack cardPack) {
    this.cardPack = cardPack;

    setBackground(Color.black);
    createTitleLabel();
    createCardsJList();
    createAddCardTextField();
    createButtons();
  }

  private void createTitleLabel() {
    title = new JLabel("\"" + cardPack.getName() + "\"");
    title.setFont(new Font("San Serif", PLAIN, 20));
    title.setForeground(Color.white);
    title.setBackground(Color.black);
    title.setOpaque(true);
    add(title);
  }

  private void createCardsJList() {
    Card[] cards = cardPack.getCardsArray();

    // Make a mutable model for the JList
    listModel = new DefaultListModel();
    for (int i = 0; i < cardPack.getSize(); i++) {
      listModel.addElement(cards[i].front + " - " + cards[i].back);
    }

    // Create the JList with the model
    cardList = new JList(listModel);
    add(cardList);
    cardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    cardList.setLayoutOrientation(JList.VERTICAL);

    // Add the Scroller
    JScrollPane listScroller = new JScrollPane(cardList);
    add(listScroller);
    listScroller.setPreferredSize(new Dimension(450, 240));
  }

  private void createAddCardTextField() {
    cardFrontTextField = new JTextField(10);
    add(cardFrontTextField);
    cardBackTextField = new JTextField(10);
    add(cardBackTextField);

    setTextFieldPlaceHolder("Front", cardFrontTextField);
    setTextFieldPlaceHolder("Back", cardBackTextField);
  }

  private void setTextFieldPlaceHolder(String text, JTextField tf) {
    TextPrompt tp = new TextPrompt(text, tf);
    tp.setForeground(Color.GRAY);
    tp.changeAlpha(0.5f);
    tp.changeStyle(Font.BOLD);
  }

  private void createButtons() {
    createAddCardButton();
    createRemoveCardButton();
    createBackButton();
  }

  private void createAddCardButton() {
    JButton addCardButton = new JButton("Add Card");

    addCardButton.addActionListener(e -> newCard());
    add(addCardButton);
  }

  private void createRemoveCardButton() {
    JButton removeCardButton = new JButton("Remove");
    removeCardButton.addActionListener(e -> removeCard());
    add(removeCardButton);
  }

  private void createBackButton() {
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> back());
    add(backButton);
  }

  // TODO add exception for empty fields
  private void newCard() {
    String cardFront = cardFrontTextField.getText();
    String cardBack = cardBackTextField.getText();
    Card cardToAdd = new Card(cardFront, cardBack);

    // add card to cardPack, update JList, save cardPack
    cardPack.addCard(cardToAdd);
    listModel.addElement(cardToAdd.front + " - " + cardToAdd.back);
    cardList.setModel(listModel);
    cardPack.saveCardPack("resources/");

    // remove text from text fields
    cardFrontTextField.setText("");
    cardBackTextField.setText("");
  }

  private void removeCard() {
    int index = cardList.getSelectedIndex();

    cardPack.removeCardByIndex(index);
    listModel.remove(index);
    cardList.setModel(listModel);
    cardPack.saveCardPack("resources/");
  }

  private void back() {
    SelectCardPackPanel selectCardPackPanel = new SelectCardPackPanel();
    FlashCardApp topFrame = (FlashCardApp) SwingUtilities.windowForComponent(this);

    topFrame.changePanel(selectCardPackPanel);
  }


}
