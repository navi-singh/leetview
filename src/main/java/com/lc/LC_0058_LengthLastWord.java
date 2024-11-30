package com.lc;

/**
 * Given a string s consisting of words and spaces, return the length of the last word in the
 * string.
 *
 * <p>A word is a maximal substring consisting of non-space characters only.
 *
 * <p>Example 1:
 *
 * <p>Input: s = "Hello World" Output: 5 Explanation: The last word is "World" with length 5.
 * Example 2:
 *
 * <p>Input: s = " fly me to the moon " Output: 4 Explanation: The last word is "moon" with length
 * 4. Example 3:
 *
 * <p>Input: s = "luffy is still joyboy" Output: 6 Explanation: The last word is "joyboy" with
 * length 6.
 */
public class LC_0058_LengthLastWord {
  public int lengthOfLastWord(String s) {
    if (s == null || s.length() < 1) {
      return 0;
    }
    int firstNonSpace = s.length() - 1;
    while (s.charAt(firstNonSpace) == ' ') {
      if (firstNonSpace == 0) {
        return 0;
      }
      firstNonSpace--;
    }
    int left = firstNonSpace;
    while (left >= 0 && s.charAt(left) != ' ') {
      left--;
    }
    int length = firstNonSpace - left;
    return left == 0 ? s.charAt(left) == ' ' ? length : length + 1 : length;
  }
}
