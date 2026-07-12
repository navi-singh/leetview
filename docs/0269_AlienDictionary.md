# 0269\_AlienDictionary

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

## In Plain Terms

Infer a valid letter order from words that are already sorted according to an alien alphabet. Compare neighboring words to discover ordering rules, then topologically sort those rules; return an empty string if they contradict each other.

```text
wrt vs wrf  -> t before f
wrf vs er   -> w before e
er  vs ett  -> r before t
ett vs rftt -> e before r
one order: w -> e -> r -> t -> f
```

> Example 1: Given the following words in dictionary\
> \[ "wrt", "wrf", "er", "ett", "rftt" ]

The correct order is: "wertf".

Example 2: Given the following words in dictionary,

\[ "z", "x" ]

The correct order is: "zx".

Example 3: Given the following words in dictionary,

\[ "z", "x", "z" ]

The order is invalid, so return "".

Note:

You may assume all letters are in lowercase. You may assume that if a is a prefix of b, then a must appear before b in the given dictionary. If the order is invalid, return an empty string. There may be multiple valid order of letters, return any one of them is fine.

_**Time: O(26+∣words∣−1)**_\
&#xNAN;_**Space: O(26+∣words∣−1)**_

```java
class Solution {
  public String alienOrder(String[] words) {
    Map<Character, Set<Character>> graph = new HashMap<>();
    int[] inDegree = new int[26];

    buildGraph(graph, words, inDegree);

    return bfs(graph, inDegree);
  }

  private void buildGraph(Map<Character, Set<Character>> graph, String[] words, int[] inDegree) {
    // create node for each character in each word
    for (final String word : words)
      for (final char c : word.toCharArray())
        graph.putIfAbsent(c, new HashSet<>());

    for (int i = 1; i < words.length; ++i) {
      final String first = words[i - 1];
      final String second = words[i];
      final int length = Math.min(first.length(), second.length());
      for (int j = 0; j < length; ++j) {
        final char u = first.charAt(j);
        final char v = second.charAt(j);
        if (u != v) {
          if (!graph.get(u).contains(v)) {
            graph.get(u).add(v);
            ++inDegree[v - 'a'];
          }
          break; // later characters' order are meaningless
        }
        // first = "ab", second = "a" -> invalid
        if (j == length - 1 && first.length() > second.length()) {
          graph.clear();
          return;
        }
      }
    }
  }

  private String bfs(Map<Character, Set<Character>> graph, int[] inDegree) {
    StringBuilder sb = new StringBuilder();
    Queue<Character> q = new LinkedList<>();

    for (final char c : graph.keySet())
      if (inDegree[c - 'a'] == 0)
        q.offer(c);

    while (!q.isEmpty()) {
      final char u = q.poll();
      sb.append(u);
      for (final char v : graph.get(u))
        if (--inDegree[v - 'a'] == 0)
          q.offer(v);
    }

    // words = ["z", "x", "y", "x"]
    return sb.length() == graph.size() ? sb.toString() : "";
  }
}
```
