---
description: EASY
---

# 217. Contains Duplicate

Given an integer array `nums`, return `true` if any value appears at least twice in the array, and return `false` if every element is distinct.

**Example 1:**

```text
Input: nums = [1,2,3,1]
Output: true
```

**Example 2:**

```text
Input: nums = [1,2,3,4]
Output: false
```

**Example 3:**

```text
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
```

**Constraints:**

- `1 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`

---

## Approach: HashSet Membership Check

Insert each number into a `HashSet`. Before inserting, check whether it already exists—if it does, a duplicate has been found.

#### Complexity Analysis

- **Time complexity: O(n).** Each element is checked and inserted in O(1) amortized time.
- **Space complexity: O(n).** In the worst case all elements are distinct and the set holds all `n` values.

```java
public class LC_0217_ContainsDuplicate {
  public boolean containsDuplicate(int[] nums) {
    if (nums == null || nums.length < 1) {
      return false;
    }
    Set<Integer> dict = new HashSet<>();
    for (int num : nums) {
      if (dict.contains(num)) {
        return true;
      }
      dict.add(num);
    }
    return false;
  }
}
```

**Key insight:** A HashSet provides O(1) average-case lookup, making a single linear scan sufficient to detect any duplicate without sorting the array.
