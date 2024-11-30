package com.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LC_0056_MergeIntervals {
  public int[][] merge(int[][] intervals) {
    if (intervals.length <= 1) {
      return intervals;
    }
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    Arrays.sort(
        intervals,
        new Comparator<int[]>() {
          @Override
          public int compare(int[] a, int[] b) {
            return Integer.compare(a[0], b[0]);
          }
        });

    int start = intervals[0][0], end = intervals[0][1];
    for (int i = 0; i < intervals.length; ++i) {

      if (intervals[i][0] > end) {
        addList(res, start, end);
        start = intervals[i][0];
        end = intervals[i][1];
      } else {
        end = Math.max(end, intervals[i][1]);
      }
    }
    addList(res, start, end);
    int[][] result = new int[res.size()][2];
    for (int i = 0; i < res.size(); ++i) {
      result[i][0] = res.get(i).get(0);
      result[i][1] = res.get(i).get(1);
    }
    return result;
  }

  private void addList(List<List<Integer>> res, int start, int end) {
    List<Integer> interval = new ArrayList<Integer>();
    interval.add(start);
    interval.add(end);
    res.add(interval);
  }
}
