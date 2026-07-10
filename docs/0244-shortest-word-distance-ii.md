---
description: MEDIUM
---

# 244. Shortest Word Distance II

Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.

Implement the `WordDistance` class:

- `WordDistance(String[] wordsDict)` initializes the object with the strings array `wordsDict`.
- `int shortest(String word1, String word2)` returns the shortest distance between `word1` and `word2` in the array `wordsDict`.

**Example 1:**

```text
Input:
["WordDistance", "shortest", "shortest"]
[[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
Output:
[null, 3, 1]
Explanation:
WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
wordDistance.shortest("coding", "practice"); // return 3
wordDistance.shortest("makes", "coding");    // return 1
```

**Constraints:**

- `1 <= wordsDict.length <= 3 * 10^4`
- `1 <= wordsDict[i].length <= 10`
- `wordsDict[i]` consists of lowercase English letters.
- `word1` and `word2` are in `wordsDict`.
- `word1 != word2`
- At most `5000` calls will be made to `shortest`.

---

## Approach: HashMap of Index Lists + Brute-Force Pair Search

Pre-process the word list in the constructor, building a `HashMap` from each word to a sorted list of all its indices. For each `shortest(word1, word2)` query, iterate over all pairs of indices from the two lists and track the minimum absolute difference.

#### Complexity Analysis

- **Time complexity: O(n) constructor, O(p * q) per query.** Where `n` is the dictionary size and `p`, `q` are the occurrence counts of `word1` and `word2` respectively.
- **Space complexity: O(n).** The map stores one entry per word occurrence.

```java
package com.lc;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list
 * of words and your method will be called repeatedly many times with different parameters. How
 * would you optimize it?
 *
 * <p>Design a class which receives a list of words in the constructor, and implements a method that
 * takes two words word1 and word2 and return the shortest distance between these two words in the
 * list.
 *
 * <p>For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * <p>Given word1 = "coding", word2 = "practice", return 3. Given word1 = "makes", word2 = "coding",
 * return 1.
 */
public class LC_0244_ShortedWordDistance2 {
  HashMap<String, ArrayList<Integer>> map;

  public LC_0244_ShortedWordDistance2(String[] words) {
    map = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      if (map.containsKey(words[i])) {
        map.get(words[i]).add(i);
      } else {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(i);
        map.put(words[i], list);
      }
    }
  }

  public int shortest(String word1, String word2) {

    ArrayList<Integer> l1 = map.get(word1);
    ArrayList<Integer> l2 = map.get(word2);

    int result = Integer.MAX_VALUE;
    for (int i1 : l1) {
      for (int i2 : l2) {
        result = Math.min(result, Math.abs(i1 - i2));
      }
    }
    return result;
  }
}
```

**Key insight:** Pre-building the index map amortizes the O(n) construction cost across multiple queries; since index lists are already sorted, a two-pointer merge could further optimize each query to O(p + q), though the brute-force O(p * q) is shown here.
