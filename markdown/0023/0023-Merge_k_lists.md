# [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

**Example 1:** 

```
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

```
Input: lists = []
Output: []
```

**Example 3:** 

```
Input: lists = [[]]
Output: []
```

### Solution:
#### Complexity Analysis

Time complexity : O(Nlogk) where k is the number of linked lists.

We can merge two sorted linked list in O(n) time where n is the total number of nodes in two lists.
Sum up the merge process and we can get: O(∑ 
Space complexity : O(K) where k is the number of lists


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

#### Python
```python
class HeapNode:
    def __init__(self, node):
        self.node = node

    def __lt__(self, other):
        # Define comparison based on ListNode's value
        return self.node.val < other.node.val


class Solution:
    def mergeKLists(
        self, lists: List[Optional[ListNode]]
    ) -> Optional[ListNode]:
        dummy = ListNode(0)
        current = dummy
        heap = []

        # Initialize the heap
        for l in lists:
            if l:
                heapq.heappush(heap, HeapNode(l))

        # Extract the minimum node and add its next node to the heap
        while heap:
            heap_node = heapq.heappop(heap)
            node = heap_node.node
            current.next = node
            current = current.next
            if node.next:
                heapq.heappush(heap, HeapNode(node.next))

        return dummy.next
```
