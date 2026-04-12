---
description: EASY
---

# 203. Remove Linked List Elements

Given the `head` of a linked list and an integer `val`, remove all the nodes of the linked list that have `Node.val == val`, and return the new head.

**Example 1:**

```text
Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]
```

**Example 2:**

```text
Input: head = [], val = 1
Output: []
```

**Example 3:**

```text
Input: head = [7,7,7,7], val = 7
Output: []
```

**Constraints:**

- The number of nodes in the list is in the range `[0, 10^4]`.
- `1 <= Node.val <= 50`
- `0 <= val <= 50`

---

## Approach: Sentinel Node (Iterative)

A dummy sentinel node is prepended before the head to simplify edge cases where the head itself must be removed. Two pointers—`temp` (the node just before the current) and `head` (the current node)—walk through the list. When a matching value is found, the node is unlinked by updating `temp.next`; otherwise both pointers advance.

#### Complexity Analysis

- **Time complexity: O(n).** Each node is visited exactly once.
- **Space complexity: O(1).** Only a constant number of extra pointers are used.

```java
public class LC_0203_RemoveLinkedListElements {
  public ListNode removeElements(ListNode head, int val) {
    if (head == null) {
      return head;
    }
    ListNode prev = new ListNode();
    prev.next = head;
    ListNode temp = prev;
    while (head != null) {
      if (head.val == val) {
        temp.next = head.next;
        head.next = null;
        head = temp.next;
      } else {
        temp = temp.next;
        head = head.next;
      }
    }
    return prev.next;
  }
}
```

**Key insight:** The sentinel node eliminates special-casing for head deletion—`prev.next` always points to the true new head regardless of how many leading nodes are removed.
