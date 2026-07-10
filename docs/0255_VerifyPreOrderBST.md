# Verify preorder traversal of BST
 Given an array of numbers, verify whether it is the correct preorder traversal sequence of a
 binary search tree.
 
 <p>You may assume each number in the sequence is unique.
 
 <p>Follow up: Could you do it using only constant space complexity?
 
 <p>Thoughts
 
 <p>keep track of prev val in order to compare whether there is a new right branch keep track of
 lower bound value of parent node for branching to the right
 
![Actual tree](https://www.techiedelight.com/wp-content/uploads/BST-1.png)  
Preorder traversal: { 15, 10, 8, 12, 20, 16, 25 }.  
```java

public class LC255_VerifyPreOrderBST {
  boolean verifyPreorder(List<Integer> preorder) {
    int low = Integer.MIN_VALUE, index = -1;
    for (Integer elem : preorder) {
      if (elem < low) {
        return false;
      }
      while (index >= 0 && elem > preorder[index]) {
        low = preorder[index--];
      }
      preorder[++index] = val;
    }
    return true;
  }
}
```
<strong> Solution with stack  
Time: O(n)
Space: O(n)</strong>
```java
class Solution {
  public boolean verifyPreorder(int[] preorder) {
    int low = Integer.MIN_VALUE;
    Stack<Integer> stack = new Stack<>();

    for (final int p : preorder) {
      if (p < low)
        return false;
      while (!stack.isEmpty() && stack.peek() < p)
        low = stack.pop();
      stack.push(p);
    }

    return true;
  }
}
```