# [1762. Buildings With an Ocean View](https://leetcode.com/problems/buildings-with-an-ocean-view/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

There are `n` buildings in a line. You are given an integer array `heights` of size `n` that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a **smaller**  height.

Return a list of indices **(0-indexed)**  of buildings that have an ocean view, sorted in increasing order.

**Example 1:** 

```
Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
```

**Example 2:** 

```
Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.
```

**Example 3:** 

```
Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.
```

## Solution
### Complexity Analysis

**Time complexity: O(N).**

We iterate over the given array once, and for each building height, we perform a constant number of operations.
The answer array is reversed at the end, which also takes O(N) time.
In Java, copying the elements from the array list to an integer array in reverse order also takes O(N).

**Space complexity: O(1)**
No auxiliary space was used other than for the output array.
Although, in Java, in order to maintain a dynamic size array (since we don't know the size of the output array at the beginning), we created an extra Array List that supports fast O(1) push operation. Array List can contain at most N elements, hence for the Java solution, the space complexity is O(N).

```python
class Solution:
    def findBuildings(self, heights: List[int]) -> List[int]:
        max = -1
        res = []
        for i in reversed(range(len(heights))):
            if heights[i] > max:
                res.append(i)
                max = heights[i]
        res.reverse()
        return res
```
<details>
<summary>Java </summary>

```java
class Solution {
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        List<Integer> list = new ArrayList<>();
        int maxHeight = -1;
        
        for (int current = n - 1; current >= 0; --current) {
            // If there is no building higher (or equal) than the current one to its right,
            // push it in the list.
            if (maxHeight < heights[current]) {
                list.add(current);
                
                // Update max building till now.
                maxHeight = heights[current];
            }
        }
        
        // Push building indices from list to answer array in reverse order.
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(list.size() - 1 - i);
        }
        
        return answer;
    }
}
```
  
</details>

