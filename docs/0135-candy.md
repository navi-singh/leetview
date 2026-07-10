---
description: HARD
---

# 135. Candy

There are `n` children standing in a line. Each child is assigned a rating value given in the integer array `ratings`.

You are giving candies to these children subjected to the following requirements:

- Each child must have at least one candy.
- Children with a higher rating get more candies than their neighbors.

Return the minimum number of candies you need to have to distribute the candies to the children.

**Example 1:**

```text
Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
```

**Example 2:**

```text
Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.
```

**Constraints:**

- `n == ratings.length`
- `1 <= n <= 2 * 10^4`
- `0 <= ratings[i] <= 2 * 10^4`

---

## Approach: Two-Pass Greedy

**Left-to-right pass:** If a child's rating is higher than the previous child's, give them one more candy than the previous child; otherwise give them 1 candy.

**Right-to-left pass:** Traverse backwards. If a child's rating is higher than the next child's, their candy count must be at least `candles[i+1] + 1`. Take the maximum of the left-pass value and the right-pass requirement before summing.

#### Complexity Analysis

- **Time complexity: O(n).** Two linear passes over the array.
- **Space complexity: O(n).** The `candles` array holds one value per child.

```java
package com.lc;

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
```

**Key insight:** A single left-to-right pass satisfies the left-neighbor constraint, but only a right-to-left pass can satisfy the right-neighbor constraint; taking the `max` of both ensures both constraints are met simultaneously with the minimum candy count.
