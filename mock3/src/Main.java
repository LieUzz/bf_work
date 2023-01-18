import java.util.*;

public class Main {
    public static void main(String[] args) {

//        int[] heights = new int[] {78,90,65,51,87,99,100,31,150};
//        int[] res = hillClimbing(heights);
//
//        for (int i = 0; i < heights.length; i++) {
//            System.out.println(res[i]);
//        }

        int res2 = findMinimumChanges("abcdeeab", "aabbeeff");
        System.out.println(res2);
    }

    public static int[] hillClimbing(int[] heights) {

        int n = heights.length;

        int[] res = new int[n];
        Stack<Integer> list = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!list.isEmpty() && heights[list.peek()] < heights[i]) {
                int idx = list.pop();

                res[idx] = i - idx;
            }

            list.push(i);
        }
        while (!list.isEmpty()) {
            res[list.pop()] = 0;
        }
        return res;
    }

    public static int findMinimumChanges(String str1, String str2) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch:str1.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int res = 0;

        for (char ch:str2.toCharArray()) {
            if (!map.containsKey(ch) || map.get(ch) == 0) {
                res ++;
            }
            else {
                map.put(ch, map.getOrDefault(ch, 0)-1);
            }
        }
        return res;

    }
}