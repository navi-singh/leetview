import java.util.HashMap;

public class LC146_LruCache {

  HashMap<Integer, Node> map = null;
  int cap = 0;
  Node head, tail;

  public LRUCache(int capacity) {
    this.map = new HashMap<Integer, Node>();
    this.cap = capacity;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Node n = map.get(key);
      removeNode(n);
      addNode(n);
      return n.val;
    }
    return -1;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      Node n = map.get(key);
      n.val = value;
      removeNode(n);
      addNode(n);
    } else {
      if (map.size() >= this.cap) {
        map.remove(head.key);
        removeNode(head);
      }
      Node n = new Node(key, value);
      addNode(n);
      map.put(key, n);
    }
  }

  private void removeNode(Node n) {
    if (n.prev == null) {
      head = n.next;
    } else {
      n.prev.next = n.next;
    }
    if (n.next == null) {
      tail = n.prev;
    } else {
      n.next.prev = n.prev;
    }
  }

  private void addNode(Node n) {
    if (tail != null) {
      tail.next = n;
    }
    n.prev = tail;
    n.next = null;
    tail = n;
    if (head == null) {
      head = n;
    }
  }

  class Node {
    int val;
    Node prev;
    Node next;
    int key;

    Node(int key, int val) {
      this.key = key;
      this.val = val;
    }
  }
}
