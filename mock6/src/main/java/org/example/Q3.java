package org.example;

import java.util.PriorityQueue;
import java.util.Queue;

public class Q3 {

    public static void main(String[] args) {
        int[] nums = new int[] {2,4, 3, 5, 5, 1};
        System.out.println(findKthLargest(nums, 3));
    }

    static public int findKthLargest(int[] nums, int k) {


        Queue<Integer> queue = new PriorityQueue<>((a, b) -> (b-a));

        for ( int i : nums) {
            queue.add(i);
        }

        int res = queue.peek();
        for (int i = 0 ; i < k ; i++) {
            res = queue.poll();
        }

        return res;
    }
}
