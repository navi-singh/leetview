public class LC_0125_ValidPalindrome {
  public boolean isPalindrome(String s) {
    if (s == null || s.isEmpty()) {
      return true;
    }
    s = s.toLowerCase();
    int start = 0, end = s.length() - 1;
    while (start < end) {
      while (start < end && !isAlNum(s.charAt(start))) {
        start++;
      }

      while (start < end && !isAlNum(s.charAt(end))) {
        end--;
      }
      if (s.charAt(start) != s.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }

  boolean isAlNum(char c) {
    return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
  }
}
