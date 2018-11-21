package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CardPack implements Serializable {
    private String name;
    public ArrayList<Card> cards;

    // constructor
    public CardPack(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    // getters
    public String getName() {
        return name;
    }

    public int getSize() { return cards.size();}

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }


}
