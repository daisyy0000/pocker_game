package pocker;

public enum PockerRanking {
   ROYAL_STRAIGHT_FLUSH(13_000),
   BACK_STRAIGHT_FLUSH(12_000),
   STRAIGHT_FLUSH(11_000),
   FOUR_CARD(10_000),
   FULL_HOUSE(9_000),
   FLUSH(8_000),
   MOUNTAIN(7_000),
   BACK_STRAIGHT(6_000),
   STRAIGHT(5_000),
   TRIPLE(4_000),
   TWO_PAIR(3_000),
   ONE_PAIR(2_000),
   HIGH_CARD(1_000);

   private final int rankScore;

   PockerRanking(int rankScore) {
      this.rankScore = rankScore;
   }

   public int getRankScore() {
      return rankScore;
   }

   public static int getRank(String playerRank) {
      try {
         return PockerRanking.valueOf(playerRank).getRankScore();
      } catch (IllegalArgumentException e) {
         return 0;
      }
   }
}
