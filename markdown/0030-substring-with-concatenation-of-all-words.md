---
description: HARD
---

# 30. Substring with Concatenation of All Words

You are given a string `s` and an array of strings `words` of the same length. Return all starting indices of substring(s) in `s` that is a concatenation of each word in `words` exactly once, in any order, and without any intervening characters.

You can return the answer in any order.

**Example 1:**

```text
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
```

**Example 2:**

```text
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
```

**Example 3:**

```text
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
```

**Constraints:**

- `1 <= s.length <= 10^4`
- `1 <= words.length <= 5000`
- `1 <= words[i].length <= 30`
- `s` and `words[i]` consist of lowercase English letters

---

## Approach 1: Sliding Window with HashMap

Pre-build a frequency map of all words. For every valid starting index `i` (up to `s.length() - totalLen`), extract word-sized chunks and compare against the frequency map using a temporary map. If all chunks match without exceeding their allowed frequency, record `i`.

#### Complexity Analysis

- **Time complexity: O(n * m * w).** `n` is the length of `s`, `m` is the number of words, and `w` is the word length. For each of the O(n) starting positions we scan O(m) words of length O(w).
- **Space complexity: O(m).** The word frequency map and temporary map each hold at most `m` entries.

```java
public class LC_0030_SubstringWithAllWord {
  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> res = new ArrayList<>();
    if (s.isEmpty() || words.length < 1) return res;

    int wordLen = words[0].length();
    int totalLen = words.length * wordLen;
    Map<String, Integer> wordLookup = new HashMap<>();
    if (totalLen > s.length()) return res;
    for (String word : words) {
      wordLookup.put(word, wordLookup.containsKey(word) ? wordLookup.get(word) + 1 : 1);
    }
    for (int i = 0; i <= (s.length() - totalLen); ++i) {
      Map<String, Integer> temp = new HashMap<String, Integer>();
      int j = 0;
      for (; j < totalLen; j += wordLen) {
        String word = s.substring(i + j, i + j + wordLen);
        if (!wordLookup.containsKey(word)) break;
        int currentCount = temp.getOrDefault(word, 0);
        if (temp.getOrDefault(word, 0) >= wordLookup.get(word)) break;
        temp.put(word, currentCount + 1);
      }
      if (j >= totalLen) res.add(i);
    }
    return res;
  }
}
```

**Key insight:** Since all words have the same length, the string can be viewed as a sequence of fixed-width slots. Checking one slot at a time and breaking early on any mismatch avoids redundant work. The temporary map is rebuilt for each starting position to track per-position word usage.
