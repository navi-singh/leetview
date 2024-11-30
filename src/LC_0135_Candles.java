public class LC_0135_Candles {
  public int candy(int[] ratings) {
    if (ratings == null || ratings.length == 0) {
      return 0;
    }
    int candles[] = new int[ratings.length];
    candles[0] = 1;
    for (int i = 1; i < ratings.length; i++) {
      candles[i] = (ratings[i] > ratings[i - 1]) ? candles[i - 1] + 1 : 1;
    }
    int result = candles[ratings.length - 1];
    for (int i = ratings.length - 2; i >= 0; i--) {
      int cur = 1;
      if (ratings[i] > ratings[i + 1]) {
        cur = candles[i + 1] + 1;
      }
      result += Math.max(cur, candles[i]);
      candles[i] = cur;
    }
    return result;
  }
}
