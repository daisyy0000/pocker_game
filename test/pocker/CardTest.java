package pocker;

import org.junit.Test;

import static pocker.Suit.DIAMONDS;
import static pocker.Suit.SPADES;

public class CardTest {

   @Test
   public void cardTest() {
   Card c = new Card(SPADES, Rank.ACE);
   System.out.println(c);
   }

   @Test
   public void cardTest2() {
      Card c = new Card(DIAMONDS, Rank.TWO);
      System.out.println(c);
   }
}
