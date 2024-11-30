package com.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * You are given a string s and an array of strings words of the same length. Return all starting
 * indices of substring(s) in s that is a concatenation of each word in words exactly once, in any
 * order, and without any intervening characters.
 *
 * <p>You can return the answer in any order.
 *
 * <p>Example 1:
 *
 * <p>Input: s = "barfoothefoobarman", words = ["foo","bar"] Output: [0,9] Explanation: Substrings
 * starting at index 0 and 9 are "barfoo" and "foobar" respectively. The output order does not
 * matter, returning [9,0] is fine too.
 *
 * <p>Example 2:
 *
 * <p>Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"] Output: []
 *
 * <p>Example 3:
 *
 * <p>Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"] Output: [6,9,12]
 */
public class LC_0030_SubstringWithAllWord{
  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> res = new ArrayList<>();
    if (s.isEmpty() || words.length < 1) return res;

    int wordLen = words[0].length();
    int totalLen = words.length * wordLen;
    Map<String, Integer> wordLookup = new HashMap<>();
    if (totalLen > s.length()) return res;
    for (String word : words) {
      wordLookup.put(word, wordLookup.containsKey(word) ? wordLookup.get(word) + 1 : 1);
    }
    for (int i = 0; i <= (s.length() - totalLen); ++i) {
      Map<String, Integer> temp = new HashMap<String, Integer>();
      int j = 0;
      for (; j < totalLen; j += wordLen) {
        String word = s.substring(i + j, i + j + wordLen);
        if (!wordLookup.containsKey(word)) break;
        int currentCount = temp.getOrDefault(word, 0);
        if (temp.getOrDefault(word, 0) >= wordLookup.get(word)) break;
        temp.put(word, currentCount + 1);
      }
      if (j >= totalLen) res.add(i);
    }
    return res;
  }
}
