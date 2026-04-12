---
description: EASY
---

# 141. Linked List Cycle

Given `head`, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer. Internally, `pos` is used to denote the index of the node that tail's `next` pointer is connected to. Note that `pos` is not passed as a parameter.

Return `true` if there is a cycle in the linked list. Otherwise, return `false`.

**Example 1:**

```text
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
```

**Example 2:**

```text
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
```

**Example 3:**

```text
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
```

**Constraints:**

- The number of the nodes in the list is in the range `[0, 10^4]`.
- `-10^5 <= Node.val <= 10^5`
- `pos` is `-1` or a valid index in the linked list.

---

## Approach: Floyd's Tortoise and Hare (Two Pointers)

Use two pointers: `slow` moves one step at a time and `fast` moves two steps at a time. If there is a cycle, the fast pointer will eventually catch up to the slow pointer. If `fast` reaches `null`, there is no cycle.

#### Complexity Analysis

- **Time complexity: O(n).** In the worst case, the fast pointer travels at most 2n steps before either reaching null or lapping the slow pointer.
- **Space complexity: O(1).** Only two pointer variables are used.

```java
package com.lc;

public class LC_0141_LinkedListCycle {
  public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }
}
```

**Key insight:** In a cycle, the fast pointer gains one step on the slow pointer each iteration; since the cycle is finite, fast will inevitably lap slow and they will meet.
