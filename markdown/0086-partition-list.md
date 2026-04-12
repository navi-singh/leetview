---
description: MEDIUM
---

# 86. Partition List

Given the `head` of a linked list and a value `x`, partition it such that all nodes less than `x` come before nodes greater than or equal to `x`.

You should preserve the original relative order of the nodes in each of the two partitions.

**Example 1:**

```text
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
```

**Example 2:**

```text
Input: head = [2,1], x = 2
Output: [1,2]
```

**Constraints:**

- The number of nodes in the list is in the range `[0, 200]`
- `-100 <= Node.val <= 100`
- `-200 <= x <= 200`

---

## Approach 1: Two-List Partition

Two dummy-headed sublists are built simultaneously: `smallNodes` collects all nodes with `val < x` in their original relative order, and `restNodes` collects all remaining nodes. A single pass over the original list routes each node to the appropriate sublist. After the traversal, the tail of `restNodes` is nulled (to avoid a cycle) and the two sublists are concatenated by pointing `smallNodes.next` to `remaining.next` (the actual head of the rest list).

#### Complexity Analysis

- **Time complexity: O(n).** Every node is visited exactly once.
- **Space complexity: O(1).** Only a constant number of pointer variables are used; nodes are relinked in place.

```java
public ListNode partition(ListNode head, int x) {
    ListNode smallNodes = new ListNode(-1);
    ListNode restNodes = new ListNode(-1);
    ListNode newHead = smallNodes, remaining = restNodes;
    while (head != null) {
        if (head.val < x) {
            smallNodes.next = head;
            smallNodes = smallNodes.next;
        } else {
            restNodes.next = head;
            restNodes = restNodes.next;
        }
        head = head.next;
    }
    smallNodes.next = remaining.next;
    restNodes.next = null;
    return newHead.next;
}
```

**Key insight:** Using two separate dummy-headed lists lets you append to each partition in O(1) while naturally preserving relative order, then joining them requires only a single pointer assignment.
