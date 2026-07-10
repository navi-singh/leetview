---
description: MEDIUM
---

# 134. Gas Station

There are `n` gas stations along a circular route, where the amount of gas at the `i`th station is `gas[i]`.

You have a car with an unlimited gas tank and it costs `cost[i]` of gas to travel from the `i`th station to its next `(i + 1)`th station. You begin the journey with an empty tank at one of the gas stations.

Given two integer arrays `gas` and `cost`, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return `-1`. If there exists a solution, it is **guaranteed** to be **unique**.

**Example 1:**

```text
Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your tank = 5 - 5 + 0 = 0
You can travel around the circuit without running out of gas!
```

**Example 2:**

```text
Input: gas = [2,3,4], cost = [3,4,3]
Output: -1
Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's begin our journey from station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
```

**Constraints:**

- `n == gas.length == cost.length`
- `1 <= n <= 10^5`
- `0 <= gas[i], cost[i] <= 10^4`

---

## Approach: Greedy Single Pass

Track a running current balance (`curr`) and a global total balance (`total`). Whenever `curr` drops below zero, the current start index is invalid, so reset `curr` and move `startIndex` to `i + 1`. After the full pass, if `total >= 0` a solution exists and `startIndex` is the answer; otherwise return `-1`.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass over both arrays.
- **Space complexity: O(1).** Only a handful of scalar variables are used.

```java
package com.lc;

public class LC_0134_GasStation {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int curr = 0, total = 0, diff = 0;
    int startIndex = 0;
    for (int i = 0; i < gas.length; i++) {
      diff = gas[i] - cost[i];
      curr += diff;
      total += diff;
      if (curr < 0) {
        curr = 0;
        startIndex = i + 1;
      }
    }
    return total >= 0 ? startIndex : -1;
  }
}
```

**Key insight:** If the total gas surplus is non-negative, a valid starting point must exist, and it is always the station immediately after the last point where the running balance went negative.
