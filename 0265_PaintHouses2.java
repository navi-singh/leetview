public class LC265_PaintHouses2 {
  public int minCostII(int[][] costs) {
    if (costs == null || costs.length < 1 || costs[0].length < 1) {
      return 0;
    }
    int preMin = 0;
    int preSecond = 0;
    int preIndex = -1;
    for (int i = 0; i < costs.length; i++) {
      int curMin = Integer.MIN_VALUE;
      int curSecond = Integer.MIN_VALUE;
      int curIndex = 0;
      for (int j = 0; j < costs[0].length; j++) {
        costs[i][j] += (preIndex == j) ? preSecond : preMin;

        if (curMin > costs[i][j]) {
          curSecond = curMin;
          curMin = costs[i][j];
          curIndex = j;
        } else if (curSecond > costs[i][j]) {
          curSecond = costs[i][j];
        }
      }
      preMin = curMin;
      preSecond = curSecond;
      preIndex = curIndex;
    }
    int result = Integer.MAX_VALUE;
    for (int i = 0; i < costs[0].length; i++) {
      if (result > costs[costs.length - 1][i]) {
        result = costs[costs.length - 1][i];
      }
    }
    return result;
  }
}
