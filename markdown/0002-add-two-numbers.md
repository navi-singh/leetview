---
description: MEDIUM
---

# 2. Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in **reverse order**, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number `0` itself.

**Example 1:**

```text
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
```

**Example 2:**

```text
Input: l1 = [0], l2 = [0]
Output: [0]
```

**Example 3:**

```text
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
Explanation: 9999999 + 9999 = 10009998.
```

**Constraints:**

- The number of nodes in each linked list is in the range `[1, 100]`.
- `0 <= Node.val <= 9`
- It is guaranteed that the list represents a number that does not have leading zeros.

---

## Approach: Iterative with Carry (Dummy Head)

Simulate grade-school addition digit by digit. Use a `carry` variable (stored in `num / 10`) that rolls over into the next position. A dummy head node simplifies result list construction — no special-casing for the first node.

**Algorithm:**
1. Create a `dummyHead` node; keep an `iter` pointer to build the result list.
2. While either list has remaining nodes:
   - Add the current digit from `l1` (if not null) and `l2` (if not null) to `num`.
   - Append `num % 10` as the next node; advance `iter`.
   - Set `num = num / 10` to carry the overflow into the next iteration.
3. After the loop, if `num != 0` a final carry remains — append it as the last node.
4. Return `dummyHead.next`.

#### Complexity Analysis

- **Time complexity: O(max(m, n)).** Single pass through both lists, where m and n are their lengths.
- **Space complexity: O(max(m, n)).** The result list has at most max(m, n) + 1 nodes.

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode iter = dummyHead;
    int num = 0;
    while (l1 != null || l2 != null) {
        if (l1 != null) {
            num += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            num += l2.val;
            l2 = l2.next;
        }
        iter.next = new ListNode(num % 10);
        iter = iter.next;
        num /= 10;
    }
    if (num != 0) {
        iter.next = new ListNode(num);
    }
    return dummyHead.next;
}
```

**Key insight:** The `num` variable doubles as both the running digit sum and the carry — `num % 10` gives the digit to store, `num / 10` gives the carry (0 or 1) for the next position. This avoids a separate carry variable.
