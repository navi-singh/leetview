import java.util.HashMap;

public class LC208_ImplementTrie {
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
  public Trie() {
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
