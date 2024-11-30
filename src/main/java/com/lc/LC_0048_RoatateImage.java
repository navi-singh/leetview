package com.lc;

public class LC_0048_RoatateImage {
  public void rotate(int[][] matrix) {
    if (matrix.length < 1 || matrix.length != matrix[0].length) {
      return;
    }
    int len = matrix.length;
    for (int row = 0; row < len / 2; ++row) {
      for (int col = 0; col < (len % 2 == 1 ? len / 2 + 1 : len / 2); ++col) {
        int temp = matrix[row][col];
        matrix[row][col] = matrix[len - col - 1][row];
        matrix[len - col - 1][row] = matrix[len - row - 1][len - col - 1];
        matrix[len - row - 1][len - col - 1] = matrix[col][len - row - 1];
        matrix[col][len - row - 1] = temp;
      }
    }
  }
}
