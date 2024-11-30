import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC_0205_IsomorphicStrings {
  // public boolean isIsomorphic(String s, String t) {
  // if (s == null || t == null || s.length() != t.length()) {
  // return false;
  // }
  // Map<Character, Character> sToTMap = new HashMap<>();
  // Map<Character, Character> tToSMap = new HashMap<>();
  // for (int i = 0; i < s.length(); i++) {
  // if (sToTMap.containsKey(s.charAt(i))) {
  // if (sToTMap.get(s.charAt(i)) != t.charAt(i)) {
  // return false;
  // }
  // } else {
  // if (tToSMap.containsKey(t.charAt(i))) {
  // return false;
  // }
  // sToTMap.put(s.charAt(i), t.charAt(i));
  // tToSMap.put(t.charAt(i), s.charAt(i));
  // }
  // }
  // return false;
  // }
  public boolean isIsomorphic(String s, String t) {
    if (s == null || t == null || s.length() != t.length()) {
      return false;
    }
    Map<Character, Character> sToTMap = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      if (sToTMap.containsKey(s.charAt(i))) {
        if (sToTMap.get(s.charAt(i)) != t.charAt(i)) {
          return false;
        }
      } else {
        sToTMap.put(s.charAt(i), t.charAt(i));
      }
    }

    Set<Character> set = new HashSet<>(sToTMap.values());
    if (set.size() != sToTMap.values().size()) {
      return false;
    }
    return true;
  }
}
