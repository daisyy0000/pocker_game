package pocker;

public class Main {
   public static void main(String[] args) {
      Game game = new Game();
      game.addPlayer("Player1");
      game.addPlayer("Player2");
      game.addPlayer("Player3");
      game.addPlayer("Player4");

      game.play(100);
   }
}
