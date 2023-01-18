package org.example;

import java.util.*;

public class Q3 {
    public static void main(String[] args) {
        int[] s = new int[] {1,1,1,2,2,3};
        System.out.println(Arrays.toString(topKFrequent(s, 1)));
    }

    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap();
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        for (int n: map.keySet()) {
            pq.add(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] res = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            res[i] = pq.poll();
        }
        return res;
    }

}
