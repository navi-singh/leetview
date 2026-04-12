---
description: MEDIUM
---

# 72. Edit Distance

Given two strings `word1` and `word2`, return the minimum number of operations required to convert `word1` to `word2`.

You have the following three operations permitted on a word:

- Insert a character
- Delete a character
- Replace a character

**Example 1:**

```text
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (delete 'r')
rose -> ros (delete 'e')
```

**Example 2:**

```text
Input: word1 = "intention", word2 = "execution"
Output: 5
```

**Constraints:**

- `0 <= word1.length, word2.length <= 500`
- `word1` and `word2` consist of lowercase English letters.

---

## Approach 1: Dynamic Programming (2D Table)

`dp[i][j]` represents the minimum edit distance between the first `i` characters of `word1` and the first `j` characters of `word2`. Base cases: converting an empty string requires as many operations as the other string's length. If the current characters match, `dp[i][j] = dp[i-1][j-1]`. Otherwise, take the minimum of delete (`dp[i-1][j]`), insert (`dp[i][j-1]`), or replace (`dp[i-1][j-1]`) and add 1.

#### Complexity Analysis

- **Time complexity: O(m * n).** We fill an `(m+1) x (n+1)` table where `m` and `n` are the lengths of the two words.
- **Space complexity: O(m * n).** The full DP table is stored.

```java
public class LC_0072_EditDistance {
  public int minDistance(String word1, String word2) {
    int len1 = word1.length();
    int len2 = word2.length();
    int[][] dp = new int[len1 + 1][len2 + 1];
    for (int i = 0; i <= len1; i++) {
      for (int j = 0; j <= len2; j++) {
        if (i == 0) {
          dp[i][j] = j;
        } else if (j == 0) {
          dp[i][j] = i;
        } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
        }
      }
    }
    return dp[len1][len2];
  }
}
```

**Key insight:** Each of the three edit operations corresponds to a specific neighboring cell in the DP table — left (insert), above (delete), or diagonal (replace) — so the recurrence captures all valid transitions in a single expression.
