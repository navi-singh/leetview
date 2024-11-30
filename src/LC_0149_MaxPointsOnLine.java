import java.util.HashMap;
import java.util.Map;

public class LC_0149_MaxPointsOnLine {
  public int maxPoints(int[][] points) {
    int max = 0;
    if (points == null || points.length == 0) return max;
    Map<Double, Integer> slopeCount = new HashMap<Double, Integer>();
    for (int i = 0; i < points.length; i++) {
      int vertical = 0;
      int duplicate = 1;
      for (int j = i + 1; j < points.length; j++) {
        if (points[j][0] == points[i][0]) {
          if (points[j][1] == points[i][1]) {
            duplicate++;
          } else {
            vertical++;
          }
        } else {
          double slope =
              points[j][1] == points[i][1]
                  ? 0.0
                  : (1 * (points[j][1] - points[i][1])) / (points[j][0] - points[i][0]);
          if (slopeCount.containsKey(slope)) {
            slopeCount.put(slope, slopeCount.get(slope) + 1);
          } else {
            slopeCount.put(slope, 1);
          }
        }
      }
      for (int counts : slopeCount.values()) {
        if (counts + duplicate > max) {
          max = counts + duplicate;
        }
      }
      max = Math.max(vertical + duplicate, max);
      slopeCount.clear();
    }
    return max;
  }
}
