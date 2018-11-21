package model;

import java.util.ArrayList;

import static model.ObjectFileStore.storeObject;

public class Inventory {
    public ArrayList<CardPack> inventory;

    public Inventory() {
        this.inventory = null;
    }

    public void addCardPack(String name) {
        inventory.add(new CardPack(name));
    }

    public void saveCardPacks() {
        for (CardPack cp : inventory) {
            storeObject(cp, cp.getName());
        }
    }

    // TODO find method to read all files in one dir
    public void readCardPacks() {

    }

}
