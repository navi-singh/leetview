package com.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC_0018_FourSum{
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<List<Integer>>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int k = j + 1, l = nums.length - 1;
                while (k < l) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target) {
                        result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l])));
                        k++;
                        l--;
                    } else if (sum > target)
                        l--;
                    else
                        k++;
                }
            }
        }
        return new ArrayList<List<Integer>>(result);
    }
}