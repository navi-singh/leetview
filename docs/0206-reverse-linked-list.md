---
description: EASY
---

# 206. Reverse Linked List

Given the `head` of a singly linked list, reverse the list, and return the reversed list.

**Example 1:**

```text
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

**Example 2:**

```text
Input: head = [1,2]
Output: [2,1]
```

**Example 3:**

```text
Input: head = []
Output: []
```

**Constraints:**

- The number of nodes in the list is the range `[0, 5000]`.
- `-5000 <= Node.val <= 5000`

**Follow up:** A linked list can be reversed either iteratively or recursively. Could you implement both?

---

## Approach: Iterative with Sentinel

A sentinel node's `next` pointer is used as a safe holder for the remainder of the list while reversing. `prev` tracks the already-reversed portion. In each iteration the current node is detached from the front of the remaining list and prepended to the reversed portion, until only the new tail remains, at which point its `next` is linked to `prev`.

#### Complexity Analysis

- **Time complexity: O(n).** Each node is visited exactly once.
- **Space complexity: O(1).** Only a fixed number of extra pointers are used.

```java
public class LC_0206_ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode temp = new ListNode();
    temp.next = head;
    ListNode prev = null;
    while (head.next != null) {
      temp.next = head.next;
      head.next = prev;
      prev = head;
      head = temp.next;
    }
    temp.next.next = prev;
    return temp.next;
  }
}
```

**Key insight:** The sentinel's `next` always holds the front of the unprocessed list, letting the loop safely redirect `head.next` to `prev` without losing the rest of the chain.
