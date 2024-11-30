package com.lc;

public class LC_0123_BestTimeStock3 {
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length < 1) {
      return 0;
    }
    int[] profits = new int[prices.length];
    profits[0] = 0;
    int profit = 0, min = prices[0];
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] - min > profit) {
        profit = prices[i] - min;
      }
      if (min > prices[i]) {
        min = prices[i];
      }
      profits[i] = profit;
    }
    int maxProfit = profits[prices.length - 1];
    int maxValue = prices[prices.length - 1];
    profit = 0;
    for (int j = prices.length - 2; j >= 0; j--) {
      if (maxValue - prices[j] > profit) {
        profit = maxValue - prices[j];
      }
      if (maxValue < prices[j]) {
        maxValue = prices[j];
      }
      maxProfit = Math.max(maxProfit, profit + profits[j]);
    }
    return maxProfit;
  }
}
