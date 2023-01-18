package org.example;

import java.util.Arrays;

public class Q1 {
    public static void main(String[] args) {

        int[] nums1 = new int[] {1, 3};
        int[] nums2 = new int[] {2, 4};
        System.out.println(medianOfTwoSortedArray(nums1, nums2));
    }

    static double medianOfTwoSortedArray(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;

        int[] array = new int[n+m];
        int j = 0;
        for (int i = 0; i < n; i++) {
            array[j] = nums1[i];
            j++;
        }
        for (int i = 0; i < m; i++) {
            array[j] = nums2[i];
            j++;
        }

        Arrays.sort(array);
        for (int i:array) {
            System.out.println(i);
        }
        double res;
        if ((n + m ) % 2 == 1) {
            res = array[(n+m)/2];
        }
        else {
            res = (double)(array[(n+m)/2] + array[(n+m)/2-1]) / 2;
        }
        return res;
    }
}
