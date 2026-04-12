---
description: EASY
---

# 225. Implement Stack using Queues

Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (`push`, `top`, `pop`, and `empty`).

Implement the `MyStack` class:

- `void push(int x)` Pushes element x to the top of the stack.
- `int pop()` Removes the element on the top of the stack and returns that element.
- `int top()` Returns the element on the top of the stack.
- `boolean empty()` Returns `true` if the stack is empty, `false` otherwise.

**Notes:**

- You must use **only** standard operations of a queue, which means that only `push to back`, `peek/pop from front`, `size` and `is empty` operations are valid.
- Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.

**Example 1:**

```text
Input:
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output:
[null, null, null, 2, 2, false]
Explanation:
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top();   // return 2
myStack.pop();   // return 2
myStack.empty(); // return False
```

**Constraints:**

- `1 <= x <= 9`
- At most `100` calls will be made to `push`, `pop`, `top`, and `empty`.
- All the calls to `pop` and `top` are valid.

---

## Approach: Single Queue with Rotation on Push

Use a single queue. On every `push`, enqueue the new element and then rotate all previously enqueued elements to the back. This keeps the most recently pushed element at the front of the queue, making `pop` and `top` O(1).

#### Complexity Analysis

- **Time complexity: O(n) for push, O(1) for pop/top/empty.** Each push rotates all existing elements to the back of the queue.
- **Space complexity: O(n).** The queue holds all stack elements.

```java
package com.lc;

import java.util.LinkedList;
import java.util.Queue;

public class LC_0225_StackQueues {
  Queue<Integer> st;

  /** Initialize your data structure here. */
  public LC_0225_StackQueues() {
    st = new LinkedList<Integer>();
  }

  /** Push element x onto stack. */
  public void push(int x) {
    int size = st.size();
    st.add(x);
    for (int i = 0; i < size; i++) {
      Integer elem = st.poll();
      st.add(elem);
    }
  }

  /** Removes the element on top of the stack and returns that element. */
  public int pop() {
    if (st.isEmpty()) {
      return -1;
    }
    return Integer.valueOf(st.poll().toString());
  }

  /** Get the top element. */
  public int top() {
    return st.peek();
  }

  /** Returns whether the stack is empty. */
  public boolean empty() {
    return st.isEmpty();
  }
}
```

**Key insight:** By rotating all old elements behind the newly pushed element, the queue's front always corresponds to the stack's top, converting O(1) queue-front operations into O(1) stack operations at the cost of an O(n) push.
