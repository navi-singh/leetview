
public class LC_0079_WordSearch {
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