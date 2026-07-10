---
description: MEDIUM
---

# 24. Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed).

**Example 1:**

```text
Input: head = [1,2,3,4]
Output: [2,1,4,3]
```

**Example 2:**

```text
Input: head = []
Output: []
```

**Example 3:**

```text
Input: head = [1]
Output: [1]
```

**Constraints:**

- The number of nodes in the list is in the range `[0, 100]`
- `0 <= Node.val <= 100`

---

## Approach 1: Iterative with Dummy Node

A dummy node is prepended and a `prev` pointer tracks the node just before the current pair. For each pair (`head`, `head.next`), the second node is promoted before the first by relinking three pointers, then `prev` and `head` advance past the swapped pair.

#### Complexity Analysis

- **Time complexity: O(n).** Each node is visited and relinked exactly once.
- **Space complexity: O(1).** Only a fixed number of pointer variables are used.

```java
public class LC_0024_SwapPairsInList {
  public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode();
    dummy.next = head;
    ListNode prev = dummy;
    while (head != null && head.next != null) {
      ListNode temp = head.next.next;
      prev.next = head.next;
      head.next.next = head;
      head.next = temp;
      prev = head;
      head = temp;
    }
    return dummy.next;
  }
}
```

**Key insight:** Saving `head.next.next` in `temp` before any relinking prevents losing the reference to the rest of the list. After the swap, `head` (now the second node in the pair) becomes the new `prev`, and `temp` becomes the new `head`.
