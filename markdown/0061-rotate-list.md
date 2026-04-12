---
description: MEDIUM
---

# 61. Rotate List

Given the `head` of a linked list, rotate the list to the right by `k` places.

**Example 1:**

```text
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Explanation: Rotating right by 1 gives [5,1,2,3,4]. Rotating right by 2 gives [4,5,1,2,3].
```

**Example 2:**

```text
Input: head = [0,1,2], k = 4
Output: [2,0,1]
```

**Constraints:**

- The number of nodes in the list is in the range `[0, 500]`
- `-100 <= Node.val <= 100`
- `0 <= k <= 2 * 10^9`

---

## Approach 1: Circular List + Find New Tail

First, compute the length of the list and form a circular list by connecting the tail to the head. Then determine the effective rotation amount `k % len`. The new tail is at position `len - k % len - 1` from the original head. Break the circle at that point and return the new head.

#### Complexity Analysis

- **Time complexity: O(n).** We traverse the list once to find the length and once more to find the new tail.
- **Space complexity: O(1).** Only a constant number of pointers are used.

```java
public class LC_0061_RotateList {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null) return head;
    ListNode iter = head;
    int len = 1;
    while (iter.next != null) {
      iter = iter.next;
      len++;
    }
    iter.next = head;
    k = len - k % len;
    while (k-- > 0) {
      iter = iter.next;
    }
    head = iter.next;
    iter.next = null;
    return head;
  }
}
```

**Key insight:** Forming a circular list allows us to avoid edge cases with wrapping. The effective number of steps is `len - k % len` steps from the old tail, which lands exactly at the new tail.
