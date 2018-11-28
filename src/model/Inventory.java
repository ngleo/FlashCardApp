package model;

import java.io.File;
import java.util.ArrayList;

import static tools.ObjectFileStore.readObjects;
import static tools.ObjectFileStore.storeObject;

/**
 * The Inventory class represents a collection of CardPacks.
 */
public class Inventory {
  private ArrayList<CardPack> storage;

  public Inventory() {
    this.storage = new ArrayList<>();
  }

  /**
   * Method returns a cardPack at a specific location in storage ArrayList.
   *
   * @param index is the position of cardPack to be retrieved.
   * @return cardPack object at the given index position.
   */
  public CardPack getCardPackByIndex(int index) {
    return storage.get(index);
  }

  /**
   * Method returns the names of all cardPacks in the inventory.
   *
   * @return an array of the names of all the cardPacks in the storage ArrayList.
   */
  public String[] getCardPackNames() {
    String[] names = new String[storage.size()];

    for (int i = 0; i < storage.size(); i++) {
      names[i] = storage.get(i).getName();
    }

    return names;
  }

  /**
   * Method returns the sizes of all cardPacks in the inventory.
   *
   * @return an array of the sizes of all the cardPacks in the storage ArrayList.
   */
  public Integer[] getCardPackSize() {
    Integer[] sizes = new Integer[storage.size()];

    for (int i = 0; i < storage.size(); i++) {
      sizes[i] = storage.get(i).getSize();
    }

    return sizes;
  }

  /**
   * Method adds a CardPack object to its storage ArrayList.
   *
   * @param cardPack is the CardPack object to be added to the storage.
   */
  public void addCardPack(CardPack cardPack) {
    storage.add(cardPack);
  }

  /**
   * Method removes a cardPack from its storage ArrayList by given index.
   *
   * @param index is the index position of the cardPack in the storage.
   */
  public void removeCardPackByIndex(int index) {
    storage.remove(index);
  }

  /**
   * Method stores all of the cardPack objects in the inventory by creating several txt files.
   *
   * @param dir is the path of preferred location to store the output txt files.
   */
  public void saveCardPacks(String dir) {
    for (CardPack cp : storage) {
      storeObject(cp, cp.getName(), dir);
    }
  }

  /**
   * Method reads all files in given path, creates CardPack objects from files in the path
   * which are not directories and adds these objects into the storage ArrayList.
   *
   * @param dir is the path of location from which files are read.
   */
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
