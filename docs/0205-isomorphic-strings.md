---
description: EASY
---

# 205. Isomorphic Strings

Given two strings `s` and `t`, determine if they are isomorphic.

Two strings `s` and `t` are isomorphic if the characters in `s` can be replaced to get `t`.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

**Example 1:**

```text
Input: s = "egg", t = "add"
Output: true
```

**Example 2:**

```text
Input: s = "foo", t = "bar"
Output: false
```

**Example 3:**

```text
Input: s = "paper", t = "title"
Output: true
```

**Constraints:**

- `1 <= s.length <= 5 * 10^4`
- `t.length == s.length`
- `s` and `t` consist of any valid ASCII character.

---

## Approach: Single Map + Value Uniqueness Check

Build a mapping from characters in `s` to characters in `t`. If a character in `s` is already mapped, verify the mapping is consistent. After processing all characters, confirm that the mapped values are all unique (no two `s`-characters map to the same `t`-character) by comparing the map's value collection size to a set of those values.

#### Complexity Analysis

- **Time complexity: O(n).** One pass to build the map, one pass to check uniqueness of values.
- **Space complexity: O(1).** The map holds at most 256 entries (ASCII character set).

```java
public class LC_0205_IsomorphicStrings {
  public boolean isIsomorphic(String s, String t) {
    if (s == null || t == null || s.length() != t.length()) {
      return false;
    }
    Map<Character, Character> sToTMap = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      if (sToTMap.containsKey(s.charAt(i))) {
        if (sToTMap.get(s.charAt(i)) != t.charAt(i)) {
          return false;
        }
      } else {
        sToTMap.put(s.charAt(i), t.charAt(i));
      }
    }

    Set<Character> set = new HashSet<>(sToTMap.values());
    if (set.size() != sToTMap.values().size()) {
      return false;
    }
    return true;
  }
}
```

**Key insight:** Checking that the values of the `s→t` map are all distinct enforces the bijection requirement—without it, two different `s`-characters could incorrectly map to the same `t`-character.
