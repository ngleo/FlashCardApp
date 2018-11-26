package model;

import java.io.File;
import java.util.ArrayList;

import static model.ObjectFileStore.readObjects;
import static model.ObjectFileStore.storeObject;

public class Inventory {
    public ArrayList<CardPack> storage;

    public Inventory() {
        this.storage = new ArrayList<>();
    }

    // getter
    public String[] getCardPackNames() {
        String[] cardPackNames = new String[this.storage.size()];

        for (int i = 0; i < storage.size(); i++) {
            cardPackNames[i] = this.storage.get(i).getName();
        }

        return cardPackNames;
    }

    // TODO add exception
    public CardPack getCardPackByName(String name) {
        for (CardPack cp : this.storage) {
            if (cp.getName().equals(name)) {
                return cp;
            }
        }
        return null;
    }


    public void saveCardPacks(String dir) {
        for (CardPack cp : storage) {
            storeObject(cp, cp.getName(), dir);
        }
    }

    public void readCardPacks(String dir) {
        File path = new File(dir);
        File[] fileList = path.listFiles();

        if (fileList != null) {
            for (File f : fileList) {
                if (!f.isDirectory()) {
                    CardPack cp = readObjects(f);
                    this.storage.add(cp);
                }
            }
        }
    }

}
