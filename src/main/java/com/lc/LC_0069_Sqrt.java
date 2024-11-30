package com.lc;

public class LC_0069_Sqrt {
  public int mySqrt(int x) {
    if (x < 1) {
      return 0;
    }
    if (x < 4) {
      return 1;
    }
    int left = 2, right = x / 2;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int temp = x / mid;
      if (temp == mid) {
        return mid;
      } else if (temp > mid) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return right;
  }
}
