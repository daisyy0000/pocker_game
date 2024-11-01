package pocker;

import java.util.*;

public class Game {
   private static final int PRIZE_MONEY = 100;
   private List<Player> players;
   private Dealer dealer;

   public Game() {
      this.players = new ArrayList<>();
      this.dealer = new Dealer();
   }

   public List<Player> getPlayers() {
      return players;
   }

   public Dealer getDealer() {
      return dealer;
   }

   public boolean isDuplicatePlayer(String nickname) {
      for(Player player :players) {
         if (nickname.equals(player.getNickname())) {
            return true;
         }
      }
      return false;
   }

   public void addPlayer(String nickName) {
      if (isDuplicatePlayer(nickName)) {
         throw new IllegalArgumentException("중복된 닉네임입니다.");
      } else {
         players.add(new Player(nickName));
      }
   }

   public void checkPlayerCnt() {
      if (players.isEmpty() || players.size() == 1 || players.size() > 4) {
         throw new RuntimeException("최소 참가인원이 부족하거나 초과하여 게임을 실행할 수 없습니다.");
      }
   }

   public void play(int rounds) {
      checkPlayerCnt();

      for (int i = 0; i < rounds; i++) {
         dealer.dealCards(players);
         Map<String, List<Player>> result = dealer.comparePlayerRank(players);
         winGame(result.get("highRankPlayers"));
         loseGame(result.get("lowRankPlayers"));
      }
      displayResults();
   }

   public void winGame(List<Player> highRankPlayers) {
      if(highRankPlayers.size() == 1) {
         highRankPlayers.get(0).addWinCnt();
         highRankPlayers.get(0).addMoney();
      } else {
         for(Player winPlayer : highRankPlayers) {
            winPlayer.addWinCnt();
            winPlayer.addMoney( PRIZE_MONEY / highRankPlayers.size());
         }
      }
   }

   public void loseGame(List<Player> lowRankPlayers) {
      for(Player losePlayer : lowRankPlayers) {
         losePlayer.addLossCnt();
      }
   }

   public void displayResults() {
      players.sort(Comparator.comparing(Player::getWinCnt).reversed());
      System.out.println("=================[포커 게임 결과]=================");
      for (Player player : players) {
         System.out.printf("%s: %d승, %d패,  보유금액: %d%n", player.getNickname()
                 , player.getWinCnt(), player.getLossCnt(), player.getMoney());
      }
   }
}
