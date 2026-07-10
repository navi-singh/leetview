---
description: MEDIUM
---

# 19. Remove Nth Node From End of List

Given the `head` of a linked list, remove the `n`th node from the end of the list and return its head.

**Example 1:**

```text
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
```

**Example 2:**

```text
Input: head = [1], n = 1
Output: []
```

**Example 3:**

```text
Input: head = [1,2], n = 1
Output: [1]
```

**Constraints:**

- The number of nodes in the list is `sz`.
- `1 <= sz <= 30`
- `0 <= Node.val <= 100`
- `1 <= n <= sz`

**Follow up:** Could you do this in one pass?

---

## Approach 1: Two Pointers with Dummy Head (One Pass)

Use a dummy node prepended before `head` to handle edge cases (such as removing the head itself). Advance the `curr` pointer `n + 1` steps ahead of `node`. Then move both pointers forward in lockstep until `curr` is null. At that point `node` points to the node just before the one to remove. Set `node.next = node.next.next` to unlink the target node.

#### Complexity Analysis

- **Time complexity: O(sz).** The list is traversed exactly once.
- **Space complexity: O(1).** Only two additional pointers are used.

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) return head;
    ListNode dummyNode = new ListNode(0);
    dummyNode.next = head;
    ListNode curr = dummyNode, node = dummyNode;
    for (int i = 0; i <= n; i++) curr = curr.next;
    while (curr != null) {
        node = node.next;
        curr = curr.next;
    }
    node.next = node.next.next;
    return dummyNode.next;
}
```

**Key insight:** By initializing both pointers at the dummy node and advancing `curr` exactly `n + 1` steps before starting the parallel traversal, `node` naturally stops at the predecessor of the node to remove — no separate length calculation or second pass is needed.
