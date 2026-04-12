---
description: HARD
---

# 23. Merge K Sorted Lists

You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order. Merge all the linked-lists into one sorted linked-list and return it.

**Example 1:**

```text
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
```

**Example 2:**

```text
Input: lists = []
Output: []
```

**Example 3:**

```text
Input: lists = [[]]
Output: []
```

**Constraints:**

- `k == lists.length`
- `0 <= k <= 10^4`
- `0 <= lists[i].length <= 500`
- `-10^4 <= lists[i][j] <= 10^4`
- `lists[i]` is sorted in ascending order
- The sum of `lists[i].length` will not exceed `10^4`

---

## Approach 1: Min-Heap (Priority Queue)

Seed a min-heap with the head node of each non-null list. Repeatedly poll the smallest node, append it to the result, and push its successor (if any) back into the heap. This ensures the globally minimum node is always selected next.

#### Complexity Analysis

- **Time complexity: O(n log k).** Each of the `n` total nodes is pushed and popped from a heap of size at most `k`, costing O(log k) per operation.
- **Space complexity: O(k).** The heap holds at most one node per list.

```java
public class LC_0023_MergeKSortedLists {
  public ListNode mergeKLists(ListNode[] lists) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    Queue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val); // min-heap

    for (final ListNode list : lists) if (list != null) pq.offer(list);

    while (!pq.isEmpty()) {
      ListNode minNode = pq.poll();
      if (minNode.next != null) pq.offer(minNode.next);
      curr.next = minNode;
      curr = curr.next;
    }

    return dummy.next;
  }
}
```

**Key insight:** Only the current front of each list needs to be in the heap at any time. By pushing the successor of a polled node, the heap size stays bounded by `k` and the invariant that the heap always contains the next candidates is maintained.

---

## Approach 2: Divide and Conquer (Pair-wise Merge)

Repeatedly merge pairs of lists — similar to merge sort — halving the number of lists each pass until one sorted list remains.

#### Complexity Analysis

- **Time complexity: O(n log k).** There are O(log k) passes, and each pass merges O(n) total nodes.
- **Space complexity: O(1).** Merging is done in-place.

```java
// public class LC_0023_MergeKSortedLists {
//    public ListNode mergeKLists(ListNode[] lists) {
//        int length = lists.length;
//        for (int processed = 1; processed < length; processed *= 2) {
//            for (int i = 0; i < length - processed; i = processed * 2) {
//                lists[i] = mergeTwoLists(lists[i], lists[i + processed]);
//            }
//        }
//        return length > 0 ? lists[0] : null;
//    }
//
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode dummy = new ListNode();
//        ListNode curr = dummy;
//        while (l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                curr.next = l1;
//                l1 = l1.next;
//            } else {
//                curr.next = l2;
//                l2 = l2.next;
//            }
//            curr = curr.next;
//        }
//        curr.next = l1 != null ? l1 : l2;
//        return dummy.next;
//    }
// }
```

**Key insight:** Divide and conquer avoids the overhead of a heap by reusing the two-list merge from problem 21, achieving the same O(n log k) time with lower constant factors and O(1) extra space.
