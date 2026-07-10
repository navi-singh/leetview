---
description: HARD
---

# 188. Best Time to Buy and Sell Stock IV

You are given an integer array `prices` where `prices[i]` is the price of a given stock on the `i`th day, and an integer `k`.

Find the maximum profit you can achieve. You may complete at most `k` transactions: i.e. you may buy at most `k` times and sell at most `k` times.

**Note:** You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

**Example 1:**

```text
Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4 - 2 = 2.
```

**Example 2:**

```text
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6 - 2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3 - 0 = 3.
```

**Constraints:**

- `1 <= k <= 100`
- `1 <= prices.length <= 1000`
- `0 <= prices[i] <= 1000`

---

## Approach: Dynamic Programming (k Transactions)

Define two arrays: `buy[j]` = maximum profit after completing the buy leg of transaction `j`, and `sell[j]` = maximum profit after completing the sell leg of transaction `j`. For each price, update all transaction states from `k` down to 1. If `k >= n/2`, the problem degenerates to unlimited transactions (greedy sum of all positive differences).

#### Complexity Analysis

- **Time complexity: O(n * k).** For each of the `n` days, update `k` transaction states.
- **Space complexity: O(k).** Two arrays of size `k` track the buy and sell states.

```java
// The Java source file is a stub (empty class body).
// Standard DP solution for reference:

public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    if (k >= n / 2) {
        int profit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        }
        return profit;
    }
    int[] buy = new int[k + 1];
    int[] sell = new int[k + 1];
    Arrays.fill(buy, Integer.MIN_VALUE);
    for (int price : prices) {
        for (int j = k; j >= 1; j--) {
            sell[j] = Math.max(sell[j], buy[j] + price);
            buy[j]  = Math.max(buy[j],  sell[j - 1] - price);
        }
    }
    return sell[k];
}
```

**Key insight:** When `k` exceeds half the number of days, every profitable consecutive-day swing can be captured independently, so the problem reduces to the unlimited-transactions greedy approach.
