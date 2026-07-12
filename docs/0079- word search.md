---
description: MEDIUM
---

# 79. Word Search

Given an `m x n` grid of characters `board` and a string `word`, return `true` _if_ `word` _exists in the grid_.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

## In Plain Terms

Starting from any cell, try to spell out `word` by stepping to horizontally or vertically adjacent cells, one letter at a time, without reusing a cell on the current path. Return `true` if some path spells the whole word. In Example 1, `"ABCCED"` is traced by walking `A→B→C→C→E→D` through neighboring cells.

```text
board:                 path for "ABCCED":
  A  B  C  E             A →B →C  E
  S  F  C  S                     ↓
  A  D  E  E             A  D  E ←C
                         ↑     ↓
                         (D)← (E)      ->  true
```

**Example 1:**

```text
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
```

**Example 2:**

```text
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
```

**Example 3:**

```text
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
```

**Constraints:**

- `m == board.length`
- `n == board[i].length`
- `1 <= m, n <= 6`
- `1 <= word.length <= 15`
- `board` and `word` consists of only lowercase and uppercase English letters.

> **Follow-up:** Could you use search pruning to make your solution faster with a larger `board`?

---

## Approach: DFS Backtracking

Try to start the word from every cell. From a cell, run a depth-first search that matches the current character, marks the cell as visited, and recurses into its four neighbors for the next character. If a branch fails, unmark the cell (backtrack) so it can be reused by other paths. A path succeeds once every character in `word` has been matched.

#### Complexity Analysis

- **Time complexity: O(m · n · 4^L).** Where `L` is the length of `word`. Each of the `m · n` cells can start a search, and each search branches into up to 4 directions per character.
- **Space complexity: O(L).** The recursion stack goes as deep as the word length; the `visited` grid adds O(m · n).

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

**Key insight:** Marking a cell visited *before* recursing and unmarking it *after* (backtracking) ensures each path never reuses a cell, while still leaving that cell available for entirely different starting paths.
