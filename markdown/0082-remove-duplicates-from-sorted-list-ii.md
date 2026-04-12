---
description: MEDIUM
---

# 82. Remove Duplicates from Sorted List II

Given the `head` of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.

**Example 1:**

```text
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]
```

**Example 2:**

```text
Input: head = [1,1,1,2,3]
Output: [2,3]
```

**Constraints:**

- The number of nodes in the list is in the range `[0, 300]`
- `-100 <= Node.val <= 100`
- The list is guaranteed to be sorted in ascending order

---

## Approach 1: Sentinel Node with Duplicate Flag

A sentinel (dummy) node is prepended so that even if the head itself is a duplicate it can be skipped cleanly. A `prev` pointer tracks the last confirmed unique node. A `dups` boolean flag records whether the current run of identical values has length > 1. We advance a `next` pointer through the list: when `next.val` equals `prev.next.val` we mark `dups = true`; when they differ and `dups` is set, we skip the entire duplicate run by pointing `prev.next` directly to `next`. After the loop ends we handle the edge case where the final run was duplicated by setting `prev.next = null`.

#### Complexity Analysis

- **Time complexity: O(n).** Each node is visited exactly once.
- **Space complexity: O(1).** Only a constant number of pointers and a boolean are maintained.

```java
public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode temp = new ListNode(-1);
    temp.next = head;
    ListNode prev = temp;
    ListNode next = head.next;
    boolean dups = false;
    while (next != null) {
        if (next.val == prev.next.val) {
            dups = true;
        } else if (next.val != prev.next.val) {
            if (dups) {
                prev.next = next;
                dups = false;
            } else {
                prev = prev.next;
            }
        }
        next = next.next;
    }
    if (dups) {
        prev.next = null;
    }
    return temp.next;
}
```

**Key insight:** The `dups` flag lets us defer the skip decision until we have seen the first non-duplicate value after a run, making it straightforward to excise the entire duplicate group in one pointer reassignment.
