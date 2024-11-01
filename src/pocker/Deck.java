package pocker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
   private List<Card> cards;

   public Deck() {
      cards = new ArrayList<>();
      for (Suit suit : Suit.values()) {
         for (Rank rank : Rank.values()) {
            cards.add(new Card(suit, rank));
         }
      }
      shuffle();
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("===============DECK===============\n");

      for (Card card : cards) {
         sb.append(card.toString()).append("\n");
      }

      return sb.toString();
   }

   public List<Card> getCards() {
      return cards;
   }

   public void shuffle() {
      Collections.shuffle(cards);
   }
}
