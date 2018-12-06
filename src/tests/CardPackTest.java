package tests;

import model.Card;
import model.CardPack;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Unit testing for CardPack Class
 */
public class CardPackTest {
  public static final String FILE_PATH = "resources/testResources/";
  public CardPack cp1;

  @Before
  public void setup() {
    cp1 = new CardPack("TestCardPack");

    Card c1 = new Card("1f", "1b");
    Card c2 = new Card("2f", "2b");
    Card c3 = new Card("3f", "3b");
    Card c4 = new Card("4f", "4b");

    cp1.addCard(c1);
    cp1.addCard(c2);
    cp1.addCard(c3);
    cp1.addCard(c4);
  }

  @Test
  public void testSaveCardPack() {
    cp1.saveCardPack(FILE_PATH);

    // create actual and expected list
    ArrayList<String> actual = new ArrayList<>();
    actual.add("resources/testResources/TestCardPack.txt");

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
}
