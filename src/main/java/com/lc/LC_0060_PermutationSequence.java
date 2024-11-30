package com.lc;

import java.util.ArrayList;
/**
The set [1, 2, 3, ..., n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Example 1:
Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
Example 3:

Input: n = 3, k = 1
Output: "123"
*/
public class LC_0060_PermutationSequence {
    // Need to find k/n! where n! will be total permutations
    public String getPermutation(int n, int k) {
        ArrayList<Integer> lis = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            lis.add(i);
            System.out.print(i + ",");
        }
        System.out.println();
        k--;
        long mod = 1;
        for (int i = 1; i <= n; ++i) {
            mod = mod * i;
            System.out.print(mod + ",");
        }
        System.out.println();
        String result = "";
        for (int i = 0; i < n; i++) {
            mod = mod / (n - i);
            int currIndex =(int) Math.round( k / mod);
            k %= mod;
            result += lis.get(currIndex);
            System.out.println(currIndex + ":" + k + "<>" + mod + "::" + result);
            lis.remove(currIndex);
        }
        return result.toString();
    }
}
