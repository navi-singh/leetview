# 268 Missing number
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 
<p>For example, Given nums = [0, 1, 3] return 2.
 
<p>Note: Your algorithm should run in linear runtime complexity. Could you implement it using
only constant extra space complexity?

***Time: O(n)  
Space: O(1)***
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