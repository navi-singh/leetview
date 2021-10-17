import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LC187_RepeatedDNASequence {
  public List<String> findRepeatedDnaSequences(String s) {
    List<String> result = new ArrayList<>();
    if (s == null || s.isEmpty()) {
      return result;
    }
    Map<Character, Integer> dict = new HashMap<>();
    dict.put('A', 0);
    dict.put('C', 1);
    dict.put('G', 2);
    dict.put('T', 3);
    int hash = 0;
    int mask = (1 << 20) - 1;

    Set<Integer> added = new HashSet<Integer>();
    Set<Integer> temp = new HashSet<Integer>();
    for (int i = 0; i < s.length(); i++) {
      hash = (hash << 2) + dict.get(s.charAt(i));
      if (i >= 9) {
        hash &= mask;
        if (temp.contains(hash) & !added.contains(hash)) {
          result.add(s.substring(i - 9, i + 1));
          added.add(hash);
        } else {
          temp.add(hash);
        }
      }
    }

    return result;
  }
}
