---
description: MEDIUM
---

# 143. Reorder List

You are given the head of a singly linked-list: `L0 → L1 → … → Ln - 1 → Ln`

Reorder it to: `L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …`

You may not modify the values in the list's nodes. Only nodes themselves may be changed.

**Example 1:**

```text
Input: head = [1,2,3,4]
Output: [1,4,2,3]
```

**Example 2:**

```text
Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]
```

**Constraints:**

- The number of nodes in the list is in the range `[1, 5 * 10^4]`.
- `1 <= Node.val <= 1000`

---

## Approach: Find Middle + Reverse Second Half + Merge

1. **Find the middle** using slow/fast pointers.
2. **Reverse the second half** of the list in-place.
3. **Interleave** the first half and the reversed second half by alternating nodes.

#### Complexity Analysis

- **Time complexity: O(n).** Each of the three phases (find middle, reverse, merge) is a single linear pass.
- **Space complexity: O(1).** All operations are done in-place with pointer manipulation.

```java
package com.lc;

class LC143_ReorderList {

  public void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    ListNode second = slow.next;
    slow.next = null;
    ListNode first = head;
    second = reverseList(second);
    while (second != null) {
      ListNode p1 = first.next;
      ListNode p2 = second.next;
      first.next = second;
      second.next = p1;
      second = p2;
      first = p1;
    }
  }

  private ListNode reverseList(ListNode head) {
    ListNode pre = head, curr = head.next;
    while (curr != null) {
      ListNode temp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = temp;
    }
    head.next = null;
    return pre;
  }
}
```

**Key insight:** Reversing the second half converts the problem of interleaving the front and back of the list into a straightforward two-pointer merge, avoiding the need for extra storage like a stack or array.
