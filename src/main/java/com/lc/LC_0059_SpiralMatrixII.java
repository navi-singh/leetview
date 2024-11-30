package com.lc;

public class LC_0059_SpiralMatrixII {
  public void print2DArray(int[][] arr) {
    for (int i = 0; i < arr.length; ++i) {
      for (int j = 0; j < arr[0].length; ++j) {
        System.out.print(arr[i][j] + ",");
      }
      System.out.println();
    }
  }

  public int[][] generateMatrix(int n) {
    int[][] res = new int[n][n];
    // iterate over the array for rows and columns
    // update the values in the array
    int val = 1;
    for (int i = 0; i < n / 2; ++i) {
      int last = n - i - 1;
      for (int top = i; top < last; ++top) {
        res[i][top] = val++;
      }
      for (int right = i; right < last; ++right) {
        res[right][last] = val++;
      }
      for (int bottom = last; bottom > i; --bottom) {
        res[last][bottom] = val++;
      }
      for (int left = last; left > i; --left) {
        res[left][i] = val++;
      }
    }
    if (n % 2 == 1) {
      res[n / 2][n / 2] = val;
    }
    return res;
  }

  public int[][] generateMatrixTest(int n) {
    int val = 1;
    int[][] res = new int[n][n];
    for (int i = 0; i < n / 2; i++) {
      for (int j = i; j < n - i - 1; i++) {
        res[i][j] = val++;
      }
      for (int j = i; j < n - i - 1; j++) {
        res[n - i - 1][j] = val++;
      }
      for (int j = n - i - 1; j > i; j--) {
        res[n - i - 1][j] = val++;
      }
      for (int j = n - i - 1; j > i; j--) {
        res[j][n - i - 1] = val++;
      }
    }
    if (n % 2 == 1) {
      res[n / 2][n / 2] = val;
    }
    return res;
  }
}
