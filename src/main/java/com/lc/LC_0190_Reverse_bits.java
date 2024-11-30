package com.lc;

public class LC_0190_Reverse_bits {
  public int reverseBits(int n) {
    for (int i = 0; i < 16; i++) {
      n = swapBits(n, i, 32 - i - 1);
    }
    return n;
  }

  private int swapBits(int n, int start, int end) {
    int a = (n >> start) & 1;
    int b = (n >> end) & 1;
    if ((a ^ b) != 0) {
      n ^= (1 << start) | (1 << end);
    }
    return n;
  }
}
