---
description: HARD
---

# 25. Reverse Nodes in k-Group

Given the head of a linked list, reverse the nodes of the list `k` at a time, and return the modified list. `k` is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of `k` then left-out nodes, in the end, should remain as is. You may not alter the values in the list's nodes, only nodes themselves may be changed.

**Example 1:**

```text
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
```

**Example 2:**

```text
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
```

**Example 3:**

```text
Input: head = [1,2,3,4,5], k = 1
Output: [1,2,3,4,5]
```

**Example 4:**

```text
Input: head = [1], k = 1
Output: [1]
```

**Constraints:**

- The number of nodes in the list is `n`
- `1 <= k <= n <= 5000`
- `0 <= Node.val <= 1000`

---

## Approach 1: Iterative In-Place Reversal

Iterate through the list while counting nodes. Every time the counter is a multiple of `k`, call a helper that reverses the segment between `prev` (exclusive) and `curr.next` (exclusive) using a front-insertion technique. The helper returns the new tail of the reversed segment, which becomes the next `prev`.

#### Complexity Analysis

- **Time complexity: O(n).** Each node is moved at most once during the reversal of its group.
- **Space complexity: O(1).** All relinking is done in-place with a constant number of pointers.

```java
public class LC_0025_ReverseNodesKGroup {

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null) return head;
    ListNode dummy = new ListNode(0);
    ListNode prev = dummy;
    dummy.next = head;
    ListNode curr = head;
    for (int i = 1; curr != null; ++i) {
      if (i % k == 0) {
        prev = reverseList(prev, curr.next);
        curr = prev.next;
      } else curr = curr.next;
    }
    return dummy.next;
  }

  private ListNode reverseList(ListNode prev, ListNode next) {
    ListNode curr = prev.next;
    ListNode tempNext = curr.next;
    while (tempNext != next) {
      curr.next = tempNext.next;
      tempNext.next = prev.next;
      prev.next = tempNext;
      tempNext = curr.next;
    }
    return curr;
  }
}
```

**Key insight:** The `reverseList` helper uses front-insertion: it repeatedly takes the node immediately after `curr` and moves it to just after `prev`, effectively building the reversed group in-place without extra space. The original first node of the group (`curr`) naturally becomes the tail after all insertions.
