package pocker;

public enum Rank {
   ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
   SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
   JACK(11), QUEEN(12), KING(13);

   private final int value;

   //열거형의 생성자는 제어자가 묵시적으로 private
   Rank(int value) {
      this.value = value;
   }

   public int getValue() {
      return value;
   }
}