package model;

import java.io.Serializable;

/**
 * The Card class represents a card in the flip card app which stores two strings,
 * front and back. The card can be flipped to display a string
 */
public class Card implements Serializable {
  private static final long serialVersionUID = 8585555395808288264L;

  public String front;
  public String back;
  public Boolean isFlipped;

  public Card(String front, String back) {
    this.front = front;
    this.back = back;
    isFlipped = false;
  }
}
