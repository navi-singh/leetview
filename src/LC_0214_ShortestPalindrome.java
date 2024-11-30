public class LC_0214_ShortestPalindrome {
  public String shortestPalindrome(String s) {
    int i = 0;

    for (int j = s.length() - 1; j >= 0; j--) {
      if (s.charAt(i) == s.charAt(j)) {
        i++;
      }
    }

    if (i == s.length()) return s;

    String suffix = s.substring(i);
    String prefix = new StringBuilder(suffix).reverse().toString();
    String mid = shortestPalindrome(s.substring(0, i));
    return prefix + mid + suffix;
  }
}
