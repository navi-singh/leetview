---
description: EASY
---

# 66. Plus One

You are given a **large integer** represented as an integer array `digits`, where each `digits[i]` is the `i`th digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading `0`s.

Increment the large integer by one and return the resulting array of digits.

**Example 1:**

```text
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123. Incrementing by one gives 124.
```

**Example 2:**

```text
Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321. Incrementing by one gives 4322.
```

**Example 3:**

```text
Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9. Incrementing by one gives 10.
```

**Constraints:**

- `1 <= digits.length <= 100`
- `0 <= digits[i] <= 9`
- `digits` does not contain any leading `0`s.

---

## Approach 1: Carry Propagation from Right

Start from the rightmost digit with an initial carry of 1. Add the carry to each digit and propagate leftward. If no carry remains, stop early. If a carry still exists after processing all digits, prepend it to a new array (handles the all-nines case, e.g., 999 → 1000).

#### Complexity Analysis

- **Time complexity: O(n).** In the worst case we traverse all digits once.
- **Space complexity: O(n).** Only when all digits are 9 do we allocate a new array of size `n + 1`; otherwise O(1) extra space.

```java
public class LC_0066_PlusOne {
  public int[] plusOne(int[] digits) {
    int right = digits.length - 1;
    int carry = 1;
    int value = 0;
    while (right >= 0) {
      value = digits[right] + carry;
      if (value > 9) {
        digits[right] = value % 10;
        carry = value / 10;
      } else {
        digits[right] = value;
        carry = 0;
        break;
      }
      right--;
    }
    if (carry > 0) {
      int[] res = new int[digits.length + 1];
      System.arraycopy(digits, 0, res, 1, digits.length);
      res[0] = carry;
      return res;
    }
    return digits;
  }
}
```

**Key insight:** The only case that requires allocating a new array is when every digit is 9 (the carry propagates all the way through); in all other cases the existing array can be modified in place.
