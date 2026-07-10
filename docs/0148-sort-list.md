---
description: MEDIUM
---

# 148. Sort List

Given the `head` of a linked list, return the list after sorting it in **ascending order**.

**Example 1:**

```text
Input: head = [4,2,1,3]
Output: [1,2,3,4]
```

**Example 2:**

```text
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
```

**Constraints:**

- The number of nodes in the list is in the range `[0, 5 * 10^4]`.
- `-10^5 <= Node.val <= 10^5`

---

## Approach: Merge Sort (Top-Down Recursive)

1. **Find the midpoint** of the list using slow/fast pointers, then split the list into two halves.
2. **Recursively sort** each half.
3. **Merge** the two sorted halves using recursive merge.

#### Complexity Analysis

- **Time complexity: O(n log n).** The list is halved log n times, and merging at each level takes O(n) total.
- **Space complexity: O(log n).** The recursion stack depth is O(log n) for the divide phase; the merge helper also adds O(log n) recursion depth.

```java
package com.lc;

public class LC_0148_SortList {
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode slow = head, fast = head, pre = head;
    while (fast != null && fast.next != null) {
      pre = slow;
      fast = fast.next.next;
      slow = slow.next;
    }
    pre.next = null;
    return merge(sortList(head), sortList(slow));
  }

  private ListNode merge(ListNode first, ListNode sec) {
    if (first == null) return sec;
    if (sec == null) return first;
    if (first.val < sec.val) {
      first.next = merge(first.next, sec);
      return first;
    } else {
      sec.next = merge(first, sec.next);
      return sec;
    }
  }
}
```

**Key insight:** Tracking `pre` (the node before `slow`) during the midpoint search allows severing the list in-place without needing an extra pass; setting `pre.next = null` cleanly splits the list into two independent halves.
