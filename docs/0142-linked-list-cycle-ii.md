---
description: MEDIUM
---

# 142. Linked List Cycle II

Given the `head` of a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer. Internally, `pos` is used to denote the index of the node that tail's `next` pointer is connected to (**0-indexed**). It is `-1` if there is no cycle. **Note that** `pos` **is not passed as a parameter**.

**Do not modify** the linked list.

**Example 1:**

```text
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

**Example 2:**

```text
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

**Example 3:**

```text
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
```

**Constraints:**

- The number of nodes in the list is in the range `[0, 10^4]`.
- `-10^5 <= Node.val <= 10^5`
- `pos` is `-1` or a valid index in the linked list.

---

## Approach: Floyd's Cycle Detection + Intersection Reset

**Phase 1:** Use slow and fast pointers to detect the meeting point inside the cycle (standard Floyd's algorithm).

**Phase 2:** Reset `fast` to `head`. Move both `slow` and `fast` one step at a time. The node where they meet again is the cycle entry point.

#### Complexity Analysis

- **Time complexity: O(n).** Both phases are linear in the number of nodes.
- **Space complexity: O(1).** Only pointer variables are used.

```java
package com.lc;

public class LC_0142_ListCycle2 {
  public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;
    boolean hasCycle = false;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        hasCycle = true;
        break;
      }
    }
    if (!hasCycle) {
      return null;
    }
    fast = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow;
  }
}
```

**Key insight:** The mathematical proof shows that the distance from `head` to the cycle entry equals the distance from the meeting point to the cycle entry when traversing the cycle, so resetting one pointer to `head` and stepping both by one will make them converge at the entry node.
