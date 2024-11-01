package pocker;

import java.util.*;

import static pocker.Card.NUM_MAX;

public class Dealer {
   private Deck deck;

   public Dealer() {
      this.deck = new Deck();
   }

   public Deck getDeck() {
      return deck;
   }

   public void dealCards(List<Player> players) {
      deck.shuffle();

      int playerIndex = 0;
      for (Player player : players) {
         List<Card> playerHand = new ArrayList<>();
         for (int j = 0; j < 5; j++) {
            playerHand.add(deck.getCards().get(playerIndex * 5 + j));
         }
         player.setCards(playerHand);
         playerIndex++;
      }
   }

   public int evaluateCardRank(List<Card> cards) {
      int rank = PockerRanking.getRank("HIGH_CARD");

      int[] cardNumCnt = new int[NUM_MAX];
      Map<Suit, Integer> cardKindCnt = new HashMap<>();

      for (Suit suit : Suit.values()) {
         cardKindCnt.put(suit, 0);
      }

      //player이 가지고 있는 카드의 숫자와 무늬 체크
      for (Card card : cards) {
         cardNumCnt[card.getRank().getValue() - 1]++;
         cardKindCnt.put(card.getSuit(), cardKindCnt.get(card.getSuit()) + 1);
      }

      //족보 점수 추가
      int pairCnt = 0;
      boolean hasTwoCard = false;
      boolean hasThreeCard = false;
      boolean hasFourCard = false;
      boolean hasStraight = false;
      int straightCnt = 0; //연속된 숫자 계산
      boolean hasBackStraight = false;
      boolean hasRoyalStraight = false;
      boolean hasFlush = cardKindCnt.containsValue(5);

      for (int num : cardNumCnt) {
         if (num == 1) {
            straightCnt++;
            if (straightCnt == 5) {
               hasStraight = true;
            }
         } else {
            straightCnt = 0;

            if (num == 2) {
               pairCnt++;
               hasTwoCard = true;
            }

            //full house 때문에 else if X
            if (num == 3) {
               hasThreeCard = true;
            } else if (num == 4) {
               hasFourCard = true;
            }
         }
      }

      if (pairCnt == 1) { rank = PockerRanking.getRank("ONE_PAIR"); }

      if (pairCnt == 2) { rank = PockerRanking.getRank("TWO_PAIR"); }

      if (hasThreeCard) { rank =  PockerRanking.getRank("TRIPLE"); }

      if (hasFourCard) { rank =  PockerRanking.getRank("FOUR_CARD"); }

      if (hasTwoCard && hasThreeCard) { rank = PockerRanking.getRank("FULL_HOUSE"); }

      //로얄 스트레이트(10, J, Q, K, A)
      if (cardNumCnt[9] == 1 && cardNumCnt[10] == 1 && cardNumCnt[11] == 1
              && cardNumCnt[12] == 1 && cardNumCnt[0] == 1) {
         hasRoyalStraight = true;
      }

      //백 스트레이트(A, 2, 3, 4, 5)
      if (cardNumCnt[0] == 1 && cardNumCnt[1] == 1 && cardNumCnt[2] == 1
              && cardNumCnt[3] == 1 && cardNumCnt[4] == 1 ) {
         hasBackStraight = true;
      }

      if (hasRoyalStraight && hasFlush) {
         rank = PockerRanking.getRank("ROYAL_STRAIGHT_FLUSH");
      } else if (hasRoyalStraight && !hasFlush){
         rank = PockerRanking.getRank("MOUNTAIN");
      } else if (hasBackStraight && hasFlush) {
         rank = PockerRanking.getRank("BACK_STRAIGHT_FLUSH");
      } else if (hasBackStraight && !hasFlush) {
         rank = PockerRanking.getRank("BACK_STRAIGHT");
      } else if (hasStraight && hasFlush) {
         rank = PockerRanking.getRank("STRAIGHT_FLUSH");
      } else if (hasStraight) {
         rank = PockerRanking.getRank("STRAIGHT");
      } else if (hasFlush) {
         rank = PockerRanking.getRank("FLUSH");
      }

      if (rank == PockerRanking.getRank("HIGH_CARD")
              || rank == PockerRanking.getRank("STRAIGHT")
              || rank == PockerRanking.getRank("FLUSH")
              || rank == PockerRanking.getRank("STRAIGHT_FLUSH")) {
         rank += addExtraScore(cardNumCnt, 1);
      }

      if (rank == PockerRanking.getRank("ONE_PAIR")
              || rank == PockerRanking.getRank("TWO_PAIR")) {
         rank += addExtraScore(cardNumCnt, 2);
      }

      if (rank == PockerRanking.getRank("TRIPLE")
              || rank == PockerRanking.getRank("FULL_HOUSE")) {
         rank += addExtraScore(cardNumCnt, 3);
      }

      if (rank == PockerRanking.getRank("FOUR_CARD")) {
         rank += addExtraScore(cardNumCnt, 4);
      }

      return rank;
   }

   public Map<String, List<Player>> comparePlayerRank(List<Player> players) {
      List<Player> highRankPlayers = new ArrayList<>();
      List<Player> lowRankPlayers = new ArrayList<>();

      for (Player player : players) {
         int rankScore = evaluateCardRank(player.getCards());
         player.setRankScore(rankScore);
      }

      Collections.sort(players, Comparator.comparingInt(Player::getRankScore).reversed());

      int highestScore = players.get(0).getRankScore();

      for (Player player : players) {
         if (player.getRankScore() == highestScore) {
            highRankPlayers.add(player);
         } else {
            lowRankPlayers.add(player);
         }
      }

      Map<String, List<Player>> result = new HashMap<>();
      result.put("highRankPlayers", highRankPlayers);
      result.put("lowRankPlayers", lowRankPlayers);

      return result;
   }

   public int addExtraScore(int[] cardNumCnt, int count) {
      int aceValue = 14;
      int addScore = 0;
      boolean hasAce = cardNumCnt[0] == count ? true : false;

      // ace는 aceValue로, 다른 카드는 i+1로
      for (int i = cardNumCnt.length - 1; i >= 0; i--) {
         if (hasAce) {
            return aceValue;
         }

         if (cardNumCnt[i] == count) {
            return i + 1;
         }
      }
      return addScore;
   }
}
