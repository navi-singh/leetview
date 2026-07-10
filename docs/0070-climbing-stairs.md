---
description: EASY
---

# 70. Climbing Stairs

You are climbing a staircase. It takes `n` steps to reach the top. Each time you can either climb `1` or `2` steps. In how many distinct ways can you climb to the top?

**Example 1:**

```text
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
```

**Example 2:**

```text
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
```

**Constraints:**

- `1 <= n <= 45`

---

## Approach 1: Dynamic Programming (Array)

Store the number of ways to reach each step in an array. The base cases are `steps[0] = steps[1] = 1`. For every subsequent step `i`, the number of ways equals `steps[i-1] + steps[i-2]` (arrive from one step below or two steps below).

#### Complexity Analysis

- **Time complexity: O(n).** Single loop up to `n`.
- **Space complexity: O(n).** An array of size `n + 1` is used.

```java
  public int climbStairsUsingDP(int n) {
    if (n <= 2) {
      return n;
    }
    int[] steps = new int[n + 1];
    steps[0] = steps[1] = 1;
    for (int i = 2; i <= n; i++) {
      steps[i] = steps[i - 1] + steps[i - 2];
    }
    return steps[n];
  }
```

---

## Approach 2: Space-Optimized Fibonacci

Since each step only depends on the two immediately preceding values, we can drop the array and use two variables instead.

#### Complexity Analysis

- **Time complexity: O(n).** Single loop up to `n`.
- **Space complexity: O(1).** Only two rolling variables are maintained.

```java
public class LC_0070_ClimbingStairs {
  public int climbStairs(int n) {
    if (n <= 2) {
      return n;
    }

    int first = 1, second = 2;
    int temp = 0;
    for (int i = 3; i <= n; i++) {
      temp = first + second;
      first = second;
      second = temp;
    }
    return second;
  }
}
```

**Key insight:** The number of ways to climb `n` stairs follows the Fibonacci sequence — each value is the sum of the two before it — because the only choices are a 1-step or a 2-step move.
