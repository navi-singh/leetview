package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0077_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (n < 1 || n < k) {
            return res;
        }
        List<Integer> temp = new ArrayList<Integer>();
        helper(n, k, 0, temp, res);
        return res;
    }

    private void helper(int n, int k, int index, List<Integer> temp, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < n; i++) {
            temp.add(i + 1);
            helper(n, k - 1, i + 1, temp, res);
            temp.remove(temp.size() - 1);
        }
    }
}