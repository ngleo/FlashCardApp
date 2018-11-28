package tools;

import model.CardPack;

import java.io.*;

/**
 * The ObjectFileStore class allows writing objects to txt files and reading objects from files
 */
public class ObjectFileStore {
  /**
   * Method saves a CardPack object by creating an output file with given name in given directory
   *
   * @param cardPack is an instance of the cardPack class
   * @param name is the preferred name of the output file
   * @param dir is the preferred location of the output file
   */
  public static void storeObject(CardPack cardPack, String name, String dir) {
    OutputStream ops = null;
    ObjectOutputStream objOps = null;

    try {
      ops = new FileOutputStream(dir + name + ".txt");
      objOps = new ObjectOutputStream(ops);

      objOps.writeObject(cardPack);
      objOps.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (objOps != null) objOps.close();
      } catch (Exception ex) {
      }
    }
  }

  /**
   * Method reads a txt file and return its stored object (cardPack)
   *
   * @param name is the name of the txt file, including its path
   * @return the object (cardPack) stored in file
   */
  public static CardPack readObjects(File name) {
    InputStream fileIs = null;
    ObjectInputStream objIs = null;
    CardPack cardPack = null;

    try {
      fileIs = new FileInputStream(name);
      objIs = new ObjectInputStream(fileIs);
      cardPack = (CardPack) objIs.readObject();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (objIs != null) objIs.close();
      } catch (Exception ex) {
      }
    }
    return cardPack;
  }
}

