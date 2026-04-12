---
description: MEDIUM
---

# 78. Subsets

Given an integer array `nums` of **unique** elements, return all possible subsets (the power set).

The solution set **must not** contain duplicate subsets. Return the solution in **any order**.

**Example 1:**

```text
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
```

**Example 2:**

```text
Input: nums = [0]
Output: [[],[0]]
```

**Constraints:**

- `1 <= nums.length <= 10`
- `-10 <= nums[i] <= 10`
- All the numbers of `nums` are **unique**.

---

## Approach 1: Iterative Expansion (Cascading)

Start with a result list containing only the empty subset. For each number in `nums`, iterate over all existing subsets, copy each one, append the current number, and add the new subset to the result. After processing all numbers, the result list contains every possible subset.

#### Complexity Analysis

- **Time complexity: O(n * 2^n).** There are `2^n` subsets, and each element is appended to half of them on average, giving O(n * 2^n) total work.
- **Space complexity: O(n * 2^n).** Storing all subsets requires this space.

```java
public class LC_0078_Subsets {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    res.add(new ArrayList<Integer>());
    if (nums.length < 1) {
      return res;
    }
    for (int i = 0; i < nums.length; i++) {
      int size = res.size();
      for (int j = 0; j < size; j++) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(res.get(j));
        temp.add(nums[i]);
        res.add(temp);
      }
    }

    return res;
  }
}
```

**Key insight:** Each new element doubles the number of subsets — every existing subset spawns an identical copy that includes the new element — which is why the result size grows as a power of 2.
