---
description: MEDIUM
---

# 96. Unique Binary Search Trees

Given an integer `n`, return the number of structurally unique BSTs (binary search trees) which has exactly `n` nodes of unique values from `1` to `n`.

**Example 1:**

```text
Input: n = 3
Output: 5
```

**Example 2:**

```text
Input: n = 1
Output: 1
```

**Constraints:**

- `1 <= n <= 19`

---

## Approach 1: Dynamic Programming (Catalan Number)

`count[i]` stores the number of unique BSTs that can be formed with `i` nodes. The recurrence is:

```
count[i] = sum over j from 0 to i-1 of ( count[j] * count[i - j - 1] )
```

Here `j` represents the number of nodes in the left subtree when the `(j+1)`-th value is chosen as root, and `i - j - 1` is the number of nodes in the right subtree. Base cases are `count[0] = count[1] = 1`.

#### Complexity Analysis

- **Time complexity: O(n^2).** The double loop fills an array of size `n+1`.
- **Space complexity: O(n).** The DP array stores `n+1` values.

```java
public int numTrees(int n) {
    int[] count = new int[n + 1];
    count[0] = count[1] = 1;
    for (int i = 2; i <= n; i++) {
        for (int j = 0; j <= i - 1; j++) {
            count[i] = count[i] + count[j] * count[i - j - 1];
        }
    }
    return count[n];
}
```

**Key insight:** The number of unique BSTs with `n` nodes is the n-th Catalan number, which is computed here via DP by summing over all possible root choices and multiplying the independent counts of left and right subtrees.
