package pocker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTest {

   @Test
   public void deckSizeTest() {
      Deck deck = new Deck();
      assertEquals(52, deck.getCards().size());
   }

   @Test
   public void deckTest2() {
      Deck deck = new Deck();
      System.out.println(deck);
   }
}
