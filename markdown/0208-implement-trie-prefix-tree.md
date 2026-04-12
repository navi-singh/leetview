---
description: MEDIUM
---

# 208. Implement Trie (Prefix Tree)

A **trie** (pronounced as "try") or **prefix tree** is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the `Trie` class:

- `Trie()` Initializes the trie object.
- `void insert(String word)` Inserts the string `word` into the trie.
- `boolean search(String word)` Returns `true` if the string `word` is in the trie (i.e., was inserted before), and `false` otherwise.
- `boolean startsWith(String prefix)` Returns `true` if there is a previously inserted string that has the prefix `prefix`, and `false` otherwise.

**Example 1:**

```text
Input:
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output:
[null, null, true, false, true, null, true]

Explanation:
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
```

**Constraints:**

- `1 <= word.length, prefix.length <= 2000`
- `word` and `prefix` consist only of lowercase English letters.
- At most `3 * 10^4` calls in total will be made to `insert`, `search`, and `startsWith`.

---

## Approach: HashMap-based Trie Nodes

Each `TrieNode` holds a character value, a `HashMap` of children keyed by character, and an `isLeaf` flag. `insert` walks the trie level by level, creating new nodes as needed and marking the last node as a leaf. `search` delegates to a helper that traverses to the last character's node and checks `isLeaf`. `startsWith` uses the same helper and returns whether the node exists at all.

#### Complexity Analysis

- **Time complexity: O(m) per operation**, where `m` is the length of the word or prefix. Each character is looked up in O(1) via the HashMap.
- **Space complexity: O(n * m).** In the worst case, every character of every inserted word is stored in a separate node.

```java
public class LC_0208_ImplementTrie {
  class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;

    public TrieNode() {}

    public TrieNode(char c) {
      this.c = c;
    }
  }

  TrieNode root;

  /** Initialize your data structure here. */
  public void TrieNode() {
    root = new TrieNode();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    HashMap<Character, TrieNode> children = root.children;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      TrieNode t;
      if (children.containsKey(c)) {
        t = children.get(c);
      } else {
        t = new TrieNode(c);
        children.put(c, t);
      }
      children = t.children;
      if (i == word.length() - 1) {
        t.isLeaf = true;
      }
    }
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    TrieNode node = getNodeIfExist(word);
    if (node == null || !node.isLeaf) {
      return false;
    }
    return true;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    TrieNode node = getNodeIfExist(prefix);
    return node != null ? true : false;
  }

  private TrieNode getNodeIfExist(String word) {
    HashMap<Character, TrieNode> children = root.children;
    TrieNode t = null;
    for (int i = 0; i < word.length(); i++) {
      if (!children.containsKey(word.charAt(i))) {
        return null;
      }
      t = children.get(word.charAt(i));
      children = t.children;
    }
    return t;
  }
}
```

**Key insight:** The `isLeaf` flag on each node distinguishes a complete word from a mere prefix, allowing `search` and `startsWith` to share the same traversal helper while returning different results.
