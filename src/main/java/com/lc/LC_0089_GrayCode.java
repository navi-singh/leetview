package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0089_GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> lis = new ArrayList<>();
        lis.add(0);
        int increment = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < lis.size(); j++) {
                lis.add(lis.get(j) + increment);
            }
            increment <<= 1;
        }
        return lis;
    }
}