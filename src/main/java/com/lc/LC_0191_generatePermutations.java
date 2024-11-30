package com.lc;

import java.util.ArrayList;
import java.util.List;


public class LC_0191_generatePermutations {
  public static void main(String[] args) {
    System.out.println("Hello World");

    generatePermutations("abc");
  }

  public static List<String> generatePermutations(String s) {
    List<String> result = new ArrayList<>();
    // base case
    if (s == null || s.length() == 0) {
      return result;
    }

    backtrack(s, result, "");
    return result;
  }

  public static void backtrack(String s, List<String> result, String temp) {
    if (temp.length() == s.length()) {
      result.add(new String(temp));
      return;
    }

    for (int i = 0; i < s.length(); i++) {
      if (temp.contains(String.valueOf(s.charAt(i)))) {
        continue;
      }
      temp += s.charAt(i); // temp abc
      backtrack(s, result, temp);
      temp.substring(0, temp.length() - 1);
    }
  }
}
