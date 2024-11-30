package com.lc;

public class LC_0065_ValidNumber {
    public boolean isNumber(String s) {
        int left = 0, right = s.length();
        while (left < right && s.charAt(left) == ' ') {
            left++;
        }
        while (left < right && s.charAt(right - 1) == ' ') {
            right--;
        }
        if (left < right && isPositiveOrNegative(s.charAt(left))) {
            left++;
        }
        boolean isNumber = false;
        while (left < right && isDigit(s.charAt(left))) {
            isNumber = true;
            left++;
        }
        if (left < right && s.charAt(left) == '.') {
            left++;
            while (left < right && isDigit(s.charAt(left))) {
                left++;
                isNumber = true;
            }
        }
        if (isNumber && left < right && s.charAt(left) == 'e') {
            left++;
            isNumber = false;
            if (left < right && isPositiveOrNegative(s.charAt(left))) {
                left++;
            }
            if (left < right && isDigit(s.charAt(left))) {
                isNumber = true;
                while (left < right && isDigit(s.charAt(left))) {
                    left++;
                }
            }
        }
        return isNumber && left == right;
    }

    private boolean isDigit(char c) {
        int num = c - '0';
        return (num >= 0 && num <= 9);
    }

    private boolean isPositiveOrNegative(char c) {
        return (c == '+' || c == '-');
    }
}