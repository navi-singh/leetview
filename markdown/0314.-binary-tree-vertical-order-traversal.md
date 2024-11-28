---
description: MEDIUM
---

# 314. Binary Tree Vertical Order Traversal

Given the `root` of a binary tree, return _**the vertical order traversal** of its nodes' values_. \(i.e., from top to bottom, column by column\).

If two nodes are in the same row and column, the order should be from **left to right**.

**Example 1:** ![](https://assets.leetcode.com/uploads/2021/01/28/vtree1.jpg)

```text
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
```

**Example 2:** ![](https://assets.leetcode.com/uploads/2021/01/28/vtree2-1.jpg)

```text
Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]
```

**Example 3:** ![](https://assets.leetcode.com/uploads/2021/01/28/vtree2.jpg)

```text
Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]
```

**Example 4:**

```text
Input: root = []
Output: []
```

**Overview**

This is yet another problem about Binary Tree traversals. As one would probably know, the common strategies to traverse a _Tree_ data structure are _**Breadth-First Search**_ \(_a.k.a_ BFS\) and _**Depth-First Search**_ \(_a.k.a._ DFS\).

The DFS strategy can be further distinguished as _preorder DFS_, _inorder DFS_ and _postorder DFS_, depending on the relative order of visit among the node itself and its child nodes.

If one is not familiar with the concepts of BFS and DFS, one can find the corresponding problems on LeetCode to practice with. Also, we have an Explore card called [Queue & Stack](https://leetcode.com/explore/learn/card/queue-stack/) where we cover both [the BFS traversal](https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/) as well as [the DFS traversal](https://leetcode.com/explore/learn/card/queue-stack/232/practical-application-stack/). Hence, in this article, we won't repeat ourselves on these concepts.

In the problem description, we are asked to return the _**vertical**_ order of a binary tree, which actually implies two sub-orders, where each node would have a 2-dimensional index \(denoted as `<column, row>`\)

![tree in 2D coordinates](https://leetcode.com/problems/binary-tree-vertical-order-traversal/Figures/314/314_coordinates.png)

* **column-wise order**  

If we look at a binary tree _horizontally_, each node can be aligned to a specific `column`, based on its relative _offset_ to the root node of the tree.

Let us assume that the root node has a column index of `0`, then its left child node would have a column index of `-1` and its right child node would have a column index of `+1`, and so on.

* **row-wise order**  

   Now if we put the nodes into a _vertical_ dimension, each node would be assigned to a specific `row`, based on its _level_ \(_i.e._ the vertical distance to the root node\).  

Let us assume that the root node has a row index of `0`, then both its child nodes would have the row index of `1`.

> Given the above definitions, we can now formulate the problem as a task to order the nodes based on the 2-dimensional coordinates that we defined above.

More specifically, the nodes should be ordered by `column` first, and further the nodes on the same column should be ordered _**vertically**_ based on their `row` indices.

**Approach 1: Breadth-First Search \(BFS\)**

**Intuition**

With the formulation of the problem in the overview section, one of the most intuitive solutions to tackle the problem would be applying the BFS traversal, where the nodes would be visited _level by level_.

With the BFS traversal, we naturally can guarantee the vertical order of the visits, _i.e._ the nodes at _higher_ levels \(large `row` values\) would get visited later than the ones at lower levels.

However, we are still missing the horizontal order \( the `column` order\). To ensure this order, we need to do some additional processing during the BFS traversal.

> The idea is that we keep a hash table \(let's denote it as `columnTable<key, value>`\), where we keep the node values grouped by the `column` index.

The `key` in the hash table would be the `column` index, and the corresponding `value` would be a list which contains the values of all the nodes that share the same column index.

In addition, the values in the corresponding list should be ordered by their `row` indices, which would be guaranteed by the BFS traversal as we mentioned before.

**Algorithm**

We elaborate on the steps to implement the above idea.

* First, we create a hash table named `columnTable` to keep track of the results. 
* As to the BFS traversal, a common code pattern would be to use a `queue` data structure to keep track of the order we need to visit nodes. We initialize the queue by putting the root node along with its column index value \(`0`\). 
* We then run the BFS traversal with a loop consuming the elements from the queue. 
* At each iteration within the BFS, we pop out an element from the queue. The element consists of a `node` and its corresponding `column` index. If the node is not empty, we then populate the `columnTable` with the value of the node. Subsequently, we then put its child nodes along with their respective column indices \(_i.e._ `column-1` and `column+1`\) into the queue. 
* At the end of the BFS traversal, we obtain a hash table that contains the desired node values grouped by their `column` indices. For each group of values, they are further ordered by their `row` indices. 
* We then sort the hash table by its keys, _i.e._ `column` index in ascending order. And finally we return the results _column by column_.

{% tabs %}
{% tab title="Java" %}
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> output = new ArrayList();
    if (root == null) {
      return output;
    }

    Map<Integer, ArrayList> columnTable = new HashMap();
    Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
    int column = 0;
    queue.offer(new Pair(root, column));

    while (!queue.isEmpty()) {
      Pair<TreeNode, Integer> p = queue.poll();
      root = p.getKey();
      column = p.getValue();

      if (root != null) {
        if (!columnTable.containsKey(column)) {
          columnTable.put(column, new ArrayList<Integer>());
        }
        columnTable.get(column).add(root.val);

        queue.offer(new Pair(root.left, column - 1));
        queue.offer(new Pair(root.right, column + 1));
      }
    }

    List<Integer> sortedKeys = new ArrayList<Integer>(columnTable.keySet());
    Collections.sort(sortedKeys);
    for(int k : sortedKeys) {
      output.add(columnTable.get(k));
    }

    return output;
  }
}
```
{% endtab %}

{% tab title="Python" %}
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from collections import defaultdict
class Solution:
    def verticalOrder(self, root: TreeNode) -> List[List[int]]:
        columnTable = defaultdict(list)
        queue = deque([(root, 0)])

        while queue:
            node, column = queue.popleft()

            if node is not None:
                columnTable[column].append(node.val)

                queue.append((node.left, column - 1))
                queue.append((node.right, column + 1))

        return [columnTable[x] for x in sorted(columnTable.keys())]
```
{% endtab %}
{% endtabs %}

**Complexity Analysis**

* Time Complexity:O\(Nlog⁡N\)\mathcal{O}\(N \log N\)O\(NlogN\) whereNNN is the number of nodes in the tree.

  In the first part of the algorithm, we do the BFS traversal, whose time complexity isO\(N\)\mathcal{O}\(N\)O\(N\) since we traversed each node once and only once.

  In the second part, in order to return the ordered results, we then sort the obtained hash table by its keys, which could result in theO\(Nlog⁡N\)\mathcal{O}\(N \log N\)O\(NlogN\) time complexity in the worst case scenario where the binary tree is extremely imbalanced \(for instance, each node has only left child node.\)

  As a result, the overall time complexity of the algorithm would beO\(Nlog⁡N\)\mathcal{O}\(N \log N\)O\(NlogN\).

* Space Complexity:O\(N\)\mathcal{O}\(N\)O\(N\) whereNNN is the number of nodes in the tree.

  First of all, we use a hash table to group the nodes with the same column index. The hash table consists of keys and values. In any case, the values would consumeO\(N\)\mathcal{O}\(N\)O\(N\) memory. While the space for the keys could vary, in the worst case, each node has a unique column index, _i.e._ there would be as many keys as the values. Hence, the total space complexity for the hash table would still beO\(N\)\mathcal{O}\(N\)O\(N\).

  During the BFS traversal, we use a `queue` data structure to keep track of the next nodes to visit. At any given moment, the queue would hold no more two levels of nodes. For a binary tree, the maximum number of nodes at a level would beN+12\frac{N+1}{2}2N+1​ which is also the number of leafs in a full binary tree. As a result, in the worst case, our queue would consume at mostO\(N+12⋅2\)=O\(N\)\mathcal{O}\(\frac{N+1}{2} \cdot 2\) = \mathcal{O}\(N\)O\(2N+1​⋅2\)=O\(N\) space.

  Lastly, we also need some space to hold the results, which is basically a reordered hash table of sizeO\(N\)\mathcal{O}\(N\)O\(N\) as we discussed before.

  To sum up, the overall space complexity of our algorithm would beO\(N\)\mathcal{O}\(N\)O\(N\).

**Approach 2: BFS without Sorting \(Preffered\)**

**Intuition**

In the previous approach, it is a pity that the sorting of results overshadows the main part of the algorithm which is the BFS traversal. One might wonder if we have a way to eliminate the need for sorting. And the answer is yes.

> The key insight is that we only need to know the **range** of the column index \(_i.e._ `[min_column, max_column]`\). Then we can simply _**iterate**_ through this range to generate the outputs without the need for sorting.

The above insight would work under the _condition_ that there won't be any missing column index in the given range. And the condition always holds, since there won't be any broken branch in a binary tree.

**Algorithm**

To implement this optimization, it suffices to make some small modifications to our previous BFS approach.

During the BFS traversal, we could obtain the range of the column indices, _i.e._ with the variable of `min_column` and `max_column`.

At the end of the BFS traversal, we would then walk through the column range `[min_column, max_column]` and retrieve the results accordingly.![Current](blob:https://leetcode.com/9fa992c9-eb6a-e44d-87b9-66003778868b)1 / 14

{% tabs %}
{% tab title="Java" %}
```java
class Solution {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> output = new ArrayList();
    if (root == null) {
      return output;
    }

    Map<Integer, ArrayList> columnTable = new HashMap();
    // Pair of node and its column offset
    Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
    int column = 0;
    queue.offer(new Pair(root, column));

    int minColumn = 0, maxColumn = 0;

    while (!queue.isEmpty()) {
      Pair<TreeNode, Integer> p = queue.poll();
      root = p.getKey();
      column = p.getValue();

      if (root != null) {
        if (!columnTable.containsKey(column)) {
          columnTable.put(column, new ArrayList<Integer>());
        }
        columnTable.get(column).add(root.val);
        minColumn = Math.min(minColumn, column);
        maxColumn = Math.max(maxColumn, column);

        queue.offer(new Pair(root.left, column - 1));
        queue.offer(new Pair(root.right, column + 1));
      }
    }

    for(int i = minColumn; i < maxColumn + 1; ++i) {
      output.add(columnTable.get(i));
    }

    return output;
  }
}
```
{% endtab %}

{% tab title="Python" %}
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from collections import defaultdict
class Solution:
    def verticalOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []

        columnTable = defaultdict(list)
        min_column = max_column = 0
        queue = deque([(root, 0)])

        while queue:
            node, column = queue.popleft()

            if node is not None:
                columnTable[column].append(node.val)
                min_column = min(min_column, column)
                max_column = max(max_column, column)

                queue.append((node.left, column - 1))
                queue.append((node.right, column + 1))

        return [columnTable[x] for x in range(min_column, max_column + 1)]
```
{% endtab %}
{% endtabs %}

**Complexity Analysis**

* Time Complexity:O\(N\)\mathcal{O}\(N\)O\(N\) whereNNN is the number of nodes in the tree.  Following the same analysis in the previous BFS approach, the only difference is that this time we don't need the costy sorting operation \(\_i.e.\_O\(Nlog⁡N\)\mathcal{O}\(N \log N\)O\(NlogN\)\).
* Space Complexity:O\(N\)\mathcal{O}\(N\)O\(N\) whereNNN is the number of nodes in the tree. The analysis follows the same logic as in the previous BFS approach.  

**Approach 3: Depth-First Search \(DFS\)**

**Intuition**

Although we applied a BFS traversal in both of the previous approaches, it is not impossible to solve the problem with a DFS traversal.

> As we discussed in the overview section, once we assign a 2-dimensional index \(_i.e._ `<column, row>`\) for each node in the binary tree, to output the tree in _**vertical**_ order is to sort the nodes based on the 2-dimensional index, firstly by `column` then by `row`, as shown in the following graph.

![tree to table](https://leetcode.com/problems/binary-tree-vertical-order-traversal/Figures/314/314_table.png)

Compared to the DFS traversal, the BFS traversal gives us a head start, since the nodes in higher rows would be visited later than the ones in the lower lows. As a result, we only need to focus on the `column` order.

That being said, we could simply traverse the tree in any DFS order \(preorder, inorder or postorder\), then we sort the resulting list strictly based on two keys `<column, row>`, which would give us the same results as the BFS traversal.

> An important note is that two nodes might share the same `<column, row>`, in the case, as stated in the problem, the order between these two nodes should be from **left** to **right** as we did for BFS traversals. As a result, to ensure such a priority, one should make sure to visit the left child node before the right child node during the DFS traversal.

**Algorithm**

* Here we implement the above algorithm, with the trick that we applied in Approach 2 \(BFS without sorting\) where we obtained the range of `column` during the traversal. 
* First, we conduct a DFS traversal on the input tree. During the traversal, we would then build a similar `columnTable` with the `column` index as the key and the list of `(row, val)` tuples as the value. 
* At the end of the DFS traversal, we iterate through the `columnTable` via the key of `column` index. Accordingly, we have a list of `(row, val)` tuples associated with each key. We then sort this list, based on the `row` index. 
* After the above steps, we would then obtain a list of node values ordered firstly by its `column` index and then by its `row` index, which is exactly the the _vertical_ order traversal of binary tree as defined in the problem.

{% tabs %}
{% tab title="Java" %}
```java
class Solution {
  Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap();
  int minColumn = 0, maxColumn = 0;

  private void DFS(TreeNode node, Integer row, Integer column) {
    if (node == null)
      return;

    if (!columnTable.containsKey(column)) {
      this.columnTable.put(column, new ArrayList<Pair<Integer, Integer>>());
    }

    this.columnTable.get(column).add(new Pair<Integer, Integer>(row, node.val));
    this.minColumn = Math.min(minColumn, column);
    this.maxColumn = Math.max(maxColumn, column);
    // preorder DFS traversal
    this.DFS(node.left, row + 1, column - 1);
    this.DFS(node.right, row + 1, column + 1);
  }

  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> output = new ArrayList();
    if (root == null) {
      return output;
    }

    this.DFS(root, 0, 0);

    // Retrieve the resuts, by ordering by column and sorting by row
    for (int i = minColumn; i < maxColumn + 1; ++i) {

      Collections.sort(columnTable.get(i), new Comparator<Pair<Integer, Integer>>() {
        @Override
        public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
          return p1.getKey() - p2.getKey();
        }
      });

      List<Integer> sortedColumn = new ArrayList();
      for (Pair<Integer, Integer> p : columnTable.get(i)) {
        sortedColumn.add(p.getValue());
      }
      output.add(sortedColumn);
    }

    return output;
  }
}
```
{% endtab %}

{% tab title="Python" %}
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from collections import defaultdict
class Solution:
    def verticalOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []

        columnTable = defaultdict(list)
        min_column = max_column = 0

        def DFS(node, row, column):
            if node is not None:
                nonlocal min_column, max_column
                columnTable[column].append((row, node.val))
                min_column = min(min_column, column)
                max_column = max(max_column, column)

                # preorder DFS
                DFS(node.left, row + 1, column - 1)
                DFS(node.right, row + 1, column + 1)

        DFS(root, 0, 0)

        # order by column and sort by row
        ret = []
        for col in range(min_column, max_column + 1):
            columnTable[col].sort(key=lambda x:x[0])
            colVals = [val for row, val in columnTable[col]]
            ret.append(colVals)

        return ret
```
{% endtab %}
{% endtabs %}

**Complexity Analysis**

* Time Complexity:O\(W⋅Hlog⁡H\)\) where W is the width of the binary tree \(_i.e._ the number of columns in the result\) andHHH is the height of the tree.  

   In the first part of the algorithm, we traverse the tree in DFS, which results inO\(N\)\mathcal{O}\(N\)O\(N\) time complexity.  

Once we build the `columnTable`, we then have to sort it _column by column_.

Let us assume the time complexity of the sorting algorithm to beO\(Klog⁡K\)\mathcal{O}\(K \log K\)O\(KlogK\) whereKKK is the length of the input. The maximal number of nodes in a column would beH2\frac{H}{2}2H​ whereHHH is the height of the tree, due to the zigzag nature of the node distribution. As a result, the upper bound of time complexity to sort a column in a binary tree would beO\(H2log⁡H2\)\mathcal{O}\(\frac{H}{2} \log \frac{H}{2}\)O\(2H​log2H​\).

Since we need to sortWWW columns, the total time complexity of the sorting operation would then beO\(W⋅\(H2log⁡H2\)\)=O\(W⋅Hlog⁡H\)\mathcal{O}\big\(W \cdot \(\frac{H}{2} \log{\frac{H}{2}}\)\big\) = \mathcal{O}\(W \cdot H \log{H}\)O\(W⋅\(2H​log2H​\)\)=O\(W⋅HlogH\). Note that, the total number of nodesNNN in a tree is bounded byW⋅HW \cdot HW⋅H, \_i.e.\_N&lt;W⋅H N &lt; W \cdot HN&lt;W⋅H. As a result, the time complexity ofO\(W⋅Hlog⁡H\)\mathcal{O}\big\(W \cdot H \log{H}\big\)O\(W⋅HlogH\) will dominate theO\(N\)\mathcal{O}\(N\)O\(N\) of the DFS traversal in the first part.

At the end of the DFS traversal, we have to iterate through the `columnTable` in order to retrieve the values, which will take anotherO\(N\)\mathcal{O}\(N\)O\(N\) time.

To sum up, the overall time complexity of the algorithm would beO\(W⋅Hlog⁡H\)\mathcal{O}\big\(W \cdot H \log{H}\big\)O\(W⋅HlogH\).

An interesting thing to note is that in the case where the binary tree is completely imbalanced \(_e.g._ node has only left child.\), this DFS approach would have theO\(N\)\mathcal{O}\(N\)O\(N\) time complexity, since the sorting takes no time on columns that contains only a single node. While the time complexity for our first BFS approach would beO\(Nlog⁡N\)\mathcal{O}{\(N \log N\)}O\(NlogN\), since we have to sort theNNN keys in the `columnTable`.

* Space Complexity:O\(N\)\mathcal{O}\(N\)O\(N\) whereNNN is the number of nodes in the tree.

  We kept the `columnTable` which contains all the node values in the binary tree. Together with the keys, it would consumeO\(N\)\mathcal{O}\(N\)O\(N\) space as we discussed in previous approaches.

Since we apply the recursion for our DFS traversal, it would incur additional space consumption on the function call stack. In the worst case where the tree is completely imbalanced, we would have the size of call stack up toO\(N\)\mathcal{O}\(N\)O\(N\).

Finally, we have the output which contains all the values in the binary tree, thusO\(N\)\mathcal{O}\(N\)O\(N\) space.

So in total, the overall space complexity of this algorithm remainsO\(N\)\mathcal{O}\(N\)O\(N\).

