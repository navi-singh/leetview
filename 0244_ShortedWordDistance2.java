/**
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list
 * of words and your method will be called repeatedly many times with different parameters. How
 * would you optimize it?
 *
 * <p>Design a class which receives a list of words in the constructor, and implements a method that
 * takes two words word1 and word2 and return the shortest distance between these two words in the
 * list.
 *
 * <p>For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * <p>Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding",
 * return 1.
 */
public class LC244_ShortedWordDistance2 {
  HashMap<String, ArrayList<Integer>> map;

  public WordDistance(String[] words) {
    map = new HashMap<String, ArrayList<Integer>>();
    for (int i = 0; i < words.length; i++) {
      if (map.containsKey(words[i])) {
        map.get(words[i]).add(i);
      } else {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(i);
        map.put(words[i], list);
      }
    }
  }

  public int shortest(String word1, String word2) {

    ArrayList<Integer> l1 = map.get(word1);
    ArrayList<Integer> l2 = map.get(word2);

    int result = Integer.MAX_VALUE;
    for (int i1 : l1) {
      for (int i2 : l2) {
        result = Math.min(result, Math.abs(i1 - i2));
      }
    }
    return result;
  }
}
