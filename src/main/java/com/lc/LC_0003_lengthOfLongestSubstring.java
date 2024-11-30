package com.lc;

import java.util.HashMap;
import java.util.Map;


/*
3. Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
Time complexity : O(n)O(n). Index jj will iterate nn times.
Space complexity (HashMap) : O(min(m, n))O(min(m,n)). Same as the previous approach.
*/
public class LC_0003_lengthOfLongestSubstring{
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int start = 0, j = 0; j < len; j++) {
            if(map.containsKey(s.charAt(j))) {
                start = Math.max(map.get(s.charAt(j)), start);
            }
            res = Math.max(res, j-start + 1);
            map.put(s.charAt(j), j + 1);
        }
        return res;
    }
}