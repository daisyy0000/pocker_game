package pocker;

public class Card {

   static final int NUM_MAX = 13;

   private final Suit suit;
   private final Rank rank;

   public Card(Suit suit, Rank rank) {
      this.suit = suit;
      this.rank = rank;
   }

   public Suit getSuit() {
      return suit;
   }

   public Rank getRank() {
      return rank;
   }

   @Override
   public String toString() {
      return "[suit: " + suit + ", rank: " + rank + "]";
   }
}
