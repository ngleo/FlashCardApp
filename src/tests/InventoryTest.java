package tests;

import model.Card;
import model.CardPack;
import model.Inventory;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit testing for Inventory Class
 */
public class InventoryTest {
  public static final String FILE_PATH = "resources/testResources/";

  @Before
  public void setup() {
    // set up inventory, cardPacks, cards
    Card c1 = new Card("1f", "1b");
    Card c2 = new Card("2f", "2b");
    Card c3 = new Card("3f", "3b");
    Card c4 = new Card("4f", "4b");
    Card c5 = new Card("5f", "5b");
    Card c6 = new Card("6f", "6b");

    CardPack cp1 = new CardPack("cp1");
    CardPack cp2 = new CardPack("cp2");
    CardPack cp3 = new CardPack("cp3");
    cp1.addCard(c1);
    cp1.addCard(c2);
    cp2.addCard(c3);
    cp2.addCard(c4);
    cp3.addCard(c5);
    cp3.addCard(c6);

    Inventory inv1 = new Inventory();
    inv1.addCardPack(cp1);
    inv1.addCardPack(cp2);
    inv1.addCardPack(cp3);
    inv1.saveCardPacks(FILE_PATH);
  }

  @Test
  public void testSaveCardPacks() {
    // create two lists of expected and actual results
    ArrayList<String> actual = new ArrayList<>();
    actual.add("resources/testResources/cp2.txt");
    actual.add("resources/testResources/cp3.txt");
    actual.add("resources/testResources/cp1.txt");

    ArrayList<String> expected = new ArrayList<>();

    // check if two lists are the same in String
    File path = new File(FILE_PATH);
    File[] fileList = path.listFiles();

    try {
      for (File f : fileList) {
        expected.add(f.toString());
      }
      assertEquals(expected, actual);
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      // remove files
      for (File f : fileList) {
        f.delete();
      }
    }
  }

  @Test
  public void testReadObjects() {
    Inventory inv2 = new Inventory();

    File path = new File(FILE_PATH);
    File[] fileList = path.listFiles();

    // read CardPacks from testResources path to inv2
    inv2.readCardPacks(path.toString());

    // print out all CardPack Names
    String[] cardPackList = inv2.getCardPackNames();

    for (String name : cardPackList) {
      System.out.println(name);
    }

    // remove files
    for (File f : fileList) {
      f.delete();
    }
  }
}
