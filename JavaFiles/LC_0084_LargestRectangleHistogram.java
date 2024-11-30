import java.util.Stack;

public class LC_0084_LargestRectangleHistogram {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<Integer>();
        int left = 0, right = heights.length;
        int height = 0, width = 0, maxArea = 0;
        while (left < right) {
            if (st.empty() || heights[st.peek()] < heights[left]) {
                st.push(left++);
            } else {
                height = heights[st.pop()];
                width = st.empty() ? left : (left - st.peek() - 1);
                maxArea = Integer.max(maxArea, height * width);
            }
        }
        while (!st.empty()) {
            height = heights[st.pop()];
            width = st.empty() ? left : (left - st.peek() - 1);
            maxArea = Integer.max(maxArea, height * width);
        }
        return maxArea;
    }
}