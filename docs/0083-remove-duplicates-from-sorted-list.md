---
description: EASY
---

# 83. Remove Duplicates from Sorted List

Given the `head` of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

**Example 1:**

```text
Input: head = [1,1,2]
Output: [1,2]
```

**Example 2:**

```text
Input: head = [1,1,2,3,3]
Output: [1,2,3]
```

**Constraints:**

- The number of nodes in the list is in the range `[0, 300]`
- `-100 <= Node.val <= 100`
- The list is guaranteed to be sorted in ascending order

---

## Approach 1: Two-Pointer In-Place Removal

A `head` pointer serves as the write cursor (the last accepted unique node) and a `next` pointer scans ahead. Whenever `head.val != next.val`, we link `head.next = next` and advance `head`. After the loop, a final check nulls out `head.next` if the last segment was a duplicate run (i.e., `head.next` still points to a node with the same value). The original head of the list is saved in `temp` so it can be returned.

#### Complexity Analysis

- **Time complexity: O(n).** The `next` pointer visits every node exactly once.
- **Space complexity: O(1).** No extra data structures are allocated.

```java
public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode temp = head;
    ListNode next = head.next;

    while (next != null) {
        if (head.val != next.val) {
            head.next = next;
            head = head.next;
        }
        next = next.next;
    }
    if (head.next != null && head.val == head.next.val) {
        head.next = null;
    }
    return temp;
}
```

**Key insight:** Because the list is sorted, duplicates are always adjacent, so a single linear pass comparing adjacent nodes is sufficient to remove them all without needing extra storage.
