package pocker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
   @Test
   public void playerTest() {
      Player player = new Player("dkkdft");
      assertEquals("dkkdft", player.getNickname());
   }

   @Test
   public void playerTest2() {
      Player player = new Player("dkkdft");
      System.out.println(player);
   }

   @Test
   public void nicknameTest() {
      Player player = new Player("");
      assertEquals("", player.getNickname());
   }

   @Test
   public void failLengthNicknameTest() {
      Player player = new Player("dkkdftdkkdftdkkdfdtdd");
      assertEquals("dkkdftdkkdftdkkdfdtdd", player.getNickname());
   }

   @Test
   public void playerMoneyTest() {
      Player player = new Player("dkkdft");
      assertEquals(10000, player.getMoney());
   }

   @Test
   public void addWinTest() {
      Player player = new Player("dkkdft");
      assertEquals(0, player.getWinCnt());
      assertEquals(10000, player.getMoney());
      player.addMoney(100);
      assertEquals(10100, player.getMoney());
      player.addWinCnt();
      assertEquals(1, player.getWinCnt());
   }

   @Test
   public void lossWinTest() {
      Player player = new Player("dkkdft");
      assertEquals(0, player.getWinCnt());
      assertEquals(0, player.getLossCnt());
      player.addLossCnt();
      assertEquals(0, player.getWinCnt());
      assertEquals(1, player.getLossCnt());
   }
}
