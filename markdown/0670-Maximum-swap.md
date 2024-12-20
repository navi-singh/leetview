# [670. Maximum Swap](https://leetcode.com/problems/maximum-swap/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

You are given an integer `num`. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.

**Example 1:** 

```
Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
```

**Example 2:** 

```
Input: num = 9973
Output: 9973
Explanation: No swap.
```

**Constraints:** 

- `0 <= num <= 10^8`

### Java
```java
class Solution {
    public int maximumSwap(int num) {
        char[] numArr = Integer.toString(num).toCharArray();
        int n = numArr.length;
        int[] maxRightIndex = new int[n];

        maxRightIndex[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; --i) {
            maxRightIndex[i] = (numArr[i] > numArr[maxRightIndex[i + 1]])
                ? i
                : maxRightIndex[i + 1];
        }

        for (int i = 0; i < n; ++i) {
            if (numArr[i] < numArr[maxRightIndex[i]]) {
                char temp = numArr[i];
                numArr[i] = numArr[maxRightIndex[i]];
                numArr[maxRightIndex[i]] = temp;
                return Integer.parseInt(new String(numArr));
            }
        }
        return num;
    }
}
```

```python
class Solution:
    def maximumSwap(self, num: int) -> int:
        num_str = list(str(num))
        n = len(num_str)
        max_right_index = [0] * n

        max_right_index[n - 1] = n - 1
        for i in range(n - 2, -1, -1):
            max_right_index[i] = (
                i
                if num_str[i] > num_str[max_right_index[i + 1]]
                else max_right_index[i + 1]
            )

        for i in range(n):
            if num_str[i] < num_str[max_right_index[i]]:
                num_str[i], num_str[max_right_index[i]] = (
                    num_str[max_right_index[i]],
                    num_str[i],
                )
                return int("".join(num_str))

        return num
```
#### Complexity Analysis
Let n be the number of digits in the input number.

Time complexity: O(n)

Converting the integer num to its string representation takes O(n).

We iterate through the digits from right to left, making one comparison per digit. This pass takes O(n) time.

We iterate from left to right, checking whether the current digit is smaller than the maximum digit to its right. This also takes O(n) time.

Converting the modified string back to an integer takes O(n) time.

Overall, each operation in the algorithm takes linear time, so the total time complexity is O(n).

Space complexity: O(n)

We store the string representation of the number, which requires O(n) space.

We maintain an array maxRightIndex of size n, which also takes O(n) space.

The space used by simple variables like num and loop counters is constant, i.e., O(1).

Thus, the total space complexity is O(n).



### More efficient java solution
```java
Solution 2: Greedy O(n); O(1)

Intuition

At each digit of the input number in order, if there is a larger digit that occurs later, we know that the best swap must occur with the digit we are currently considering.

Algorithm

We will compute last[d] = i, the index i of the last occurrence of digit d(if it exists).

Afterwards, when scanning the number from left to right, if there is a larger digit in the future, we will swap it with the largest such digit; if there are multiple such digits, we will swap it with the one that occurs the latest.

    public int maximumSwap(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] last = new int[10]; 
        for (int i = 0; i < A.length; i++) {
            last[A[i] - '0'] = i;
        }

        for (int i = 0; i < A.length; i++) {
            for (int d = 9; d > A[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = A[i];
                    A[i] = A[last[d]];
                    A[last[d]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }
        return num;
    }
```
### Complexity Analysis
Let n be the number of digits in the input number.

Time complexity: O(n)

Converting the integer num to its string representation takes O(n).

We loop through the string numStr to fill the lastSeen array, which takes O(n) time.

The outer loop runs n times (once for each digit), and for each digit, the inner loop runs at most 9 times (since there are at most 9 different digits larger than the current one to check). Thus, the traversal and comparison step takes O(9n)=O(n) time.

Converting the modified string back to an integer takes O(n) time.

Overall, all steps are bounded by O(n), so the total time complexity is O(n).

Space complexity: O(n)

The string numStr requires O(n) space to store the digits of the integer num.

The array lastSeen is of fixed size 10 (for digits 0 through 9), so it takes O(1) space.

No other significant additional space is used.

Thus, the overall space complexity is dominated by the space needed to store the string, which is O(n).

