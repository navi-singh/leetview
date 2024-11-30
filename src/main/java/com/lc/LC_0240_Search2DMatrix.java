package com.lc;

public class LC_0240_Search2DMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
      return false;
    }
    int row = matrix.length - 1, col = 0;
    while (row >= 0 && col < matrix[0].length) {
      if (matrix[row][col] < target) {
        col++;
      } else if (matrix[row][col] > target) {
        row--;
      } else {
        return true;
      }
    }
    return false;
  }
}
