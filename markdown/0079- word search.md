Given an array of `intervals` where `intervals[i] = [starti, endi]`, merge all overlapping intervals, and return _an array of the non-overlapping intervals that cover all the intervals in the input_.

**Example 1:**

**Input:** intervals = \[\[1,3\],\[2,6\],\[8,10\],\[15,18\]\]
**Output:** \[\[1,6\],\[8,10\],\[15,18\]\]
**Explanation:** Since intervals \[1,3\] and \[2,6\] overlaps, merge them into \[1,6\].

**Example 2:**

**Input:** intervals = \[\[1,4\],\[4,5\]\]
**Output:** \[\[1,5\]\]
**Explanation:** Intervals \[1,4\] and \[4,5\] are considered overlapping.

**Constraints:**

- `1 <= intervals.length <= 104`
- `intervals[i].length == 2`
- `0 <= starti <= endi <= 104`

```java
public class LC79_WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board.length < 1 || board[0].length < 1) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (pathExist(board, word, visited, 0, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean pathExist(char[][] board, String word, boolean[][] visited, int index, int row, int col) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || word.charAt(index) != board[row][col]
                || visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        if (pathExist(board, word, visited, index + 1, row + 1, col)
                || pathExist(board, word, visited, index + 1, row - 1, col)
                || pathExist(board, word, visited, index + 1, row, col + 1)
                || pathExist(board, word, visited, index + 1, row, col - 1)) {
            return true;
        }
        visited[row][col] = false;
        return false;
    }
}
```
