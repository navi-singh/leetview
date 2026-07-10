---
description: EASY
---

# 234. Palindrome Linked List

Given the `head` of a singly linked list, return `true` if it is a palindrome or `false` otherwise.

**Example 1:**

```text
Input: head = [1,2,2,1]
Output: true
```

**Example 2:**

```text
Input: head = [1,2]
Output: false
```

**Constraints:**

- The number of nodes in the list is in the range `[1, 10^5]`.
- `0 <= Node.val <= 9`

---

## Approach: Slow/Fast Pointer + Stack

Use two pointers (`slow` and `fast`) to find the midpoint of the list. While advancing, push the first-half nodes onto a stack. After reaching the midpoint, compare the stack (first half in reverse) against the second half of the list node by node.

#### Complexity Analysis

- **Time complexity: O(n).** One pass to find the midpoint while filling the stack, and one pass to compare the two halves.
- **Space complexity: O(n/2) = O(n).** The stack stores half the list nodes.

```java
package com.lc;

import java.util.Stack;

public class LC_0234_PalindromeLinkedList {
  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    Stack<ListNode> st = new Stack<>();
    ListNode slow, fast;
    slow = fast = head;
    while (fast != null && fast.next != null) {
      st.push(slow);
      slow = slow.next;
      fast = fast.next.next;
    }
    if (fast != null) {
      slow = slow.next;
    }

    while (!st.isEmpty()) {
      ListNode node = st.pop();
      if (node.val != slow.val) {
        return false;
      }
      slow = slow.next;
    }
    return true;
  }
}
```

**Key insight:** Pushing the first half onto a stack naturally reverses it so we can directly compare with the second half; the odd-length adjustment (`if (fast != null) slow = slow.next`) skips the exact middle node which doesn't need to match anything.
