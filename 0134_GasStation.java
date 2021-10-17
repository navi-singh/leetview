public class LC134_GasStation {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int curr = 0, total = 0, diff = 0;
    int startIndex = 0;
    for (int i = 0; i < gas.length; i++) {
      diff = gas[i] - cost[i];
      curr += diff;
      total += diff;
      if (curr < 0) {
        curr = 0;
        startIndex = i + 1;
      }
    }
    return total >= 0 ? startIndex : -1;
  }
}
