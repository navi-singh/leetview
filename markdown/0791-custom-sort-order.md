# [791. Custom Sort String](https://leetcode.com/problems/custom-sort-string/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

You are given two strings `order` and `s`. All the characters of `order` are **unique**  and were sorted in some custom order previously.

Permute the characters of `s` so that they match the order that `order` was sorted. More specifically, if a character `x` occurs before a character `y` in `order`, then `x` should occur before `y` in the permuted string.

Return any permutation of `s` that satisfies this property.

**Example 1:** 

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: 0.875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
Input:   order = "cba", s = "abcd" 

Output:   "cbad" 

Explanation:  `"a"`, `"b"`, `"c"` appear in order, so the order of `"a"`, `"b"`, `"c"` should be `"c"`, `"b"`, and `"a"`.

Since `"d"` does not appear in `order`, it can be at any position in the returned string. `"dcba"`, `"cdba"`, `"cbda"` are also valid outputs.

**Example 2:** 

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: 0.875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
Input:   order = "bcafg", s = "abcd" 

Output:   "bcad" 

Explanation:  The characters `"b"`, `"c"`, and `"a"` from `order` dictate the order for the characters in `s`. The character `"d"` in `s` does not appear in `order`, so its position is flexible.

Following the order of appearance in `order`, `"b"`, `"c"`, and `"a"` from `s` should be arranged as `"b"`, `"c"`, `"a"`. `"d"` can be placed at any position since it's not in order. The output `"bcad"` correctly follows this rule. Other arrangements like `"dbca"` or `"bcda"` would also be valid, as long as `"b"`, `"c"`, `"a"` maintain their order.

### First solution
```java
class Solution {
    public String customSortString(String order, String s) {
        int N = s.length();
        Character[] result = new Character[N];
        for (int i = 0; i < N; i++) {
            result[i] = s.charAt(i);
        }

        Arrays.sort(result, (c1, c2) -> {
            // The index of the character in order determines the value to be sorted by
            return order.indexOf(c1) - order.indexOf(c2);
        });

        String resultString = "";
        for (Character c: result) {
            resultString += c;
        }
        return resultString;
    }
}
```

```python
class Solution(object):
    def customSortString(self, order, s):
        result = list(s)        
        result.sort(key=lambda c: order.index(c) if c in order else float('inf'))
        return ''.join(result)
```
#### Complexity Analysis
Here, we define N as the length of string s, and K as the length of string order.

- Time Complexity: O(NlogN)
Sorting an array of length N requires O(NlogN) time, and the indices of order have to be retrieved for each distinct letter, which results in an O(NlogN+K) complexity. K is at most 26, the number of unique English letters, so we can simplify the time complexity to O(NlogN).

- Space Complexity: O(N) or O(log⁡N)
### Optimized solution

```java
class Solution {
    public String customSortString(String order, String s) {
        // Create a frequency table
        Map<Character, Integer> freq = new HashMap<>();

        // Initialize frequencies of letters
        // freq[c] = frequency of char c in s
        int N = s.length();
        for (int i = 0; i < N; i++) {
            char letter = s.charAt(i);
            freq.put(letter, freq.getOrDefault(letter, 0) + 1);
        }

        // Iterate order string to append to result
        int K = order.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < K; i++) {
            char letter = order.charAt(i);
            while (freq.getOrDefault(letter, 0) > 0) {
                result.append(letter);
                freq.put(letter, freq.get(letter) - 1);
            }
        }

        // Iterate through freq and append remaining letters
        // This is necessary because some letters may not appear in `order`
        for (char letter : freq.keySet()) {
            int count = freq.get(letter);
            while (count > 0) {
                result.append(letter);
                count--;
            }
        }

        // Return the result
        return result.toString();
    }
}
```
``` python
class Solution:
    def customSortString(self, order: str, s: str) -> str:
        # Create a frequency table
        freq = {}

        # Initialize frequencies of letters in `s`
        for letter in s:
            freq[letter] = freq.get(letter, 0) + 1

        # Append characters from `order` to the result based on frequency
        result = []
        for letter in order:
            if letter in freq:
                result.append(letter * freq[letter])
                del freq[letter]  # Remove the letter from the frequency map

        # Append remaining letters that are not in `order`
        for letter, count in freq.items():
            result.append(letter * count)

        # Join the result list into a string and return
        return ''.join(result)

```
#### Complexity Analysis
- Time Complexity: O(N)

It takes O(N) time to populate the frequency table, and all other hashmap operations performed take O(1) time in the average case. Building the result string also takes O(N) time because each letter from s is appended to the result in the custom order, making the overall time complexity O(N).

- Space Complexity: O(N)

A hash map and a result string are created, which results in an additional space complexity of O(N).

