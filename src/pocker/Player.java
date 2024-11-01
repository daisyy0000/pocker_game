package pocker;

import java.util.ArrayList;
import java.util.List;

public class Player {
   private String nickname;
   private int money;
   private int winCnt;
   private int lossCnt;
   private int rankScore;
   private List<Card> cards;

   public Player(String nickname) {
      if (nickname == null || nickname.isBlank() || nickname.length() > 20) {
         throw new IllegalArgumentException("닉네임 생성 오류");
      }

      this.nickname = nickname;
      this.money = 10000;
      this.winCnt = 0;
      this.lossCnt = 0;
      this.rankScore = 0;
      this.cards = new ArrayList<>();
   }

   public String getNickname() {
      return nickname;
   }

   public int getMoney() {
      return money;
   }

   public int getWinCnt() {
      return winCnt;
   }

   public int getLossCnt() {
      return lossCnt;
   }

   public void setRankScore(int rankScore) {
      this.rankScore = rankScore;
   }

   public int getRankScore() {
      return rankScore;
   }

   public void setCards(List<Card> cards) {
      this.cards = cards;
   }

   public List<Card> getCards() {
      return cards;
   }

   @Override
   public String toString() {
      return "Player{" +
              "nickname='" + nickname + '\'' +
              ", money=" + money +
              ", winCnt=" + winCnt +
              ", lossCnt=" + lossCnt +
              ", rankScore=" + rankScore +
              ", cards=" + cards +
              '}';
   }

   public void addWinCnt() {
      winCnt++;
   }

   public void addMoney() {
      money += 100;
   }

   public void addMoney(int money) {
      this.money += money;
   }

   public void addLossCnt() {
      lossCnt++;
   }
}
