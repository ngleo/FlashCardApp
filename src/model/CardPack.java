package model;

import tools.ObjectFileStore;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The CardPack class represents a collection of Card objects.
 */
public class CardPack implements Serializable {
  private static final long serialVersionUID = -4935823706970464535L;

  private String name;
  private ArrayList<Card> cards;

  public CardPack(String name) {
    this.name = name;
    cards = new ArrayList<>();
  }

  /**
   * Method returns the name of this cardPack.
   *
   * @return the name of this cardPack.
   */
  public String getName() {
    return name;
  }

  /**
   * Method returns the size of this cardPack.
   *
   * @return the number of Cards in the cards ArrayList.
   */
  public int getSize() {
    return cards.size();
  }

  /**
   * Method returns the cards in this cardPack in an array.
   *
   * @return cards as an array.
   */
  public Card[] getCardsArray() {
    return cards.toArray(new Card[this.getSize()]);
  }

  /**
   * Method returns the card at given position in the cards ArrayList.
   *
   * @param index is the position of the card to be retrieved.
   * @return the card object.
   */
  public Card getCardByIndex(int index) {
    return cards.get(index);
  }

  /**
   * Method adds a Card object to its cards ArrayList.
   *
   * @param card is the Card object to be added to the CardPack.
   */
  public void addCard(Card card) {
    cards.add(card);
  }

  /**
   * Method removes a card from its cards ArrayList by given index.
   *
   * @param index is the index position of the card in the cardPack.
   */
  public void removeCardByIndex(int index) {
    cards.remove(index);
  }

  /**
   * Method creates an output txt file to store the CardPack object.
   *
   * @param dir is the preferred location of the CardPack object.
   */
  public void saveCardPack(String dir) {
    ObjectFileStore.storeObject(this, this.getName(), dir);
  }
}
