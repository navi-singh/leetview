---
description: MEDIUM
---

# 146. LRU Cache

Design a data structure that follows the constraints of a **Least Recently Used (LRU) cache**.

Implement the `LRUCache` class:

- `LRUCache(int capacity)` Initialize the LRU cache with **positive** size `capacity`.
- `int get(int key)` Return the value of the `key` if it exists, otherwise return `-1`.
- `void put(int key, int value)` Update the value of the `key` if it exists. Otherwise, add the `key-value` pair to the cache. If the number of keys exceeds the `capacity` from this operation, **evict** the least recently used key.

The functions `get` and `put` must each run in `O(1)` average time complexity.

**Example 1:**

```text
Input:
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output:
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation:
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
```

**Constraints:**

- `1 <= capacity <= 3000`
- `0 <= key <= 10^4`
- `0 <= value <= 10^5`
- At most `2 * 10^5` calls will be made to `get` and `put`.

---

## Approach: HashMap + Doubly Linked List

Maintain a HashMap for O(1) key lookup and a doubly linked list to track recency order. The head of the list is the least recently used node and the tail is the most recently used. On `get` or `put`, move the accessed node to the tail. On eviction, remove from the head.

#### Complexity Analysis

- **Time complexity: O(1)** for both `get` and `put`. HashMap provides O(1) lookup; linked list node insertion and deletion are O(1) with direct pointer access.
- **Space complexity: O(capacity).** At most `capacity` nodes are stored in both the map and the list.

```java
package com.lc;

import java.util.HashMap;

public class LC_0146_LruCache {

  HashMap<Integer, Node> map = null;
  int cap = 0;
  Node head, tail;

  public LC_0146_LruCache(int capacity) {
    this.map = new HashMap<Integer, Node>();
    this.cap = capacity;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Node n = map.get(key);
      removeNode(n);
      addNode(n);
      return n.val;
    }
    return -1;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      Node n = map.get(key);
      n.val = value;
      removeNode(n);
      addNode(n);
    } else {
      if (map.size() >= this.cap) {
        map.remove(head.key);
        removeNode(head);
      }
      Node n = new Node(key, value);
      addNode(n);
      map.put(key, n);
    }
  }

  private void removeNode(Node n) {
    if (n.prev == null) {
      head = n.next;
    } else {
      n.prev.next = n.next;
    }
    if (n.next == null) {
      tail = n.prev;
    } else {
      n.next.prev = n.prev;
    }
  }

  private void addNode(Node n) {
    if (tail != null) {
      tail.next = n;
    }
    n.prev = tail;
    n.next = null;
    tail = n;
    if (head == null) {
      head = n;
    }
  }

  class Node {
    int val;
    Node prev;
    Node next;
    int key;

    Node(int key, int val) {
      this.key = key;
      this.val = val;
    }
  }
}
```

**Key insight:** Storing the key inside each linked list node is essential — without it, when evicting the head node you cannot remove the corresponding entry from the HashMap in O(1).
