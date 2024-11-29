import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You
 * are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take
 * course bi first if you want to take course ai.
 *
 * <p>For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 *
 * <p>Return true if you can finish all courses. Otherwise, return false.
 *
 * <p>Example 1:
 *
 * <p>Input: numCourses = 2, prerequisites = [[1,0]] Output: true Explanation: There are a total of
 * 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 *
 * <p>Example 2:
 *
 * <p>Input: numCourses = 2, prerequisites = [[1,0],[0,1]] Output: false Explanation: There are a
 * total of 2 courses to take. To take course 1 you should have finished course 0, and to take
 * course 0 you should also have finished course 1. So it is impossible.
 */
public class LC207_CourseSchedule {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites == null) {
      return false;
    }
    int len = prerequisites.length;
    int[] visit = new int[numCourses];
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int[] a : prerequisites) {
      List<Integer> lis = map.getOrDefault(a[1], new ArrayList<>());
      lis.add(a[0]);
      map.put(a[1], lis);
    }

    for (int i = 0; i < numCourses; i++) {
      if (!canFinishDFS(map, visit, i)) {
        return false;
      }
    }
    return true;
  }

  private boolean canFinishDFS(Map<Integer, List<Integer>> map, int[] visit, int i) {
    if (visit[i] == -1) {
      return false;
    }
    if (visit[i] == 1) {
      return true;
    }
    visit[i] = -1;
    if (map.containsKey(i)) {
      for (int j : map.get(i)) {
        if (!canFinishDFS(map, visit, j)) {
          return false;
        }
      }
    }
    visit[i] = 1;
    return true;
  }
}
