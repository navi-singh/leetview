---
description: EASY
---

# 1. Two Sum

Given an array of integers `nums` and an integer `target`, return _indices of the two numbers such that they add up to `target`_.

You may assume that each input would have **exactly one solution**, and you may not use the same element twice.

You can return the answer in any order.

**Example 1:**

```text
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

**Example 2:**

```text
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

**Example 3:**

```text
Input: nums = [3,3], target = 6
Output: [0,1]
```

**Constraints:**

- `2 <= nums.length <= 10^4`
- `-10^9 <= nums[i] <= 10^9`
- `-10^9 <= target <= 10^9`
- Only one valid answer exists.

> **Follow-up:** Can you come up with an algorithm that is less than `O(n²)` time complexity?

---

## Approach 1: Brute Force

For each element, scan every other element after it and check if the two sum to `target`.

#### Complexity Analysis

- **Time complexity: O(n²).** Two nested loops over the array.
- **Space complexity: O(1).** No extra space used.

```java
public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length - 1; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] == target)
                return new int[]{i, j};
        }
    }
    return new int[0];
}
```

---

## Approach 2: HashMap (Optimized)

For each element `nums[i]`, compute its complement `target - nums[i]` and check if it already exists in a HashMap. If it does, we found the pair. Otherwise, store the current element and its index in the map.

#### Complexity Analysis

- **Time complexity: O(n).** Single pass through the array; HashMap lookups are O(1).
- **Space complexity: O(n).** HashMap stores at most n entries.

```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> requiredElements = new HashMap<>();
    for (int index = 0; index < nums.length; index++) {
        if (requiredElements.containsKey(nums[index])) {
            return new int[]{index, requiredElements.get(nums[index])};
        }
        requiredElements.put(target - nums[index], index);
    }
    return null;
}
```

**Key insight:** Instead of storing `(value → index)`, store `(complement → index)` — i.e., map `target - nums[i]` to `i`. Then for each new element, checking if it exists in the map is equivalent to asking "has its complement been seen before?"
