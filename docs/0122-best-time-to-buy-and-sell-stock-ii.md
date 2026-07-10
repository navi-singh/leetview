---
description: MEDIUM
---

# 122. Best Time to Buy and Sell Stock II

You are given an integer array `prices` where `prices[i]` is the price of a given stock on the `i`th day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

**Example 1:**

```text
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4. Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3. Total profit = 4 + 3 = 7.
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
Explanation: There is no way to make a positive profit.
```

**Constraints:**

- `1 <= prices.length <= 3 * 10^4`
- `0 <= prices[i] <= 10^4`

---

## Approach: Greedy — Accumulate All Positive Differences

Since unlimited transactions are allowed, the maximum profit equals the sum of all positive day-to-day price increases. Summing every consecutive positive difference is equivalent to capturing every upward slope in the price chart.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass through the prices array.
- **Space complexity: O(1).** Only two variables are used.

```java
package com.lc;

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
```

**Key insight:** Any multi-day uptrend (e.g., prices 1 → 3 → 5 gives profit 4) can be decomposed into consecutive daily gains (1→3 = 2, 3→5 = 2), so accumulating all positive daily differences captures the full profit without needing to track actual buy/sell days.
