package ui.gui;

import model.Card;
import model.CardPack;
import tools.RandomNumberGenerator;
import ui.FlashCardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;

public class CardPanel extends JPanel {
  private JLabel title;
  private CardPack cardPack;
  private Card card;
  private JLabel wordDisplay;

  // constructor
  public CardPanel(CardPack cardPack) {
    this.cardPack = cardPack;
    this.card = null;

    createTitleLabel();
    createWordDisplayLabel();
    createFlipButton();
    createNextButton();
    createBackButton();
  }

  // getters
  public JLabel getWordDisplay() {
    return wordDisplay;
  }

  private void createTitleLabel() {
    title = new JLabel("\"" + cardPack.getName() + "\"");
    title.setFont(new Font("San Serif", PLAIN, 20));
    title.setForeground(Color.BLACK);
    title.setOpaque(true);
    add(title);
  }

  private void createWordDisplayLabel() {
    wordDisplay = new JLabel("Start Flashcards", SwingConstants.CENTER);
    wordDisplay.setFont(new Font("San Serif", PLAIN, 35));
    wordDisplay.setForeground(Color.white);
    wordDisplay.setBackground(Color.black);
    wordDisplay.setOpaque(true);
    wordDisplay.setPreferredSize(new Dimension(500, 300));
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
    if (cardPack.getSize() > 0) {
      if (card != null) {
        if (!card.isFlipped) {
          wordDisplay.setText(card.back);
          card.isFlipped = true;
        } else {
          wordDisplay.setText(card.front);
          card.isFlipped = false;
        }
      } else {
        next();
      }
    }
  }

  // REQUIRES: There must be a card in the Pack
  // MODIFIES: this
  // EFFECTS: Change wordDisplay from current card.front or null to next card.front
  private void next() {
    if (cardPack.getSize() > 0) {
      int randomNumber = RandomNumberGenerator.getRand(cardPack.getSize());
      card = cardPack.getCardByIndex(randomNumber);
      wordDisplay.setText(card.front);
      card.isFlipped = false;
    }
  }

  // MODIFIES: this, FlashCardApp JFrame
  // EFFECTS: Go back to select Panel
  private void back() {
    SelectCardPackPanel selectCardPackPanel = new SelectCardPackPanel();
    FlashCardApp topFrame = (FlashCardApp) SwingUtilities.windowForComponent(this);

    topFrame.changePanel(selectCardPackPanel);
  }

}
