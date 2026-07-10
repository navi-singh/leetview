---
description: MEDIUM
---

# 92. Reverse Linked List II

Given the `head` of a singly linked list and two integers `left` and `right` where `left <= right`, reverse the nodes of the list from position `left` to position `right`, and return the reversed list.

**Example 1:**

```text
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
```

**Example 2:**

```text
Input: head = [5], left = 1, right = 1
Output: [5]
```

**Constraints:**

- The number of nodes in the list is `n`
- `1 <= n <= 500`
- `-500 <= Node.val <= 500`
- `1 <= left <= right <= n`

---

## Approach 1: In-Place Insertion Reversal

A dummy node is prepended to handle the case where reversal starts at the head. The algorithm walks `head` to the node just before position `left` (the "anchor" node). Then, for each step of the reversal, the node immediately after `curr` (the first unreversed node) is extracted and inserted right after the anchor `head`. This is repeated `right - left` times. No extra list is allocated; the reversal happens by re-linking nodes one at a time.

#### Complexity Analysis

- **Time complexity: O(n).** The walk to position `left` and the reversal together visit at most `n` nodes.
- **Space complexity: O(1).** Only a constant number of pointer variables are used.

```java
public ListNode reverseBetween(ListNode head, int m, int n) {
    if (head == null) {
        return head;
    }
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    head = dummy;
    int num = 1;
    ListNode prev = null;
    while (head != null && num++ < m) {
        head = head.next;
    }
    ListNode curr = head.next;
    while (curr != null && num <= n) {
        ListNode nex = curr.next;
        curr.next = nex.next;
        nex.next = head.next;
        head.next = nex;
        num++;
    }
    return dummy.next;
}
```

**Key insight:** By repeatedly pulling the node just after `curr` and inserting it at the front of the reversed segment (right after the anchor), the sublist is reversed in-place without needing a second traversal or auxiliary storage.
