package model;

import java.io.File;
import java.util.ArrayList;

import static model.ObjectFileStore.storeObject;
import static model.ObjectFileStore.readObjects;

public class Inventory {
    public ArrayList<CardPack> storage;

    public Inventory() {
        this.storage = new ArrayList<>();
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
