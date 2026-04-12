---
description: MEDIUM
---

# 237. Delete Node in a Linked List

There is a singly-linked list `head` and we want to delete a node `node` in it.

You are given the node to be deleted `node`. You will **not be given access to** the first node of `head`.

All the values of the linked list are **unique**, and it is guaranteed the given node `node` is not the last node in the linked list.

Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:

- The value of the given node should not exist in the linked list.
- The number of nodes in the linked list should decrease by one.
- All the values before `node` should be in the same order.
- All the values after `node` should be in the same order.

**Example 1:**

```text
Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
```

**Example 2:**

```text
Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
```

**Constraints:**

- The number of nodes in the given list is in the range `[2, 1000]`.
- `-1000 <= Node.val <= 1000`
- The value of each node in the list is **unique**.
- The `node` to be deleted is **in the list** and is **not a tail** node.

---

## Approach: Copy Next Node's Value

Since we only have access to the node to be deleted (not its predecessor), copy the value of the next node into the current node and then skip the next node by pointing `node.next` to `node.next.next`.

#### Complexity Analysis

- **Time complexity: O(1).** Two pointer assignments and one value copy.
- **Space complexity: O(1).** No extra space used.

```java
package com.lc;

public class LC_0237_DeleteNodeInLinkedList {
  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }
}
```

**Key insight:** Without a reference to the previous node we cannot unlink the target, so instead we overwrite the target with its successor's data and unlink the successor — effectively erasing the target's identity from the list.
