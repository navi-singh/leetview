package com.lc;

public class LC_0067_AddBinary {
  public String addBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int i = a.length() - 1, j = b.length() - 1;
    int sum = 0, carry = 0;
    char one = '1', zero = '0';
    while (i >= 0 || j >= 0) {
      sum = carry;
      if (i >= 0 && a.charAt(i) == '1') {
        sum++;
      }
      if (j >= 0 && b.charAt(j) == '1') {
        sum++;
      }
      if (sum >= 2) {
        carry = 1;
      } else {
        carry = 0;
      }
      sb.insert(0, (char) (sum % 2 + zero));
      i--;
      j--;
    }
    if (carry == 1) {
      sb.insert(0, one);
    }
    return sb.toString();
  }
}
