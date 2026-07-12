# 268 Missing number

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

## In Plain Terms

The array contains every number from `0` through `n` except one; find the missing value. You can compare the expected range with the actual numbers using sum math or XOR while using constant extra space.

For example, Given nums = \[0, 1, 3] return 2.

Note: Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

_**Time: O(n)**_\
&#xNAN;_**Space: O(1)**_

```java
public class LC268_MissingNumber {
  public int missingNumber(int[] nums) {
    int mask = 0;
    for (int i = 0; i < nums.length; i++) {
      mask ^= (i + 1);
      mask ^= nums[i];
    }
    return mask;
  }
}
```
