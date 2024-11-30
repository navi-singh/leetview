import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Node {
  public int val;
  public List<Node> neighbors;

  public Node() {
    val = 0;
    neighbors = new ArrayList<Node>();
  }

  public Node(int _val) {
    val = _val;
    neighbors = new ArrayList<Node>();
  }

  public Node(int _val, ArrayList<Node> _neighbors) {
    val = _val;
    neighbors = _neighbors;
  }
}

public class LC_0133_CloneGraph {
  // HashMap<Node, Node> map = new HashMap<>();

  // public Node cloneGraphDFS(Node node) {
  // if (node == null) {
  // return null;
  // }
  // map.put(node, new Node(node.val, new ArrayList<>()));

  // for (Node neighbor : node.neighbors) {
  // if (map.containsKey(neighbor)) {
  // map.get(node).neighbors.add(map.get(neighbor));
  // } else {
  // map.get(node).neighbors.add(cloneGraphDFS(neighbor));
  // }
  // }

  // return map.get(node);
  // }

  public Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }
    Map<Node, Node> map = new HashMap<>();
    Queue<Node> queue = new ArrayDeque<>();
    queue.offer(node);
    map.put(node, new Node(node.val, new ArrayList<>()));
    while (!queue.isEmpty()) {
      Node nod = queue.poll();
      for (Node neighbor : nod.neighbors) {
        if (!map.containsKey(neighbor)) {
          map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
          queue.offer(neighbor);
        }
        map.get(nod).neighbors.add(map.get(neighbor));
      }
    }
    return map.get(node);
  }
}
