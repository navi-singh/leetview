---
description: MEDIUM
---

# 155. Min Stack

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the `MinStack` class:

- `MinStack()` initializes the stack object.
- `void push(int val)` pushes the element `val` onto the stack.
- `void pop()` removes the element on the top of the stack.
- `int top()` gets the top element of the stack.
- `int getMin()` retrieves the minimum element in the stack.

You must implement a solution with `O(1)` time complexity for each function.

**Example 1:**

```text
Input:
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output:
[null,null,null,null,-3,null,0,-2]

Explanation:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
```

**Constraints:**

- `-2^31 <= val <= 2^31 - 1`
- Methods `pop`, `top` and `getMin` operations will always be called on **non-empty** stacks.
- At most `3 * 10^4` calls will be made to `push`, `pop`, `top`, and `getMin`.

---

## Approach: Linked List Node with Cached Minimum

Each node in the custom linked list stores both its value and the minimum of all elements at or below it in the stack. When a new node is pushed, its `min` field is set to `min(new_value, current_top.min)`. This way, `getMin()` is simply `top.min` — a direct field access.

#### Complexity Analysis

- **Time complexity: O(1)** for all four operations. Push, pop, top, and getMin each perform a constant number of pointer dereferences and comparisons.
- **Space complexity: O(n).** Each pushed element allocates one Node that stores its value and cached minimum.

```java
package com.lc;

public class LC_0155_MinStack {
  class MinStack {
    class Node {
      public int val;
      public int min;
      public Node next;

      public Node(int val, int min) {
        this.val = val;
        this.min = min;
      }
    }

    Node top;

    /** initialize your data structure here. */
    public MinStack() {}

    public void push(int x) {
      if (top == null) {
        top = new Node(x, x);
      } else {
        Node temp = new Node(x, top.min > x ? x : top.min);
        temp.next = top;
        top = temp;
      }
    }

    public void pop() {
      if (top == null) {
        return;
      } else {
        Node ret = top.next;
        top.next = null;
        top = ret;
      }
    }

    public int top() {
      if (top == null) {
        return -1;
      }
      return top.val;
    }

    public int getMin() {
      if (top == null) {
        return -1;
      }
      return top.min;
    }
  }
}
```

**Key insight:** Caching the running minimum inside each node rather than maintaining a separate auxiliary stack means the minimum is always available at the current top node without any additional data structure; popping a node automatically "restores" the previous minimum because the node below still holds the correct min for that stack state.
