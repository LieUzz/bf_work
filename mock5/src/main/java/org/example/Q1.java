package org.example;

import java.util.ArrayList;
import java.util.List;

public class Q1 {
    public static void main(String[] args) {
        int[] arr = new int[] {2,9,4,3,5,6,2,5,9,4};
        System.out.println(minIndexDiff(arr, 2, 4));
        int[] arr2 = new int[] {2,9,4,3,6,4,4,2,5};
        System.out.println(minIndexDiff(arr2, 4, 4));
    }

    public static int minIndexDiff(int[] arr, int num1, int num2){
        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (num1 == arr[i]) {
                a1.add(i);
            }
            if (num2 == arr[i]) {
                a2.add(i);
            }
        }
        int res = arr.length;
        for (int i = 0; i < a1.size(); i++) {
            for (int j = 0; j < a2.size(); j++) {
                if (a1.get(i) != a2.get(j)) {
                    res = Math.min(Math.abs(a1.get(i) - a2.get(j)), res);
                }
            }
        }
        return res;
    }
}
