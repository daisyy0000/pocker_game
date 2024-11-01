package pocker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PockerRankingsTest {

   @Test
   public void rankingTest() {
      int rank = PockerRanking.getRank("HIGH_CARD");
      assertEquals(1000, rank);
   }
}
