# 0258\_AddDigits

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

## In Plain Terms

Keep summing the digits of `num` until only one digit remains, then return that digit. The follow-up asks for the constant-time digital-root shortcut instead of literally repeating the sums.

For example:

> Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up: Could you do it without any loop/recursion in O(1) runtime?

Hint:

A naive implementation of the above process is trivial. Could you come up with other methods? What are all the possible results? How do they occur, periodically or randomly? You may find this Wikipedia article useful.

> the simpler solution, is to use recursion/loop, each time, add all digits number, until generates a single digit number.

_**Time: O(1)**_\
&#xNAN;_**Space: O(1)**_

```java
class Solution {
  public int addDigits(int num) {
    return 1 + (num - 1) % 9;
  }
}
```

_**Time: O(k)**_\
&#xNAN;_**Space: O(1)**_

```java
public class LC258_AddDigits {
  public int addDigits(int num) {
    int res = 0;
    while (num > 0) {
      res += num % 10;
      num = num / 10;
      if (num == 0 && res > 9) {
        num = res;
        res = 0;
      }
    }
    return res;
  }
}
```
