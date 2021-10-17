import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" ->
 * "bcd". We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz".
 *
 * <p>Given a list of strings which contains only lowercase alphabets, group all strings that belong
 * to the same shifting sequence, return:
 *
 * <p>[ ["abc","bcd","xyz"], ["az","ba"], ["acef"], ["a","z"] ]
 */
public class LC249_GroupShiftedStrings {
  public List<List<String>> groupStrings(String[] strings) {
    List<List<String>> res = new ArrayList<List<String>>();
    Map<String, ArrayList<String>> holder = new HashMap<String, ArrayList<String>>();
    for (String str : strings) {
      char[] arr = str.toCharArray();
      if (arr.length > 0) {
        int diff = arr[0] - 'a';
        for (int i = 0; i < arr.length; i++) {
          if (arr[i] - diff < 'a') {
            arr[i] = (char) (arr[i] - diff + 26);
          } else {
            arr[i] = (char) (arr[i] - diff);
          }
        }
      }
      String temp = new String(arr);
      if (holder.containsKey(temp)) {
        holder.get(temp).add(str);
      } else {
        ArrayList<String> lis = new ArrayList<>();
        lis.add(str);
        holder.put(temp, lis);
      }
    }
    res.addAll(holder.values());
  }
}
