# 670. Maximum Swap

You are given an integer `num`. You can swap two digits at most once to get the maximum valued number.

Return _the maximum valued number you can get_.

**Example 1:**

```text
Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
```

**Example 2:**

```text
Input: num = 9973
Output: 9973
Explanation: No swap.
```

**Solution 2: Greedy O\(n\); O\(1\)**

> **Intuition**
>
> At each digit of the input number in order, if there is a larger digit that occurs later, we know that the best swap must occur with the digit we are currently considering.
>
> **Algorithm**
>
> We will compute last\[d\] = i, the index i of the last occurrence of digit d\(if it exists\).
>
> Afterwards, when scanning the number from left to right, if there is a larger digit **in the future**, we will swap it with the largest such digit; if there are multiple such digits, we will swap it with the one that occurs the latest.

```java
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

