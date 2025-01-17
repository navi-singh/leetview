# [249. Group Shifted Strings](https://leetcode.com/problems/group-shifted-strings/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

Perform the following shift operations on a string:

- **Right shift** : Replace every letter with the **successive**  letter of the English alphabet, where 'z' is replaced by 'a'. For example, `"abc"` can be right-shifted to `"bcd" `or `"xyz"` can be right-shifted to `"yza"`.
- **Left shift** : Replace every letter with the **preceding**  letter of the English alphabet, where 'a' is replaced by 'z'. For example, `"bcd"` can be left-shifted to `"abc"<font face="Times New Roman"> or ``"yza"` can be left-shifted to `"xyz"`.

We can keep shifting the string in both directions to form an **endless**  **shifting sequence** .

- For example, shift `"abc"` to form the sequence: `... <-> "abc" <-> "bcd" <-> ... <-> "xyz" <-> "yza" <-> ...`.` <-> "zab" <-> "abc" <-> ...`

You are given an array of strings `strings`, group together all `strings[i]` that belong to the same shifting sequence. You may return the answer in **any order** .

**Example 1:** 

<div class="example-block">
Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]

Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]

**Example 2:** 

<div class="example-block">
Input: strings = ["a"]

Output: [["a"]]


#### Complexity Analysis

Let N be the length of strings and K be the maximum length of a string in strings.

Time complexity: O(N∗K)

We iterate over all N strings and for each string, we iterate over all the characters to generate the Hash value, which takes O(K) time. To sum up, the overall time complexity is O(N∗K).

Space complexity: O(N∗K)

We need to store all the strings plus their Hash values in mapHashToList. In the worst scenario, when each string in the given list belongs to a different Hash value, the maximum number of strings stored in mapHashToList is 2∗N. Each string takes at most O(K) space. Hence the overall space complexity is O(N∗K).

```python
class Solution:
    def groupStrings(self, strings: List[str]) -> List[List[str]]:
        def shift_letter(letter, shift):
            return chr((ord(letter) - shift) %26 + ord('a'))

        def hash(string):
            shift = ord(string[0])
            return ''.join(shift_letter(letter, shift) for letter in string)

        groups = collections.defaultdict(list)
        for string in strings:
            hash_key = hash(string)
            groups[hash_key].append(string)
        return list(groups.values())
```


#### Java solution
```java
class Solution {
    char shiftLetter(char letter, int shift) {
        return (char) ((letter - shift + 26) % 26 + 'a');
    }
    
    // Create a hash value
    String getHash(String s) {
        char[] chars = s.toCharArray();
        
        // Calculate the number of shifts to make the first character to be 'a'
        int shift = chars[0];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = shiftLetter(chars[i], shift);
        }
        
        String hashKey = String.valueOf(chars);
        return hashKey;
    }
    
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> mapHashToList = new HashMap<>();
        
        // Create a hash_value (hashKey) for each string and append the string
        // to the list of hash values i.e. mapHashToList["abc"] = ["abc", "bcd"]
        for (String str : strings) {
            String hashKey = getHash(str);
            if (mapHashToList.get(hashKey) == null) {
                mapHashToList.put(hashKey, new ArrayList<>());
            } 
            mapHashToList.get(hashKey).add(str);
        }
        
        // Iterate over the map, and add the values to groups
        List<List<String>> groups = new ArrayList<>();
        for (List<String> group : mapHashToList.values()) {
            groups.add(group);
        }
        
        return groups;
    }
}
```
