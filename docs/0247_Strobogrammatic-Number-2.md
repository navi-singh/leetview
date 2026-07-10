A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example, Given n = 2, return ["11","69","88","96"].

```java
class Solution {
  public List<String> findStrobogrammatic(int n) {
    return helper(n, n);
  }

  private List<String> helper(int n, int k) {
    if (n == 0)
      return new ArrayList<>(Arrays.asList(""));
    if (n == 1)
      return new ArrayList<>(Arrays.asList("0", "1", "8"));

    List<String> ans = new ArrayList<>();

    for (final String inner : helper(n - 2, k)) {
      if (n < k)
        ans.add("0" + inner + "0");
      ans.add("1" + inner + "1");
      ans.add("6" + inner + "9");
      ans.add("8" + inner + "8");
      ans.add("9" + inner + "6");
    }

    return ans;
  }
}

```
