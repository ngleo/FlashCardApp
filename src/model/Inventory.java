package model;

import java.io.File;
import java.util.ArrayList;

import static model.misc.ObjectFileStore.readObjects;
import static model.misc.ObjectFileStore.storeObject;

public class Inventory {
    public ArrayList<CardPack> storage;

    // constructor
    public Inventory() {
        this.storage = new ArrayList<>();
    }

    // getter
    public String[] getCardPackNames() {
        String[] cardPackNames = new String[storage.size()];

        for (int i = 0; i < storage.size(); i++) {
            cardPackNames[i] = storage.get(i).getName();
        }

        return cardPackNames;
    }

    public Integer[] getCardPackSizes() {
        Integer[] cardPackSizes = new Integer[storage.size()];

        for (int i = 0; i < storage.size(); i++) {
            cardPackSizes[i] = storage.get(i).getSize();
        }

        return cardPackSizes;
    }

    public CardPack getCardPackByName(String name) {
        for (CardPack cp : storage) {
            if (cp.getName().equals(name)) {
                return cp;
            }
        }
        return null;
    }

    // MODIFIES: Data file
    // EFFECT: Save all CardPack objects to txt files
    public void saveCardPacks(String dir) {
        for (CardPack cp : storage) {
            storeObject(cp, cp.getName(), dir);
        }
    }

    // MODIFIES: this
    // EFFECT: Read all data files and add CardPack objects to this.storage
    public void readCardPacks(String dir) {
        File path = new File(dir);
        File[] fileList = path.listFiles();

        if (fileList != null) {
            for (File f : fileList) {
                if (!f.isDirectory()) {
                    CardPack cp = readObjects(f);
                    storage.add(cp);
                }
            }
        }
    }

}
