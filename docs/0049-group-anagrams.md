---
description: MEDIUM
---

# 49. Group Anagrams

Given an array of strings `strs`, group the **anagrams** together. You can return the answer in **any order**.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Example 1:**

```text
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```

**Example 2:**

```text
Input: strs = [""]
Output: [[""]]
```

**Example 3:**

```text
Input: strs = ["a"]
Output: [["a"]]
```

**Constraints:**

- `1 <= strs.length <= 10^4`
- `0 <= strs[i].length <= 100`
- `strs[i]` consists of lowercase English letters.

---

## Approach 1: Character Frequency Signature as HashMap Key

For each string, build a 26-element character-count array (one slot per lowercase letter). Convert this array to a `String` and use it as the key in a `HashMap`. All anagrams share the same character frequency profile and therefore produce the same key, grouping them together automatically.

#### Complexity Analysis

- **Time complexity: O(n * k).** Where `n` is the number of strings and `k` is the maximum string length. Building the frequency array for each string takes `O(k)`.
- **Space complexity: O(n * k).** The HashMap stores all strings plus their 26-character keys.

```java
public class LC_0049_GroupAnagrams {
  public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> res = new ArrayList();
    Map<String, List<String>> idenitfierToList = new HashMap<String, List<String>>();
    for (String str : strs) {
      char[] ch = new char[26];
      for (int i = 0; i < str.length(); ++i) {
        ch[str.charAt(i) - 'a']++;
      }
      String identifier = new String(ch);
      if (idenitfierToList.containsKey(identifier)) {
        idenitfierToList.get(identifier).add(str);
      } else {
        List<String> newList = new ArrayList<>();
        newList.add(str);
        idenitfierToList.put(identifier, newList);
      }
    }
    res.addAll(idenitfierToList.values());
    return res;
  }
}
```

**Key insight:** Using a character-frequency array (rather than a sorted string) as the map key avoids the `O(k log k)` sort cost and reduces grouping to a single `O(n * k)` pass.
