# 266. Palindrome Permutation

Given a string, determine if a permutation of the string could form a palindrome.

For example

>"code" -> False,  
 "aab" -> True,  
 "carerac" -> True.

***Time: O(n)  
Space: O(n)***
```java
public class LC266_PalindromePermutation {
  // boolean canPermutePalindrome(String s) {
  // BitSet bitSet = new BitSet();
  // for (int i = 0; i < s.length(); i++) {
  // bitSet.flip(s.charAt(i));
  // }
  // return bitSet.count() < 2;
  // }
  public boolean canPermutePalindrome(String s) {
    HashSet<Character> app = new HashSet<Character>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (app.contains(c)) {
        app.remove(c);
      } else {
        app.add(c);
      }
    }
    return app.size() <= 1;
  }
}
```