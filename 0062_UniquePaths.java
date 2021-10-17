
public class LC62_UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        // First index of every row
        for (int i = 0; i < m; i++) {
            grid[i][0] = 1;
        }
        // First index of every column
        for (int i = 1; i < n; i++) {
            grid[0][i] = 1;
        }
        // Now we use DP to evaluate total number of ways we can reach a particular cell
        // in the grid from its adjacent cell on right and top
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                grid[row][col] = grid[row][col - 1] + grid[row - 1][col];
            }
        }
        return grid[m - 1][n - 1];

    }

}