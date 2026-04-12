---
description: EASY
---

# 243. Shortest Word Distance

Given an array of strings `wordsDict` and two different strings that already exist in the array `word1` and `word2`, return the shortest distance between these two words in the list.

**Example 1:**

```text
Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
Output: 3
```

**Example 2:**

```text
Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1
```

**Constraints:**

- `2 <= wordsDict.length <= 3 * 10^4`
- `1 <= wordsDict[i].length <= 10`
- `wordsDict[i]` consists of lowercase English letters.
- `word1` and `word2` are in `wordsDict`.
- `word1 != word2`

---

## Approach: Single Pass with Last-Seen Indices

Iterate through the array tracking the most recent index of `word1` (`firstWordIndex`) and `word2` (`secondWordIndex`). Whenever either word is found and the other has been seen before, update the minimum distance.

#### Complexity Analysis

- **Time complexity: O(n).** Single pass through the array.
- **Space complexity: O(1).** Only two index variables and one minimum variable.

```java
package com.lc;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these
 * two words in the list.
 *
 * <p>For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * <p>Given word1 = "coding", word2 = "practice", return 3. Given word1 = "makes", word2 = "coding",
 * return 1.
 */
public class LC_0243_ShortestWordDistance {
  public int shortestDistance(String[] words, String word1, String word2) {
    int firstWordIndex = -1, secondWordIndex = -1;
    int min = Integer.MIN_VALUE;
    for (int i = 0; i < words.length; i++) {
      if (word1.equals(words[i])) {
        firstWordIndex = i;
        if (secondWordIndex != -1) {
          min = Math.min(min, firstWordIndex - secondWordIndex);
        }
      } else if (word2.equals(words[i])) {
        secondWordIndex = i;
        if (firstWordIndex != -1) {
          min = Math.min(min, secondWordIndex - firstWordIndex);
        }
      }
    }
    return min;
  }
}
```

**Key insight:** Keeping only the most recent index of each word is sufficient — the closest occurrence of any word to the other's latest occurrence will always yield the global minimum distance.
