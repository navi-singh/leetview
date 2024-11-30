public class LC_0201_BitwiseAndRange {
  public int rangeBitwiseAnd(int m, int n) {
    while (n > m) {
      n = n & n - 1;
    }
    return m & n;
  }
}
