package model;

import model.misc.ObjectFileStore;

import java.io.Serializable;
import java.util.ArrayList;

public class CardPack implements Serializable {
    private static final long serialVersionUID = -4935823706970464535L;

    private String name;
    public ArrayList<Card> cards;

    // constructor
    public CardPack(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    // getters
    public String getName() {
        return this.name;
    }

    public int getSize() { return this.cards.size();}

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    // MODIFIES: Data files
    // EFFECTS: Save card pack to file
    public void saveCardPack() {
        ObjectFileStore.storeObject(this, this.getName(), "data/");
    }

}
