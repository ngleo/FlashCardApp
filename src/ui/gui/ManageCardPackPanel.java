package ui.gui;

import model.Card;
import model.CardPack;
import model.misc.TextPrompt;
import ui.FlashCardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;

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
        createAddCardbutton();
        createRemoveCardButton();
        createBackButton();
    }

    private void createTitleLabel() {
        title = new JLabel("\"" + cardPack.getName() + "\"");
        title.setFont(new Font("San Serif", PLAIN, 20));
        title.setForeground(Color.white);
        title.setBackground(Color.black);
        title.setOpaque(true);
        add(title);
    }

    public void createCardsJList() {
        Card[] cards = cardPack.cards.toArray(new Card[cardPack.getSize()]);

        // Make a mutable model for the JList
        listModel = new DefaultListModel();
        for (int i = 0; i < cardPack.cards.size(); i++) {
            listModel.addElement(cardPack.cards.get(i).front + " - " + cardPack.cards.get(i).back);
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

    public void createAddCardTextField() {
        cardFrontTextField = new JTextField(10);
        add(cardFrontTextField);
        cardBackTextField = new JTextField(10);
        add(cardBackTextField);

        setTextFieldPlaceHolder("Front", cardFrontTextField );
        setTextFieldPlaceHolder("Back", cardBackTextField );
    }

    public void createAddCardbutton() {
        JButton addCardButton = new JButton("Add Card");

        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCard();
            }
        });
        add(addCardButton);
    }

    private void createRemoveCardButton() {
        JButton removeCardButton = new JButton("Remove");
        removeCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCard();
            }
        });
        add(removeCardButton);
    }

    private void createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
        add(backButton);
    }

    public void setTextFieldPlaceHolder(String text, JTextField tf) {
        TextPrompt tp = new TextPrompt(text, tf);
        tp.setForeground( Color.GRAY );
        tp.changeAlpha(0.5f);
        tp.changeStyle(Font.BOLD);
    }

    // TODO add exception for empty fields
    // MODIFIES: this
    // EFFECTS: Creates new card in cardPack, save it to data file
    public void newCard() {
        String cardFront = cardFrontTextField.getText();
        String cardBack = cardBackTextField.getText();
        Card cardToAdd = new Card(cardFront, cardBack);

        // add card to cardPack, update JList, save cardPack
        cardPack.addCard(cardToAdd);
        listModel.addElement(cardToAdd.front + " - " + cardToAdd.back);
        cardList.setModel(listModel);
        cardPack.saveCardPack();

        // remove text from text fields
        cardFrontTextField.setText("");
        cardBackTextField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: Remove card from cardPack, update data file
    public void removeCard() {
        int index = cardList.getSelectedIndex();

        cardPack.cards.remove(index);
        listModel.remove(index);
        cardList.setModel(listModel);
        cardPack.saveCardPack();
    }

    // MODIFIES: this, FlashCardApp JFrame
    // EFFECTS: Go back to select Panel
    private void back() {
        SelectCardPackPanel selectCardPackPanel = new SelectCardPackPanel();
        FlashCardApp topFrame = (FlashCardApp) SwingUtilities.windowForComponent(this);

        topFrame.changePanel(selectCardPackPanel);
    }


}
