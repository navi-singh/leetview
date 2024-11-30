package com.lc;

import java.util.LinkedList;

public class LC_0239_SlidingWindowMaximum {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length < 1 || k < 1) {
      return null;
    }

    // 1 2 3 4 5 k =3
    int[] res = new int[nums.length - k + 1];
    LinkedList<Integer> deque = new LinkedList<Integer>();
    for (int i = 0; i < nums.length; i++) {
      if (!deque.isEmpty() && deque.peekFirst() == i - k) {
        deque.poll();
      }
      while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
        deque.removeLast();
      }
      deque.offer(i);
      if (i + 1 >= k) {
        res[i - k + 1] = nums[deque.peek()];
      }
    }
    return res;
  }
}
