---
description: MEDIUM
---

# 204. Count Primes

Given an integer `n`, return the number of prime numbers that are strictly less than `n`.

**Example 1:**

```text
Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
```

**Example 2:**

```text
Input: n = 0
Output: 0
```

**Example 3:**

```text
Input: n = 1
Output: 0
```

**Constraints:**

- `0 <= n <= 5 * 10^6`

---

## Approach: Trial Division with Known Primes

Build up a list of primes incrementally. For each candidate `i`, test divisibility only against already-discovered primes. If none divide `i`, it is prime and is added to the list.

#### Complexity Analysis

- **Time complexity: O(n * sqrt(n) / ln n).** Each candidate is tested against O(sqrt(n) / ln n) primes on average.
- **Space complexity: O(n / ln n).** The primes list stores roughly n / ln n primes below n.

```java
public class LC_0204_CountPrimes {
  public int countPrimes(int n) {
    if (n < 3) {
      return 0;
    }
    if (n == 3) {
      return 1;
    }
    List<Integer> primes = new ArrayList<>();
    primes.add(2);
    primes.add(3);
    for (int i = 4; i < n; i++) {
      boolean isPrime = true;
      for (int p : primes) {
        int rem = i % p;
        if (rem == 0) {
          isPrime = false;
          break;
        }
      }
      if (isPrime) {
        primes.add(i);
      }
    }
    return primes.size();
  }
}
```

**Key insight:** Testing only against previously found primes avoids redundant composite checks; for large `n` the Sieve of Eratosthenes is faster, but this approach is simple and correct.
