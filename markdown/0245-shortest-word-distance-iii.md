---
description: MEDIUM
---

# 245. Shortest Word Distance III

Given an array of strings `wordsDict` and two strings that already exist in the array `word1` and `word2`, return the shortest distance between the occurrence of these two words in the list.

**Note** that `word1` and `word2` may be the same. It is guaranteed that they represent **two individual words** in the list.

**Example 1:**

```text
Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1
```

**Example 2:**

```text
Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
Output: 3
```

**Constraints:**

- `1 <= wordsDict.length <= 10^5`
- `1 <= wordsDict[i].length <= 10`
- `wordsDict[i]` consists of lowercase English letters.
- `word1` and `word2` are in `wordsDict`.

---

## Approach: Single Pass with Same-Word Handling

Handle two cases:

1. **`word1 == word2`:** Track the previous occurrence index (`prev`). Each time the word is seen, compare the current index to `prev` and update the minimum.
2. **`word1 != word2`:** Use two separate last-seen indices (`i1`, `i2`). Whenever either word is found and the other has been seen, update the minimum distance.

#### Complexity Analysis

- **Time complexity: O(n).** Single pass through the array.
- **Space complexity: O(1).** A fixed number of index variables regardless of input size.

```java
package com.lc;

/**
 * This is a follow-up problem of Shortest Word Distance. The only difference is now word1 could be
 * the same as word2.
 *
 * <p>Given a list of words and two words word1 and word2, return the shortest distance between
 * these two words in the list.
 *
 * <p>word1 and word2 may be the same and they represent two individual words in the list.
 *
 * <p>For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * <p>Given word1 = "makes", word2 = "coding", return 1. Given word1 = "makes", word2 = "makes",
 * return 3.
 */
public class LC_0245_ShortestDistance3 {
  public int shortestWordDistance(String[] words, String word1, String word2) {
    if (words == null || words.length == 0) return -1;

    if (word1 == null || word2 == null) return -1;

    boolean isSame = false;

    if (word1.equals(word2)) isSame = true;

    int shortest = Integer.MAX_VALUE;

    int prev = -1;
    int i1 = -1;
    int i2 = -1;

    for (int i = 0; i < words.length; i++) {
      if (isSame) {
        if (words[i].equals(word1)) {
          if (prev != -1) {
            shortest = Math.min(shortest, i - prev);
          }
          prev = i;
        }
      } else {
        if (word1.equals(words[i])) {
          i1 = i;
          if (i2 != -1) {
            shortest = Math.min(shortest, i - i2);
          }
        } else if (word2.equals(words[i])) {
          i2 = i;
          if (i1 != -1) {
            shortest = Math.min(shortest, i - i1);
          }
        }
      }
    }

    return shortest;
  }
}
```

**Key insight:** The key extension over problem 243 is branching on `isSame` — when both words are identical, consecutive occurrences of that single word are the candidates, so a single `prev` pointer suffices instead of two separate trackers.
