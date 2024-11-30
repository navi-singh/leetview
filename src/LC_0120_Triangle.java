import java.util.ArrayList;
import java.util.List;

public class LC_0120_Triangle {
  public int minimumTotal(List<List<Integer>> triangle) {
    if (triangle.size() < 1 || triangle.get(0).size() < 1) {
      return 0;
    }
    List<Integer> res = new ArrayList<>(triangle.get(triangle.size() - 1));
    for (int row = triangle.size() - 2; row >= 0; row--) {
      for (int j = 0; j <= row; j++) {
        res.set(j, Math.min(res.get(j), res.get(j + 1)) + triangle.get(row).get(j));
      }
    }
    return res.get(0);
  }
}
