package pocker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DealerTest {
   Dealer dealer;

   @BeforeEach
   public void set() {
      dealer = new Dealer();
   }
   @Test
   public void dealCardTest() {
      List<Player> players = List.of(new Player[]{new Player("player1")
              , new Player("player2")
              , new Player("player3")});
      dealer.dealCards(players);
      System.out.println(players.get(0).getCards());
      System.out.println(players.get(1).getCards());
      System.out.println(players.get(2).getCards());
   }

   @Test
   public void royalStraightFlushTest() {
      List<Card> royalStraightFlushCards = List.of(
              new Card(Suit.HEARTS, Rank.TEN),
              new Card(Suit.HEARTS, Rank.JACK),
              new Card(Suit.HEARTS, Rank.QUEEN),
              new Card(Suit.HEARTS, Rank.KING),
              new Card(Suit.HEARTS, Rank.ACE)
      );

      assertEquals(13000, dealer.evaluateCardRank(royalStraightFlushCards));
   }

   @Test
   public void backStraightFlushTest() {
      List<Card> backStraightFlushCards = List.of(
              new Card(Suit.CLUBS, Rank.ACE),
              new Card(Suit.CLUBS, Rank.TWO),
              new Card(Suit.CLUBS, Rank.THREE),
              new Card(Suit.CLUBS, Rank.FOUR),
              new Card(Suit.CLUBS, Rank.FIVE)
      );

      assertEquals(12000, dealer.evaluateCardRank(backStraightFlushCards));
   }

   @Test
   public void straightFlushTest() {
      List<Card> cards = List.of(
              new Card(Suit.HEARTS, Rank.TEN),
              new Card(Suit.HEARTS, Rank.JACK),
              new Card(Suit.HEARTS, Rank.QUEEN),
              new Card(Suit.HEARTS, Rank.KING),
              new Card(Suit.HEARTS, Rank.NINE)
      );

      assertEquals(11000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void straightFlushTest2() {
      List<Card> cards = List.of(
              new Card(Suit.CLUBS, Rank.SIX),
              new Card(Suit.CLUBS, Rank.TWO),
              new Card(Suit.CLUBS, Rank.THREE),
              new Card(Suit.CLUBS, Rank.FOUR),
              new Card(Suit.CLUBS, Rank.FIVE)
      );

      assertEquals(11000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void fourCardTest() {
      List<Card> cards = List.of(
              new Card(Suit.HEARTS, Rank.SIX),
              new Card(Suit.CLUBS, Rank.SIX),
              new Card(Suit.SPADES, Rank.SIX),
              new Card(Suit.DIAMONDS, Rank.SIX),
              new Card(Suit.CLUBS, Rank.FIVE)
      );

      assertEquals(10000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void fullHouseTest() {
      List<Card> cards = List.of(
              new Card(Suit.HEARTS, Rank.SIX),
              new Card(Suit.CLUBS, Rank.SIX),
              new Card(Suit.SPADES, Rank.SIX),
              new Card(Suit.DIAMONDS, Rank.FIVE),
              new Card(Suit.CLUBS, Rank.FIVE)
      );

      assertEquals(9000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void flushTest() {
      List<Card> cards = List.of(
              new Card(Suit.CLUBS, Rank.KING),
              new Card(Suit.CLUBS, Rank.TWO),
              new Card(Suit.CLUBS, Rank.THREE),
              new Card(Suit.CLUBS, Rank.FOUR),
              new Card(Suit.CLUBS, Rank.FIVE)
      );

      assertEquals(8000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void mountainTest() {
      List<Card> cards = List.of(
              new Card(Suit.DIAMONDS, Rank.TEN),
              new Card(Suit.HEARTS, Rank.JACK),
              new Card(Suit.SPADES, Rank.QUEEN),
              new Card(Suit.CLUBS, Rank.KING),
              new Card(Suit.HEARTS, Rank.ACE)
      );

      assertEquals(7000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void testBackStraight() {
      List<Card> cards = List.of(
           new Card(Suit.DIAMONDS, Rank.TWO),
              new Card(Suit.HEARTS, Rank.THREE),
              new Card(Suit.SPADES, Rank.FOUR),
              new Card(Suit.CLUBS, Rank.FIVE),
              new Card(Suit.HEARTS, Rank.ACE)
      );

      assertEquals(6000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void testTriple() {
      List<Card> cards = List.of(
              new Card(Suit.DIAMONDS, Rank.TWO),
              new Card(Suit.HEARTS, Rank.TWO),
              new Card(Suit.SPADES, Rank.TWO),
              new Card(Suit.CLUBS, Rank.FIVE),
              new Card(Suit.HEARTS, Rank.SIX)
      );

      assertEquals(4000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void testTwoPair() {
      List<Card> cards = List.of(
              new Card(Suit.DIAMONDS, Rank.TWO),
              new Card(Suit.HEARTS, Rank.TWO),
              new Card(Suit.SPADES, Rank.EIGHT),
              new Card(Suit.CLUBS, Rank.SIX),
              new Card(Suit.HEARTS, Rank.SIX)
      );

      assertEquals(3000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void testOnePair() {
      List<Card> cards = List.of(
              new Card(Suit.DIAMONDS, Rank.TWO),
              new Card(Suit.HEARTS, Rank.TWO),
              new Card(Suit.SPADES, Rank.EIGHT),
              new Card(Suit.CLUBS, Rank.SIX),
              new Card(Suit.HEARTS, Rank.SEVEN)
      );

      assertEquals(2000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void testHighCard() {
      List<Card> cards = List.of(
              new Card(Suit.DIAMONDS, Rank.TWO),
              new Card(Suit.HEARTS, Rank.ACE),
              new Card(Suit.SPADES, Rank.EIGHT),
              new Card(Suit.CLUBS, Rank.SIX),
              new Card(Suit.HEARTS, Rank.SEVEN)
      );

      assertEquals(1000, dealer.evaluateCardRank(cards));
   }

   @Test
   public void shuffleTest() {
      List<Player> playerList = new ArrayList<>();
      Player player1 =  new Player("test1");
      Player player2 =  new Player("test2");
      playerList.add(player1);
      playerList.add(player2);
      System.out.println("---------------전------------");
      List<Card> cards = dealer.getDeck().getCards().subList(0,5);
      for(Card c : cards) {
         System.out.println(c);
      }
      dealer.dealCards(playerList);
      System.out.println("---------------후------------");
      List<Card> cards2 = dealer.getDeck().getCards().subList(0,5);
      for(Card c : cards2) {
         System.out.println(c);
      }
      dealer.dealCards(playerList);
      System.out.println("---------------후2------------");
      List<Card> cards3 = dealer.getDeck().getCards().subList(0,5);
      for(Card c : cards3) {
         System.out.println(c);
      }
   }

   @Test
   public void onePairSameRankTest() {
      List<Card> cards1 = new ArrayList<>();
      List<Card> cards2= new ArrayList<>();

      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");

      cards1.add(new Card(Suit.HEARTS, Rank.TEN));
      cards1.add(new Card(Suit.DIAMONDS,Rank.TEN));
      cards1.add(new Card(Suit.CLUBS, Rank.FOUR));
      cards1.add(new Card(Suit.SPADES, Rank.THREE));
      cards1.add(new Card(Suit.HEARTS, Rank.TWO));

      cards2.add(new Card(Suit.HEARTS, Rank.NINE));
      cards2.add(new Card(Suit.DIAMONDS,Rank.NINE));
      cards2.add(new Card(Suit.CLUBS, Rank.FOUR));
      cards2.add(new Card(Suit.SPADES, Rank.THREE));
      cards2.add(new Card(Suit.HEARTS, Rank.TWO));

      player1.setCards(cards1);
      player2.setCards(cards2);

      int score1 = dealer.evaluateCardRank(player1.getCards());
      int score2 = dealer.evaluateCardRank(player2.getCards());

      System.out.printf("%d, %d", score1, score2);
   }

   @Test
   public void highCardSameRankTest() {
      List<Card> cards1 = new ArrayList<>();
      List<Card> cards2= new ArrayList<>();

      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");

      cards1.add(new Card(Suit.HEARTS, Rank.KING));
      cards1.add(new Card(Suit.DIAMONDS, Rank.TEN));
      cards1.add(new Card(Suit.CLUBS, Rank.SEVEN));
      cards1.add(new Card(Suit.SPADES, Rank.FIVE));
      cards1.add(new Card(Suit.HEARTS, Rank.TWO));

      cards2.add(new Card(Suit.HEARTS, Rank.JACK));
      cards2.add(new Card(Suit.DIAMONDS, Rank.TEN));
      cards2.add(new Card(Suit.CLUBS, Rank.SIX));
      cards2.add(new Card(Suit.SPADES, Rank.FIVE));
      cards2.add(new Card(Suit.HEARTS, Rank.TWO));

      player1.setCards(cards1);
      player2.setCards(cards2);

      int score1 = dealer.evaluateCardRank(player1.getCards());
      int score2 = dealer.evaluateCardRank(player2.getCards());

      System.out.printf("%d, %d", score1, score2);
   }

   @Test
   public void twoPairSameRankTest() {
      List<Card> cards1 = new ArrayList<>();
      List<Card> cards2= new ArrayList<>();

      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");

      cards1.add(new Card(Suit.HEARTS, Rank.QUEEN));
      cards1.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
      cards1.add(new Card(Suit.CLUBS, Rank.TEN));
      cards1.add(new Card(Suit.SPADES, Rank.TEN));
      cards1.add(new Card(Suit.HEARTS, Rank.EIGHT));

      cards2.add(new Card(Suit.HEARTS, Rank.JACK));
      cards2.add(new Card(Suit.DIAMONDS, Rank.JACK));
      cards2.add(new Card(Suit.CLUBS, Rank.TEN));
      cards2.add(new Card(Suit.SPADES, Rank.TEN));
      cards2.add(new Card(Suit.HEARTS, Rank.SEVEN));

      player1.setCards(cards1);
      player2.setCards(cards2);

      int score1 = dealer.evaluateCardRank(player1.getCards());
      int score2 = dealer.evaluateCardRank(player2.getCards());

      System.out.printf("%d, %d", score1, score2);
   }

   @Test
   public void tripleSameRankTest() {
      List<Card> cards1 = new ArrayList<>();
      List<Card> cards2= new ArrayList<>();

      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");

      cards1.add(new Card(Suit.HEARTS, Rank.QUEEN));
      cards1.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
      cards1.add(new Card(Suit.CLUBS, Rank.QUEEN));
      cards1.add(new Card(Suit.SPADES, Rank.NINE));
      cards1.add(new Card(Suit.HEARTS, Rank.EIGHT));

      cards2.add(new Card(Suit.HEARTS, Rank.JACK));
      cards2.add(new Card(Suit.DIAMONDS, Rank.JACK));
      cards2.add(new Card(Suit.CLUBS, Rank.JACK));
      cards2.add(new Card(Suit.SPADES, Rank.NINE));
      cards2.add(new Card(Suit.HEARTS, Rank.SEVEN));

      player1.setCards(cards1);
      player2.setCards(cards2);

      int score1 = dealer.evaluateCardRank(player1.getCards());
      int score2 = dealer.evaluateCardRank(player2.getCards());

      System.out.printf("%d, %d", score1, score2);
   }

   @Test
   public void straightSameRankTest() {
      List<Card> cards1 = new ArrayList<>();
      List<Card> cards2= new ArrayList<>();

      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");

      cards1.add(new Card(Suit.HEARTS, Rank.FIVE));
      cards1.add(new Card(Suit.DIAMONDS, Rank.SIX));
      cards1.add(new Card(Suit.CLUBS, Rank.SEVEN));
      cards1.add(new Card(Suit.SPADES, Rank.EIGHT));
      cards1.add(new Card(Suit.HEARTS, Rank.NINE));

      cards2.add(new Card(Suit.HEARTS, Rank.FOUR));
      cards2.add(new Card(Suit.DIAMONDS, Rank.FIVE));
      cards2.add(new Card(Suit.CLUBS, Rank.SIX));
      cards2.add(new Card(Suit.SPADES, Rank.SEVEN));
      cards2.add(new Card(Suit.HEARTS, Rank.EIGHT));

      player1.setCards(cards1);
      player2.setCards(cards2);

      int score1 = dealer.evaluateCardRank(player1.getCards());
      int score2 = dealer.evaluateCardRank(player2.getCards());

      System.out.printf("%d, %d", score1, score2);
   }

   @Test
   public void flushSameRankTest() {
      List<Card> cards1 = new ArrayList<>();
      List<Card> cards2= new ArrayList<>();

      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");

      cards1.add(new Card(Suit.HEARTS, Rank.TWO));
      cards1.add(new Card(Suit.HEARTS, Rank.FOUR));
      cards1.add(new Card(Suit.HEARTS, Rank.SIX));
      cards1.add(new Card(Suit.HEARTS, Rank.EIGHT));
      cards1.add(new Card(Suit.HEARTS, Rank.QUEEN));

      cards2.add(new Card(Suit.HEARTS, Rank.TWO));
      cards2.add(new Card(Suit.HEARTS, Rank.FOUR));
      cards2.add(new Card(Suit.HEARTS, Rank.SIX));
      cards2.add(new Card(Suit.HEARTS, Rank.EIGHT));
      cards2.add(new Card(Suit.HEARTS, Rank.JACK));

      player1.setCards(cards1);
      player2.setCards(cards2);

      int score1 = dealer.evaluateCardRank(player1.getCards());
      int score2 = dealer.evaluateCardRank(player2.getCards());

      System.out.printf("%d, %d", score1, score2);
   }

   @Test
   public void fullHouseSameRankTest() {
      List<Card> cards1 = new ArrayList<>();
      List<Card> cards2= new ArrayList<>();

      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");

      cards1.add(new Card(Suit.HEARTS, Rank.TEN));
      cards1.add(new Card(Suit.DIAMONDS, Rank.TEN));
      cards1.add(new Card(Suit.CLUBS, Rank.TEN));
      cards1.add(new Card(Suit.SPADES, Rank.FOUR));
      cards1.add(new Card(Suit.HEARTS, Rank.FOUR));

      cards2.add(new Card(Suit.HEARTS, Rank.NINE));
      cards2.add(new Card(Suit.DIAMONDS, Rank.NINE));
      cards2.add(new Card(Suit.CLUBS, Rank.NINE));
      cards2.add(new Card(Suit.SPADES, Rank.FIVE));
      cards2.add(new Card(Suit.HEARTS, Rank.FIVE));

      player1.setCards(cards1);
      player2.setCards(cards2);

      int score1 = dealer.evaluateCardRank(player1.getCards());
      int score2 = dealer.evaluateCardRank(player2.getCards());

      System.out.printf("%d, %d", score1, score2);
   }

   @Test
   public void fourCardSameRankTest() {
      List<Card> cards1 = new ArrayList<>();
      List<Card> cards2= new ArrayList<>();

      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");

      cards1.add(new Card(Suit.HEARTS, Rank.TEN));
      cards1.add(new Card(Suit.DIAMONDS, Rank.TEN));
      cards1.add(new Card(Suit.CLUBS, Rank.TEN));
      cards1.add(new Card(Suit.SPADES, Rank.TEN));
      cards1.add(new Card(Suit.HEARTS, Rank.THREE));

      cards2.add(new Card(Suit.HEARTS, Rank.NINE));
      cards2.add(new Card(Suit.DIAMONDS, Rank.NINE));
      cards2.add(new Card(Suit.CLUBS, Rank.NINE));
      cards2.add(new Card(Suit.SPADES, Rank.NINE));
      cards2.add(new Card(Suit.HEARTS, Rank.TWO));

      player1.setCards(cards1);
      player2.setCards(cards2);

      int score1 = dealer.evaluateCardRank(player1.getCards());
      int score2 = dealer.evaluateCardRank(player2.getCards());

      System.out.printf("%d, %d", score1, score2);
   }

   @Test
   public void straightFlushSameRankTest() {
      List<Card> cards1 = new ArrayList<>();
      List<Card> cards2= new ArrayList<>();

      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");

      cards1.add(new Card(Suit.HEARTS, Rank.NINE));
      cards1.add(new Card(Suit.HEARTS, Rank.TEN));
      cards1.add(new Card(Suit.HEARTS, Rank.JACK));
      cards1.add(new Card(Suit.HEARTS, Rank.QUEEN));
      cards1.add(new Card(Suit.HEARTS, Rank.KING));

      cards2.add(new Card(Suit.SPADES, Rank.EIGHT));
      cards2.add(new Card(Suit.SPADES, Rank.NINE));
      cards2.add(new Card(Suit.SPADES, Rank.TEN));
      cards2.add(new Card(Suit.SPADES, Rank.JACK));
      cards2.add(new Card(Suit.SPADES, Rank.QUEEN));

      player1.setCards(cards1);
      player2.setCards(cards2);

      int score1 = dealer.evaluateCardRank(player1.getCards());
      int score2 = dealer.evaluateCardRank(player2.getCards());

      System.out.printf("%d, %d", score1, score2);
   }

   @Test
   public void compareRankTest() {
      List<Player> playerList = new ArrayList<>();
      Player p1 = new Player("p1");
      Player p2 = new Player("p2");
      Player p3 = new Player("p3");

      List<Card> card1 = new ArrayList<>();
      card1.add(new Card(Suit.HEARTS, Rank.QUEEN));
      card1.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
      card1.add(new Card(Suit.CLUBS, Rank.TEN));
      card1.add(new Card(Suit.SPADES, Rank.TEN));
      card1.add(new Card(Suit.HEARTS, Rank.EIGHT));

      List<Card> card2 = new ArrayList<>();
      card2.add(new Card(Suit.HEARTS, Rank.JACK));
      card2.add(new Card(Suit.DIAMONDS, Rank.JACK));
      card2.add(new Card(Suit.CLUBS, Rank.TEN));
      card2.add(new Card(Suit.SPADES, Rank.TEN));
      card2.add(new Card(Suit.HEARTS, Rank.SEVEN));

      List<Card> card3 = new ArrayList<>();
      card3.add(new Card(Suit.HEARTS, Rank.KING));
      card3.add(new Card(Suit.DIAMONDS, Rank.TEN));
      card3.add(new Card(Suit.CLUBS, Rank.SEVEN));
      card3.add(new Card(Suit.SPADES, Rank.FIVE));
      card3.add(new Card(Suit.HEARTS, Rank.TWO));

      p1.setCards(card1);
      p2.setCards(card2);
      p3.setCards(card3);

      playerList.add(p1);
      playerList.add(p2);
      playerList.add(p3);

      Map<String, List<Player>> result = dealer.comparePlayerRank(playerList);

      List<Player> highRankPlayers = result.get("highRankPlayers");
      List<Player> lowRankPlayers = result.get("lowRankPlayers");

      System.out.println("이긴사람: " + highRankPlayers);
      System.out.println("진 사람: " + lowRankPlayers);
   }

   @AfterEach
   public void tearDown() {
      dealer = null;
   }
}
