package com.lc;

/**
 * public class LC_0014_LongestCommonPrefix{ public String longestCommonPrefix(String[] strs) { if(strs == null ||
 * strs.length <1){ return ""; } int min = 0; for(int i=0;i<strs[0].length(); i++){ for(int
 * j=0;j<strs.length; j++){ if(strs[j].length() <= i || (strs[j].charAt(i) != strs[0].charAt(i))) {
 * return strs[0].substring(0,i); } } } return strs[0]; } }
 */
public class LC_0014_LongestCommonPrefix{

  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    int minLen = Integer.MAX_VALUE;
    for (String str : strs) minLen = Math.min(minLen, str.length());
    int low = 1;
    int high = minLen;
    while (low <= high) {
      int middle = (low + high) / 2;
      if (isCommonPrefix(strs, middle)) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return strs[0].substring(0, (low + high) / 2);
  }

  private boolean isCommonPrefix(String[] strs, int len) {
    String str1 = strs[0].substring(0, len);
    for (int i = 1; i < strs.length; i++) {
      if (!strs[i].startsWith(str1)) return false;
    }
    return true;
  }
}
