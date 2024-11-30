public class LC_0122_BestTimeStock2 {
  public int maxProfit(int[] prices) {
    int profit = 0, diff = 0;
    for (int i = 1; i < prices.length; i++) {
      diff = prices[i] - prices[i - 1];
      if (diff > 0) {
        profit += diff;
      }
    }
    return profit;
  }
}
