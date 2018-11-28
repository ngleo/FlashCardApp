package tests;

import model.Card;
import model.CardPack;
import model.Inventory;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class InventoryTest extends Inventory {

  private Inventory inv1;
  private Inventory inv2;
  private CardPack cp1;
  private CardPack cp2;
  private CardPack cp3;
  private Card c1;
  private Card c2;
  private Card c3;
  private Card c4;
  private Card c5;
  private Card c6;

  @Before
  public void setup() {
    // set up inventory, cardPacks, cards
    c1 = new Card("1f", "1b");
    c2 = new Card("2f", "2b");
    c3 = new Card("3f", "3b");
    c4 = new Card("4f", "4b");
    c5 = new Card("5f", "5b");
    c6 = new Card("6f", "6b");
    cp1 = new CardPack("cp1");
    cp2 = new CardPack("cp2");
    cp3 = new CardPack("cp3");
    cp1.addCard(c1);
    cp1.addCard(c2);
    cp2.addCard(c3);
    cp2.addCard(c4);
    cp3.addCard(c5);
    cp3.addCard(c6);
    inv1 = new Inventory();
    inv1.addCardPack(cp1);
    inv1.addCardPack(cp2);
    inv1.addCardPack(cp3);
    inv1.saveCardPacks("data/testData/");
  }

  @Test
  public void testSaveCardPacks() {
    // create two lists of expected and actual results
    ArrayList<String> actual = new ArrayList<>();
    actual.add("data/testData/cp2.txt");
    actual.add("data/testData/cp3.txt");
    actual.add("data/testData/cp1.txt");

    ArrayList<String> expected = new ArrayList<>();

    // check if two lists are the same in String
    File path = new File("data/testData");
    File[] fileList = path.listFiles();

    for (File f : fileList) {
      expected.add(f.toString());
    }

    assertEquals(expected, actual);

    // remove files
    for (File f : fileList) {
      f.delete();
    }
  }

  @Test
  public void testReadObjects() {
    inv2 = new Inventory();

    File path = new File("data/testData");
    File[] fileList = path.listFiles();

    inv2.readCardPacks(path.toString());

    // remove files
    for (File f : fileList) {
      f.delete();
    }
  }
}
