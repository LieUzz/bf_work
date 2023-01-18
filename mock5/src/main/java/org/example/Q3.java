package org.example;

public class Q3 {
    public static void main(String[] args) {

//        int[] nums = new int[] {1,-2,3,-2};
//        System.out.println(findMaxCircularSum(nums));

        int[] nums2 = new int[] {5, -3, 5};
        System.out.println(findMaxCircularSum(nums2));

//        int[] nums3 = new int[] {-3, -2, -3};
//        System.out.println(findMaxCircularSum(nums3));
    }

    public static int findMaxCircularSum(int[] nums) {
        int n = nums.length;

        int res = nums[0];

        int prev = nums[0];

        for ( int i = 1; i < n ; i ++) {
            if (prev > 0) {
                prev = prev + nums[i];
            } else {
                prev = nums[i];
            }
            res = Math.max(res, prev);
        }

        return res;

    }
}
