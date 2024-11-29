import java.util.Stack;
/**
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 

Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:

Input: matrix = [["0"]]
Output: 0
Example 3:

Input: matrix = [["1"]]
Output: 1
*/
public class LC85_MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int maxSoFar = MaxInRow(matrix, 0);
        System.out.println(maxSoFar);
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != '0') {
                    matrix[row][col] = (char) ((matrix[row - 1][col] - '0' + 1) + '0');
                }
            }
            maxSoFar = Integer.max(maxSoFar, MaxInRow(matrix, row));
        }
        return maxSoFar;
    }

    private int MaxInRow(char[][] matrix, int row) {
        Stack<Integer> st = new Stack<Integer>();
        int left = 0, right = matrix[row].length;
        int height = 0, width = 0, maxArea = 0;
        while (left < right) {
            if (st.empty() || (matrix[row][st.peek()] - '0') < matrix[row][left] - '0') {
                st.push(left++);
            } else {
                height = matrix[row][st.pop()] - '0';
                width = st.empty() ? left : (left - st.peek() - 1);
                maxArea = Integer.max(maxArea, height * width);
            }
        }
        while (!st.empty()) {
            height = matrix[row][st.pop()] - '0';
            width = st.empty() ? left : (left - st.peek() - 1);
            maxArea = Integer.max(maxArea, height * width);
        }
        return maxArea;
    }
}
