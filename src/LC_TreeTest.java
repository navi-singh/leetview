import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


 class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

 public class LC_TreeTest {
    Map<Integer, ArrayList<Pair<Integer, Integer>>> columnMapping = new HashMap<>();
    int min = 0, max = 0;

    private void dfs(TreeNode root, int col, int row) {
        if (root == null) {
            return;
        }
        if (!columnMapping.containsKey(col)) {
            this.columnMapping.put(col, new ArrayList<Pair<Integer, Integer>>());
        }
        this.columnMapping.get(col).add(new Pair<Integer, Integer>(row, root.val));
        this.min = Math.min(min, col);
        this.min = Math.max(max, col);
        dfs(root.left, col - 1, row + 1);
        dfs(root.right, col + 1, row + 1);
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        if (root == null) {
            return result;
        }
        dfs(root, 0, 0);
        for(int i=this.min; i<= this.max; i++){
            Collections.sort(this.columnMapping.get(i), new Comparator<Pair<Integer, Integer>>(){
                @Override
                public int compare(Pair<Integer, Integer> first, Pair<Integer, Integer> second){
                    System.out.println(first.getKey());
                    return first.getKey() - second.getKey();
                }
            });

            List<Integer> list = new ArrayList();
            // columnMapping.get(i).stream().map(p -> p.getValue().val).collect(Collectors.toList());
            for(Pair<Integer, Integer> p: columnMapping.get(i)){
                list.add(p.getValue());
            }
            result.add(list);
        }
        return result;
    }
}