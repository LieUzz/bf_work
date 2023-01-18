package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Q2 {

    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,2);
        map.put(3,4);

        System.out.println(largestTree((map)));
    }

    public static Integer largestTree(Map<Integer, Integer> immediateParent){
        int n = immediateParent.size();

        Map<Integer, Integer> dp = new HashMap<>();
        for (int key : immediateParent.keySet()) {
            dp.putIfAbsent(key, key);
            dp.putIfAbsent(immediateParent.get(key), immediateParent.get(key));
        };

        for (int key : immediateParent.keySet()) {
            int val = immediateParent.get(key);
            key = find(dp, key);
            val = find(dp, val);
            if (key != val) {
                dp.put(key, val);
            }
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int key:dp.keySet()) {
            key = find(dp, key);

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        Integer max = null;
        for (int key : map.keySet()) {
            if (max == null || map.get(key) > map.get(max)) {
                max = key;
            }
            else if(Objects.equals(map.get(key), map.get(max)) && key < max) {

                max = key;
            }
        }

        return max;

    }

    public static int find(Map<Integer, Integer> dp, Integer a) {
        while (!a.equals(dp.get(a))) {
            dp.put(dp.get(a), dp.get(dp.get(a)));
            a = dp.get(a);
        }
        return a;
    }
}
