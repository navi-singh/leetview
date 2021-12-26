# Three Sum Smaller

Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

>For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

>[-2, 0, 1] [-2, 0, 3]  

***Follow up: Could you solve it in O(n2) runtime?***

why sort array is ok?

because sort array won't change the fact that a good triplet still stands
Fix first number, then use two pointers, notice that when you find a i(fixed),j,k triplet, which means all combine between k-j is valid triplet.

***Time: O(n^2)  
Space: O(1)***
```java
class Solution {
 public:
  int threeSumSmaller(vector<int>& nums, int target) {
    if (nums.size() < 3)
      return 0;

    int ans = 0;

    sort(begin(nums), end(nums));

    for (int i = 0; i + 2 < nums.size(); ++i) {
      int l = i + 1;
      int r = nums.size() - 1;
      while (l < r)
        if (nums[i] + nums[l] + nums[r] < target) {
          // (nums[i], nums[l], nums[r])
          // (nums[i], nums[l], nums[r - 1])
          // ...,
          // (nums[i], nums[l], nums[l + 1])
          ans += r - l;
          ++l;
        } else {
          --r;
        }
    }

    return ans;
  }
};

```