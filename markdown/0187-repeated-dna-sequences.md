---
description: MEDIUM
---

# 187. Repeated DNA Sequences

The **DNA sequence** is composed of a series of nucleotides abbreviated as `'A'`, `'C'`, `'G'`, and `'T'`.

- For example, `"ACGAATTCCG"` is a **DNA sequence**.

When studying **DNA**, it is useful to identify repeated sequences within the DNA.

Given a string `s` that represents a **DNA sequence**, return all the **10-letter-long** sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in **any order**.

**Example 1:**

```text
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
```

**Example 2:**

```text
Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]
```

**Constraints:**

- `1 <= s.length <= 10^5`
- `s[i]` is either `'A'`, `'C'`, `'G'`, or `'T'`.

---

## Approach: Rolling Hash with Bit Encoding

Map each nucleotide to a 2-bit value (A=0, C=1, G=2, T=3). Maintain a rolling 20-bit hash for each 10-character window by left-shifting the hash by 2 and ANDing with a 20-bit mask to drop the oldest character. Use two sets — `temp` for hashes seen once and `added` for hashes already added to the result — to detect duplicates without adding them multiple times.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass through the string computes and looks up each 10-character hash in O(1).
- **Space complexity: O(n).** The two hash sets together store at most O(n) entries.

```java
package com.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LC_0187_RepeatedDNASequence {
  public List<String> findRepeatedDnaSequences(String s) {
    List<String> result = new ArrayList<>();
    if (s == null || s.isEmpty()) {
      return result;
    }
    Map<Character, Integer> dict = new HashMap<>();
    dict.put('A', 0);
    dict.put('C', 1);
    dict.put('G', 2);
    dict.put('T', 3);
    int hash = 0;
    int mask = (1 << 20) - 1;

    Set<Integer> added = new HashSet<Integer>();
    Set<Integer> temp = new HashSet<Integer>();
    for (int i = 0; i < s.length(); i++) {
      hash = (hash << 2) + dict.get(s.charAt(i));
      if (i >= 9) {
        hash &= mask;
        if (temp.contains(hash) & !added.contains(hash)) {
          result.add(s.substring(i - 9, i + 1));
          added.add(hash);
        } else {
          temp.add(hash);
        }
      }
    }

    return result;
  }
}
```

**Key insight:** Encoding A/C/G/T as 2-bit values lets a 10-character DNA window fit exactly into a 20-bit integer, making the rolling hash a simple bit-shift operation and enabling O(1) duplicate detection via hash set lookups.
