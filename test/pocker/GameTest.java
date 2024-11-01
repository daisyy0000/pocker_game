package pocker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GameTest {
   Game game;

   @BeforeEach
   public void setup() {
      game = new Game();
   }

   @Test
   public void maxPlayerTest() {
      game.addPlayer("11111");
      game.addPlayer("22222");
      game.addPlayer("33333");
      game.addPlayer("44444");
      game.addPlayer("55555");

      assertThrows(RuntimeException.class, () -> {
         game.play(10);
      });
   }

   @Test
   public void singlePlayerTest() {
      game.addPlayer("11111");

      assertThrows(RuntimeException.class, () -> {
         game.play(10);
      });
   }

   @Test
   public void isEmptyPlayerTest() {
      assertThrows(RuntimeException.class, () -> {
         game.play(10);
      });
   }

   @Test
   public void failDupTest() {
      game.addPlayer("11111");
      game.addPlayer("22222");
      assertEquals(false, game.isDuplicatePlayer("44444"));
   }

   @Test
   public void successDupTest() {
      game.addPlayer("11111");
      game.addPlayer("22222");
      assertEquals(true, game.isDuplicatePlayer("22222"));
   }

   @Test
   public void addPlayerTest() {
      assertEquals(0, game.getPlayers().size());
      game.addPlayer("11111");
      assertEquals(1, game.getPlayers().size());
   }

   @Test
   public void addPlayerException() {
      game.addPlayer("11111");
      assertThrows(IllegalArgumentException.class, () -> {
         game.addPlayer("11111");
      });

   }

   @Test
   public void constructorTest() {
      System.out.println(game.getDealer().getDeck());
   }
}
