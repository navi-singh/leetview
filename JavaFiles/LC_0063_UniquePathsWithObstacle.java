public class LC_0063_UniquePathsWithObstacle {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;
        int[][] grid = new int[m][n];
        grid[0][0] = 1;
        ;
        // First index of every row
        for (int i = 1; i < m; i++) {
            grid[i][0] = obstacleGrid[i][0] == 1 ? 0 : grid[i - 1][0];
        }
        // First index of every column
        for (int i = 1; i < n; i++) {
            grid[0][i] = obstacleGrid[0][i] == 1 ? 0 : grid[0][i - 1];
        }
        // Now we use DP to evaluate total number of ways we can reach a particular cell
        // in the grid from its adjacent cell on right and top
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (obstacleGrid[row][col] == 1) {
                    grid[row][col] = 0;
                } else {
                    grid[row][col] = grid[row][col - 1] + grid[row - 1][col];
                }
            }
        }
        return grid[m - 1][n - 1];
    }
}