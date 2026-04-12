---
description: EASY
---

# 202. Happy Number

Write an algorithm to determine if a number `n` is happy.

A **happy number** is a number defined by the following process:

- Starting with any positive integer, replace the number by the sum of the squares of its digits.
- Repeat the process until the number equals `1` (where it will stay), or it loops endlessly in a cycle which does not include `1`.
- Those numbers for which this process ends in `1` are happy.

Return `true` if `n` is a happy number, and `false` if not.

**Example 1:**

```text
Input: n = 19
Output: true
Explanation:
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
```

**Example 2:**

```text
Input: n = 2
Output: false
```

**Constraints:**

- `1 <= n <= 2^31 - 1`

---

## Approach: HashSet Cycle Detection

Track every intermediate value in a `HashSet`. If the transformation ever produces `1`, return `true`. If it produces a value already seen in the set, a cycle has formed and we return `false`.

#### Complexity Analysis

- **Time complexity: O(log n).** The number of digits decreases rapidly; the cycle length is bounded by a small constant.
- **Space complexity: O(log n).** The set stores intermediate values until a cycle is detected.

```java
public class LC_0202_HappyNumber {
  public boolean isHappy(int n) {
    Set<Integer> dict = new HashSet<>();
    while (!dict.contains(n)) {
      dict.add(n);
      n = transform(n);
      if (n == 1) {
        return true;
      }
    }
    return false;
  }

  private int transform(int n) {
    int result = 0;
    while (n > 0) {
      result += ((n % 10) * (n % 10));
      n = n / 10;
    }
    return result;
  }
}
```

**Key insight:** Every unhappy number eventually enters a cycle; storing seen values in a set lets us detect that cycle in O(1) per lookup instead of relying on the fixed cycle detection trick (Floyd's algorithm).
