package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q1 {

    public static void main(String[] args) {
        Set set1 = new HashSet();
        set1.add("apple");
        set1.add("pen");
        System.out.println(workBreak("applepenapple", set1));
        Set set2 = new HashSet();
        set2.add("cats");
        set2.add("dog");
        set2.add("sand");
        set2.add("and");
        set2.add("cat");
        System.out.println(workBreak("ccatsandog", set2));
    }

    private static boolean workBreak(String s, Set<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                String sub = s.substring(j, i+1);
                if (wordDict.contains(sub) && dp[j]){
                    dp[i+1] = true;
                }
            }
        }
//        for (int i = 0; i < n; i ++)
//            System.out.println(dp[i]);
        return dp[n];
//        for (int i = 0; i < n; i++) {
//            for (String str : wordDict) {
//                int m = str.length();
//
//                if (i < n-m) {
//
//                    String sub = s.substring(i, i+m);
//                    System.out.println(str + " " + sub);
//                    if (sub.equals(str) && dp[i]) {
//                        dp[i + m] = true;
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < n; i ++)
//            System.out.println(dp[i]);
//        return dp[n-1];
    }

}
