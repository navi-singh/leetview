package com.lc;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    LC_0215_KthLargestElementInArray lc = new LC_0215_KthLargestElementInArray();
    int[] nums = {3, 2, 1, 5, 6, 4};

    System.out.println(lc.findKthLargest(nums, 2));
  }
}
