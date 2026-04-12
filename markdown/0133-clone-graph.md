---
description: MEDIUM
---

# 133. Clone Graph

Given a reference of a node in a **connected** undirected graph, return a **deep copy** (clone) of the graph.

Each node in the graph contains a value (`int`) and a list (`List[Node]`) of its neighbors.

```
class Node {
    public int val;
    public List<Node> neighbors;
}
```

**Test case format:** For simplicity, each node's value is the same as the node's index (1-indexed). The graph is represented in the test case using an adjacency list. An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

**Example 1:**

```text
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
```

**Example 2:**

```text
Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
```

**Example 3:**

```text
Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.
```

**Constraints:**

- The number of nodes in the graph is in the range `[0, 100]`.
- `1 <= Node.val <= 100`
- `Node.val` is unique for each node.
- There are no repeated edges and no self-loops in the graph.
- The graph is connected and all nodes can be visited starting from the given node.

---

## Approach: BFS with HashMap

Use a HashMap to map each original node to its clone. Start BFS from the given node, create its clone immediately, and enqueue it. For each dequeued node, iterate over its neighbors: if a neighbor has not been cloned yet, clone it and enqueue it; then add the cloned neighbor to the current cloned node's neighbor list.

#### Complexity Analysis

- **Time complexity: O(V + E).** Every node and every edge is visited exactly once.
- **Space complexity: O(V).** The HashMap and BFS queue together hold at most V entries.

```java
package com.lc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Node {
  public int val;
  public List<Node> neighbors;

  public Node() {
    val = 0;
    neighbors = new ArrayList<Node>();
  }

  public Node(int _val) {
    val = _val;
    neighbors = new ArrayList<Node>();
  }

  public Node(int _val, ArrayList<Node> _neighbors) {
    val = _val;
    neighbors = _neighbors;
  }
}

public class LC_0133_CloneGraph {
  public Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }
    Map<Node, Node> map = new HashMap<>();
    Queue<Node> queue = new ArrayDeque<>();
    queue.offer(node);
    map.put(node, new Node(node.val, new ArrayList<>()));
    while (!queue.isEmpty()) {
      Node nod = queue.poll();
      for (Node neighbor : nod.neighbors) {
        if (!map.containsKey(neighbor)) {
          map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
          queue.offer(neighbor);
        }
        map.get(nod).neighbors.add(map.get(neighbor));
      }
    }
    return map.get(node);
  }
}
```

**Key insight:** The HashMap serves a dual purpose — it acts as a visited set to prevent infinite loops in cyclic graphs and as the lookup table to wire up neighbor references in the cloned graph.
