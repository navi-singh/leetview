public class LC_0121_BestTimStock {
  public int maxProfit(int[] prices) {
    int min = Integer.MAX_VALUE, maxProfit = 0;
    for (int i = 0; i < prices.length; i++) {
      if (min > prices[i]) {
        min = prices[i];
      } else if (prices[i] - min > maxProfit) {
        maxProfit = prices[i] - min;
      }
    }
    return maxProfit;
  }
}
