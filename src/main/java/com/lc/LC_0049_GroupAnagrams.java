package com.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_0049_GroupAnagrams {
  public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> res = new ArrayList();
    Map<String, List<String>> idenitfierToList = new HashMap<String, List<String>>();
    for (String str : strs) {
      char[] ch = new char[26];
      for (int i = 0; i < str.length(); ++i) {
        ch[str.charAt(i) - 'a']++;
      }
      String identifier = new String(ch);
      if (idenitfierToList.containsKey(identifier)) {
        idenitfierToList.get(identifier).add(str);
      } else {
        List<String> newList = new ArrayList<>();
        newList.add(str);
        idenitfierToList.put(identifier, newList);
      }
    }
    res.addAll(idenitfierToList.values());
    return res;
  }
}
