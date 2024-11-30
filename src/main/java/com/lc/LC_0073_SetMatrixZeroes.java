package com.lc;

public class LC_0073_SetMatrixZeroes {
  public void setZeroes(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return;
    }
    int rows = matrix.length, cols = matrix[0].length;
    boolean isFirstRowZero = false, isFirstColZero = false;
    for (int i = 0; i < cols; i++) {
      if (matrix[0][i] == 0) {
        isFirstRowZero = true;
        break;
      }
    }
    for (int i = 0; i < rows; i++) {
      if (matrix[i][0] == 0) {
        isFirstColZero = true;
        break;
      }
    }
    for (int i = 1; i < rows; i++) {
      for (int j = 1; j < cols; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }
    for (int i = 1; i < rows; i++) {
      if (matrix[i][0] == 0) {
        updateMatrixToZero(matrix, rows, cols, i, true);
      }
    }
    for (int i = 1; i < cols; i++) {
      if (matrix[0][i] == 0) {
        updateMatrixToZero(matrix, rows, cols, i, false);
      }
    }
    if (isFirstRowZero) {
      updateMatrixToZero(matrix, rows, cols, 0, true);
    }
    if (isFirstColZero) {
      updateMatrixToZero(matrix, rows, cols, 0, false);
    }
  }

  public void updateMatrixToZero(int[][] matrix, int rows, int cols, int index, boolean isRow) {
    if (isRow) {
      for (int i = 0; i < cols; i++) {
        matrix[index][i] = 0;
      }
    } else {
      for (int i = 0; i < rows; i++) {
        matrix[i][index] = 0;
      }
    }
  }
}
