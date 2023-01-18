package org.example;

public class Q2 {
    public static void main(String[] args) {
        String s = "abc";
        int k = 1;
        System.out.println(shiftByK(s, 1));
    }

    private static String shiftByK(String s, Integer k) {
        char[] chars = s.toCharArray();

        int n = s.length();
        char[] res = new char[n];
        k = k % n;
        if (k < 0) {
           k += n;
        }
        int j = 0;
        for (int i = k; i < n; i++) {
            res[i] = chars[j++];
        }
        for (int i = 0; i < k; i++) {
            res[i] = chars[j++];
        }
        return String.copyValueOf(res);
    }
}
