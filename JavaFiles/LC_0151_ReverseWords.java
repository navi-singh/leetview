public class LC151_ReverseWords {
  public String reverseWords(String s) {
    char[] arr = s.toCharArray();
    int start = 0;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == ' ') {
        reverse(arr, start, i - 1);
        start = i + 1;
      }
    }
    reverse(arr, start, arr.length - 1);
    reverse(arr, 0, arr.length - 1);
    return new String(arr);
  }

  private void reverse(char[] s, int start, int end) {
    while (start < end) {
      char temp = s[start];
      s[start] = s[end];
      s[end--] = s[start++];
    }
  }

  public String reverseWords(String s) {
    if (s == null || s.isEmpty()) return s;
    String arr[] = s.split(" ");
    StringBuilder sb = new StringBuilder();
    for (int i = arr.length; i >= 0; i--) {
      if (!arr[i].equals(" ")) {
        sb.append(arr[i]).append(" ");
      }
    }
    return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
  }
}
