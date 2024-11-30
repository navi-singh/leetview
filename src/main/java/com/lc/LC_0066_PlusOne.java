package com.lc;

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