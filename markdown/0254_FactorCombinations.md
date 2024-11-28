# Factor combinations
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.  
Example 1:  
Input: 1  
Output: []

Example 2:  
Input: 37  
Output:[]

Example 3:  
Input: 12. 
Output:  
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]

Example 4:  
Input: 32  
Output:
> [  
  [2, 16],  
  [2, 2, 8],  
  [2, 2, 2, 4],  
  [2, 2, 2, 2, 2],  
  [2, 4, 4],  
  [4, 8]  
]  

<strong> Time: O(nlogn)  
Space: O(logn) </strong>
```java
class Solution {
  public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> ans = new ArrayList<>();
    dfs(n, 2, new ArrayList<>(), ans);
    return ans;
  }

  private void dfs(int n, int s, List<Integer> path, List<List<Integer>> ans) {
    if (n == 1) {
      if (path.size() > 1)
        ans.add(new ArrayList<>(path));
      return;
    }
    for (int i = s; i <= n; ++i)
      if (n % i == 0) {
        path.add(i);
        dfs(n / i, i, path, ans);
        path.remove(path.size() - 1);
      }
  }
}

```
```java
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
```