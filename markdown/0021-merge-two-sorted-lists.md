---
description: EASY
---

# 21. Merge Two Sorted Lists

You are given the heads of two sorted linked lists `list1` and `list2`. Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists. Return the head of the merged linked list.

**Example 1:**

```text
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
```

**Example 2:**

```text
Input: list1 = [], list2 = []
Output: []
```

**Example 3:**

```text
Input: list1 = [], list2 = [0]
Output: [0]
```

**Constraints:**

- The number of nodes in both lists is in the range `[0, 50]`
- `-100 <= Node.val <= 100`
- Both `list1` and `list2` are sorted in non-decreasing order

---

## Approach 1: Iterative with Dummy Node

Use a dummy head node and a `curr` pointer to build the merged list. At each step, compare the front nodes of `l1` and `l2`, attach the smaller one to `curr`, and advance that list's pointer. Once one list is exhausted, append the remainder of the other.

#### Complexity Analysis

- **Time complexity: O(m + n).** Every node from both lists is visited exactly once.
- **Space complexity: O(1).** The merge is done in-place by relinking existing nodes; only a constant number of pointers are used.

```java
public class LC_0021_MergeTwoLists {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode();
    ListNode curr = dummy;
    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        curr.next = l1;
        l1 = l1.next;
      } else {
        curr.next = l2;
        l2 = l2.next;
      }
      curr = curr.next;
    }
    curr.next = l1 != null ? l1 : l2;
    return dummy.next;
  }
}
```

**Key insight:** The dummy node eliminates the edge case of initializing the head of the result list, letting the loop handle all nodes uniformly. Appending the non-null remainder at the end avoids a second loop.
