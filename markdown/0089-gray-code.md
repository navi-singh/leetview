---
description: MEDIUM
---

# 89. Gray Code

An **n-bit gray code sequence** is a sequence of `2^n` integers where:

- Every integer is in the inclusive range `[0, 2^n - 1]`
- The first integer is `0`
- An integer appears no more than once in the sequence
- The binary representation of every pair of adjacent integers differs by exactly one bit
- The binary representation of the first and last integers differs by exactly one bit

Given an integer `n`, return any valid **n-bit gray code sequence**.

**Example 1:**

```text
Input: n = 2
Output: [0,1,3,2]
Explanation:
00 - 0
01 - 1
11 - 3
10 - 2
```

**Example 2:**

```text
Input: n = 1
Output: [0,1]
```

**Constraints:**

- `1 <= n <= 16`

---

## Approach 1: Iterative Mirror Reflection

The Gray code sequence for `n` bits can be built from the sequence for `n-1` bits by reflecting it and prepending the new highest bit. The algorithm starts with `[0]` and in each iteration appends a mirrored copy of the current list, with each mirrored element offset by the current increment (a power of 2). After `n` iterations the increment equals `2^n` and the list contains all `2^n` values.

#### Complexity Analysis

- **Time complexity: O(2^n).** The list doubles in size with each of the `n` iterations.
- **Space complexity: O(2^n).** The output list stores all `2^n` integers.

```java
public List<Integer> grayCode(int n) {
    List<Integer> lis = new ArrayList<>();
    lis.add(0);
    int increment = 1;
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < lis.size(); j++) {
            lis.add(lis.get(j) + increment);
        }
        increment <<= 1;
    }
    return lis;
}
```

**Key insight:** Reflecting the existing sequence and adding the new high-order bit ensures adjacent elements differ in exactly one bit, because the reflection point pairs elements that are already identical except for the new bit.
