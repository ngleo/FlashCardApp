package tests;

import model.Card;
import model.CardPack;
import org.junit.Before;
import org.junit.Test;
import ui.gui.CardPanel;

import static org.junit.Assert.assertEquals;

public class CardPanelTest {

    private CardPanel cardPanel;
    private CardPack cp1;
    private Card c1, c2, c3;


    @Before
    public void setUp() {
        cp1 = new CardPack("Opposites");
        c1 = new Card("Hello", "Bye");
        c2 = new Card("Near", "Far");
        c3 = new Card("Tall", "Short");
        cp1.addCard(c1);
        cp1.addCard(c2);
        cp1.addCard(c3);

        cardPanel = new CardPanel(cp1);
    }


    @Test
    public void testDisplaySetup() {
        assertEquals("Start Flashcards", cardPanel.getWordDisplay().getText());
    }

    @Test
    public void testNext() {

    }
    @Test
    public void testFirstFlip() {

    }
}
