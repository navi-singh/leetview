package com.lc;

import java.util.HashMap;
import java.util.Map;

public class LC_0076_MinWindowSubString {

  public String minWindow(String s, String t) {
    int tLen = t.length(), sLen = s.length();
    int total = 0;
    String result = "";
    int minLen = Integer.MAX_VALUE;
    Map<Character, Integer> lookup = new HashMap<Character, Integer>();
    Map<Character, Integer> target = new HashMap<Character, Integer>();

    for (int i = 0; i < t.length(); i++) {
      target.put(t.charAt(i), target.getOrDefault(t.charAt(i), 0) + 1);
    }
    for (int left = 0, right = 0; right < sLen; right++) {
      char c = s.charAt(right);
      if (!target.containsKey(c)) {
        continue;
      }
      int count = lookup.getOrDefault(c, 0);
      if (count < target.get(c)) {
        total++;
      }
      lookup.put(c, count + 1);
      if (total == tLen) {
        while (!target.containsKey(s.charAt(left))
            || lookup.get(s.charAt(left)) > target.get(s.charAt(left))) {
          char ch = s.charAt(left);
          if (target.containsKey(ch) && lookup.get(ch) > target.get(ch)) {
            lookup.put(ch, lookup.get(ch) - 1);
          }
          left++;
        }
        if (minLen > right - left + 1) {
          minLen = right - left + 1;
          result = s.substring(left, right + 1);
        }
      }
    }
    return result;
  }
}
