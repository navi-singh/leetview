---
description: HARD
---

# 123. Best Time to Buy and Sell Stock III

You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`th day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

**Example 1:**

```text
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3. Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3. Total profit = 6.
```

**Example 2:**

```text
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
```

**Example 3:**

```text
Input: prices = [7,6,4,3,1]
Output: 0
```

**Constraints:**

- `1 <= prices.length <= 10^5`
- `0 <= prices[i] <= 10^4`

---

## Approach: Two-Pass DP (Forward + Backward)

Split the problem into two halves at each index `j`: the best single transaction in `prices[0..j]` (forward pass) and the best single transaction in `prices[j..n-1]` (backward pass). The answer is the maximum over all split points of the sum of both halves.

- **Forward pass:** Build `profits[i]` = best profit from one transaction using only `prices[0..i]`.
- **Backward pass:** Walk right to left; for each `j` compute the best profit from one transaction in `prices[j..n-1]` and combine it with `profits[j]`.

#### Complexity Analysis

- **Time complexity: O(n).** Two linear scans over the prices array.
- **Space complexity: O(n).** The `profits` array stores the best first-transaction profit up to each index.

```java
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
```

**Key insight:** Every valid pair of two non-overlapping transactions has a split point between them; precomputing the best first transaction up to each index allows the second transaction to be evaluated greedily in the backward pass without double-counting.
