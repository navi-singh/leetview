package com.lc;

/**
 * Implement strStr().
 *
 * <p>Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of
 * haystack.
 *
 * <p>Clarification:
 *
 * <p>What should we return when needle is an empty string? This is a great question to ask during
 * an interview.
 *
 * <p>For the purpose of this problem, we will return 0 when needle is an empty string. This is
 * consistent to C's strstr() and Java's indexOf(). Example 1:
 *
 * <p>Input: haystack = "hello", needle = "ll" Output: 2
 *
 * <p>Example 2:
 *
 * <p>Input: haystack = "aaaaa", needle = "bba" Output: -1
 *
 * <p>Example 3:
 *
 * <p>Input: haystack = "", needle = "" Output: 0
 */
public class LC_0028_StrStr {
  public int strStr(String haystack, String needle) {
    if (needle == haystack) return 0;
    int hayLength = haystack.length();
    int needleLength = needle.length();
    if (needleLength > hayLength) return -1;
    int nIndex = 0;
    for (int index = 0; index < hayLength; ++index) {
      nIndex = 0;
      while (nIndex < needleLength
          && (index + nIndex) < hayLength
          && haystack.charAt(index + nIndex) == needle.charAt(nIndex)) {
        nIndex++;
      }
      if (nIndex == needleLength) return index;
    }
    return -1;
  }
}
