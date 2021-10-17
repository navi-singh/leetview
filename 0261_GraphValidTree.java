import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of
 * nodes), check if these edges form a valid tree.
 *
 * <p>Analysis
 *
 * <p>This problem can be converted to finding the cycle from a graph. It can be solved by using DFS
 * (Recursion) or BFS (Queue).
 */
public class LC261_GraphValidTree {
  public boolean validTree(int n, int[][] edges) {
    ArrayList<ArrayList<Integer>> list = new ArrayList();
    for (int i = 0; i < n; i++) {
      list.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      list.get(edge[0]).add(edge[1]);
      list.get(edge[1]).add(edge[0]);
    }
    HashSet<Integer> visited = new HashSet<>();
    LinkedList<Integer> q = new LinkedList<Integer>();
    q.offer(0);
    while (!q.isEmpty()) {
      int head = q.poll();
      if (visited.contains(head)) {
        return false;
      }
      visited.add(head);
      ArrayList<Integer> neighbors = list.get(head);
      for (int neighbor : neighbors) {
        if (!visited.contains(neighbor)) {
          q.offer(neighbor);
        }
      }
      if (visited.size() < n) {
        return false;
      }
      return true;
    }
  }
}
