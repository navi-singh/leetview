import java.util.ArrayList;
import java.util.List;

public class LC254_FactorCombinations {
  public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> result = new ArrayList();
    List<Integer> temp = new ArrayList();
    factors(n, temp, result);
    return result;
  }

  private void factors(int n, List<Integer> temp, List<List<Integer>> result) {
    int index = temp.isEmpty() ? 2 : temp.get(temp.size() - 1);
    for (; index <= n / index; index++) {
      if (n % index == 0) {
        temp.add(index);
        temp.add(n / index);
        result.add(new ArrayList<Integer>(temp));
        temp.remove(temp.size() - 1);
        factors(n / index, temp, result);
        temp.remove(temp.size() - 1);
      }
    }
  }
}
