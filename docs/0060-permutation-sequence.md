---
description: HARD
---

# 60. Permutation Sequence

The set `[1, 2, 3, ..., n]` contains a total of `n!` unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for `n = 3`:

1. `"123"`
2. `"132"`
3. `"213"`
4. `"231"`
5. `"312"`
6. `"321"`

Given `n` and `k`, return the `k`th permutation sequence.

**Example 1:**

```text
Input: n = 3, k = 3
Output: "213"
```

**Example 2:**

```text
Input: n = 4, k = 9
Output: "2314"
```

**Example 3:**

```text
Input: n = 3, k = 1
Output: "123"
```

**Constraints:**

- `1 <= n <= 9`
- `1 <= k <= n!`

---

## Approach 1: Factorial Number System (Direct Computation)

Convert `k` (made 0-indexed by decrementing) into a factorial number system representation. Start with `mod = n!`. At each position `i` (from 0 to `n-1`):

1. Divide `mod` by `(n - i)` to get the factorial for the current position.
2. The digit at this position is `k / mod` — this is the index into the remaining available numbers.
3. Update `k = k % mod` for the next position.
4. Append the chosen number from the remaining list and remove it.

This directly determines each digit without generating all permutations.

#### Complexity Analysis

- **Time complexity: O(n^2).** The outer loop runs `n` times and `ArrayList.remove` is `O(n)` in the worst case.
- **Space complexity: O(n).** The available numbers list holds at most `n` elements.

```java
public class LC_0060_PermutationSequence {
  // Need to find k/n! where n! will be total permutations
  public String getPermutation(int n, int k) {
    ArrayList<Integer> lis = new ArrayList<Integer>();
    for (int i = 1; i <= n; i++) {
      lis.add(i);
      System.out.print(i + ",");
    }
    System.out.println();
    k--;
    long mod = 1;
    for (int i = 1; i <= n; ++i) {
      mod = mod * i;
      System.out.print(mod + ",");
    }
    System.out.println();
    String result = "";
    for (int i = 0; i < n; i++) {
      mod = mod / (n - i);
      int currIndex = (int) Math.round(k / mod);
      k %= mod;
      result += lis.get(currIndex);
      System.out.println(currIndex + ":" + k + "<>" + mod + "::" + result);
      lis.remove(currIndex);
    }
    return result.toString();
  }
}
```

**Key insight:** By treating the permutation index as a factorial number system (each digit determined by how many `(n-1)!`-sized blocks fit into the remaining `k`), we jump directly to the `k`th permutation in `O(n)` digit selections without enumerating any intermediate permutations.
