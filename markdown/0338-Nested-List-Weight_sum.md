# [339. Nested List Weight Sum](https://leetcode.com/problems/nested-list-weight-sum/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

You are given a nested list of integers `nestedList`. Each element is either an integer or a list whose elements may also be integers or other lists.

The **depth**  of an integer is the number of lists that it is inside of. For example, the nested list `[1,[2,2],[[3],2],1]` has each integer's value set to its **depth** .

Return the sum of each integer in `nestedList` multiplied by its **depth** .

**Example 1:** 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/14/nestedlistweightsumex1.png" style="width: 405px; height: 99px;">

```
Input: nestedList = [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
```

**Example 2:** 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/14/nestedlistweightsumex2.png" style="width: 315px; height: 106px;">

```
Input: nestedList = [1,[4,[6]]]
Output: 27
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
```

**Example 3:** 
```
Input: nestedList = [0]
Output: 0
```

## Complexity Analysis

Time complexity : O(N).

Similar to the DFS approach. Each nested element is put on the queue and removed from the queue exactly once.

Space complexity : O(N).

The worst-case for space complexity in BFS occurs where most of the elements are in a single layer,
for example, a flat list such as [1, 2, 3, 4, 5] as all of the elements must be put on the queue at the same time.
Therefore, this approach also has a worst-case space complexity of O(N).


### Java
```java
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);

        int depth = 1;
        int total = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger nested = queue.poll();
                if (nested.isInteger()) {
                    total += nested.getInteger() * depth;
                } else {
                    queue.addAll(nested.getList());
                }
            }
            depth++;
        }
        return total;
    }
}
```

### Python
```Python
class Solution:
    def depthSum(self, nestedList: List[NestedInteger]) -> int:
        queue = deque(nestedList)

        depth = 1
        total = 0

        while len(queue) > 0:
            for i in range(len(queue)):
                nested = queue.pop()
                if nested.isInteger():
                    total += nested.getInteger() * depth
                else:
                    queue.extendleft(nested.getList())
            depth += 1

        return total
```
