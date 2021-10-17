public class LC256_PaintHouse {
  public int minCost(int[][] costs) {
    if (costs == null || costs.length == 0) {
      return 0;
    }
    int[][] matrix = new int[3][costs.length];
    for (int j = 0; j < costs.length; j++) {
      if (j == 0) {
        matrix[0][j] = costs[j][0];
        matrix[1][j] = costs[j][1];
        matrix[2][j] = costs[j][2];
      } else {
        matrix[0][j] = costs[j][0] + Math.min(matrix[1][j], matrix[2][j]);
        matrix[1][j] = costs[j][1] + Math.min(matrix[0][j], matrix[2][j]);
        matrix[2][j] = costs[j][2] + Math.min(matrix[0][j], matrix[1][j]);
      }
    }
    int min = Math.min(matrix[0][costs.length - 1], matrix[1][costs.length - 1]);
    min = Math.min(min, matrix[2][costs.length - 1]);
    return min;
  }
}
