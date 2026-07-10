---
description: EASY
---

# 121. Best Time to Buy and Sell Stock

You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`th day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return `0`.

**Example 1:**

```text
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
```

**Example 2:**

```text
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
```

**Constraints:**

- `1 <= prices.length <= 10^5`
- `0 <= prices[i] <= 10^4`

---

## Approach: One-Pass Greedy

Track the minimum price seen so far. For each day, either update the minimum if the current price is lower, or compute the profit if selling today and update the maximum profit. A single pass suffices because the optimal buy day is always the lowest price before the optimal sell day.

#### Complexity Analysis

- **Time complexity: O(n).** The array is traversed once.
- **Space complexity: O(1).** Only two variables (`min` and `maxProfit`) are maintained.

```java
package com.lc;

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
```

**Key insight:** Since we can only sell after buying, the optimal sell price for any given buy price is simply the maximum price that appears after it — tracking the running minimum implicitly handles this constraint.
