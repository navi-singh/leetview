import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LC218_SkylineProject {
  class Edge {
    int x;
    int height;
    boolean isStart;

    public Edge(int x, int height, boolean isStart) {
      this.x = x;
      this.height = height;
      this.isStart = isStart;
    }
  }

  public List<List<Integer>> getSkyline(int[][] buildings) {
    List<List<Integer>> result = new ArrayList<>();
    if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
      return result;
    }
    List<Edge> edges = new ArrayList<>();
    for (int[] building : buildings) {
      edges.add(new Edge(building[0], building[2], true));
      edges.add(new Edge(building[1], building[2], false));
    }
    Collections.sort(
        edges,
        new Comparator<Edge>() {
          public int compare(Edge a, Edge b) {
            if (a.x != b.x) {
              return Integer.compare(a.x, b.x);
            }
            if (a.isStart && b.isStart) {
              return Integer.compare(b.height, a.height);
            }
            if (!a.isStart && !b.isStart) {
              return Integer.compare(a.height, b.height);
            }
            return a.isStart ? -1 : 1;
          }
        });
    PriorityQueue<Integer> heightHeap = new PriorityQueue<>(10, Collections.reverseOrder());
    for (Edge edge : edges) {
      if (edge.isStart) {
        if (heightHeap.isEmpty() || edge.height > heightHeap.peek()) {
          result.add(new ArrayList<>(Arrays.asList(edge.x, edge.height)));
        }
        heightHeap.add(edge.height);
      } else {
        heightHeap.remove(edge.height);
        if (heightHeap.isEmpty()) {
          result.add(new ArrayList<>(Arrays.asList(edge.x, 0)));
        } else if (edge.height > heightHeap.peek()) {
          result.add(new ArrayList<>(Arrays.asList(edge.x, heightHeap.peek())));
        }
      }
    }

    return result;
  }
}
