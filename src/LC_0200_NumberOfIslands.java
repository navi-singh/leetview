/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return
 * the number of islands.
 *
 * <p>An island is surrounded by water and is formed by connecting adjacent lands horizontally or
 * vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * <p>Example 1: Input: grid = [ ["1","1","1","1","0"], ["1","1","0","1","0"],
 * ["1","1","0","0","0"], ["0","0","0","0","0"] ] Output: 1
 *
 * <p>Example 2:
 *
 * <p>Input: grid = [ ["1","1","0","0","0"], ["1","1","0","0","0"], ["0","0","1","0","0"],
 * ["0","0","0","1","1"] ] Output: 3
 */
public class LC_0200_NumberOfIslands {
  public int numIslands(char[][] grid) {
    if (grid == null || grid[0].length == 0) {
      return 0;
    }
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1') {
          count++;
          dfs(grid, i, j);
        }
      }
    }
    return count;
  }

  private void dfs(char[][] grid, int i, int j) {
    int m = grid.length;
    int n = grid[0].length;

    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') return;
    grid[i][j] = 'X';
    dfs(grid, i - 1, j);
    dfs(grid, i + 1, j);
    dfs(grid, i, j - 1);
    dfs(grid, i, j + 1);
  }
}
