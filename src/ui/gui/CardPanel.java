package ui.gui;

import model.Card;
import model.CardPack;
import model.RandomNumberGenerator;
import ui.FlashCardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;

public class CardPanel extends JPanel{
    private CardPack cardPack;
    private Card card;
    private JLabel wordDisplay;

    // constructor
    public CardPanel(CardPack cardPack) {
        this.cardPack = cardPack;
        this.card = null;

        createJLabel();
        createFlipButton();
        createNextButton();
        createBackButton();
    }

    // getters
    public JLabel getWordDisplay() {
        return wordDisplay;
    }

    private void createJLabel() {
        wordDisplay = new JLabel("Start Flashcards", SwingConstants.CENTER);
        wordDisplay.setFont(new Font("San Serif", PLAIN, 35));
        wordDisplay.setForeground(Color.white);
        wordDisplay.setBackground(Color.black);
        wordDisplay.setOpaque(true);
        wordDisplay.setPreferredSize(new Dimension(500,300));
        add(wordDisplay);
    }

    private void createFlipButton() {
        JButton flipButton = new JButton("Flip");
        flipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flip();
            }
        });
        add(flipButton);
    }

    private void createNextButton() {
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        add(nextButton);
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

    // REQUIRES: This.card should not be null
    // MODIFIES: this
    // EFFECTS: Flip the card from front to back or back to front
    private void flip() {
        if (this.card != null) {
               if (!this.card.isFlipped) {
                   wordDisplay.setText(this.card.back);
                   this.card.isFlipped = true;
               } else {
                   wordDisplay.setText(this.card.front);
                   this.card.isFlipped = false;
               }
        } else {
            next();
        }
    }

    // TODO THINK ABOUT TESTING OF PRIVATE METHOD
    // TODO DO YOU REALLY NEED AN EXCEPTION
    // REQUIRES: There must be a card in the Pack
    // MODIFIES: this
    // EFFECTS: Change wordDisplay from current card.front or null to next card.front
    private void next() {
            RandomNumberGenerator rng = new RandomNumberGenerator(cardPack.getSize());
            this.card = this.cardPack.cards.get(rng.getRand());
            wordDisplay.setText(this.card.front);
            this.card.isFlipped = false;
        }

    // MODIFIES: this, FlashCardApp JFrame
    // EFFECTS: Go back to select Panel
    private void back() {
        SelectPanel selectPanel = new SelectPanel();
        FlashCardApp topFrame = (FlashCardApp) SwingUtilities.windowForComponent(this);

        topFrame.changePanel(selectPanel);
    }

}
