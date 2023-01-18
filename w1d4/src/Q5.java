import java.util.*;
/**
 * Given an array of integers,
 * <p>find out whether there are two distinct indices i and j in the array
 * <p>such that the absolute difference between nums[i] and nums[j] is at most t
 * <p>and the absolute difference between i and j is at most k.
 */
public class Q5 {
    public static void main(String[] args) {

        System.out.println(solution(new int[]{1,2,3,1}, 3, 0));
        System.out.println(solution(new int[]{1,0,1,1}, 1, 2));
        System.out.println(solution(new int[]{1,5,9,1,5,9}, 2, 3));

    }

    /**
     *
     * @param list i, j is the idx from list.
     * @param k absolute difference between i and j is at most k.
     * @param t absolute difference between nums[i] and nums[j] is at most t
     * @return true: there are two distinct indices i and j, false: there are not.
     */
    public static boolean solution(int[] list, int k, int t) {
        if (t < 0) return false;

        int n = list.length;

        Map<Integer, Integer> map = new HashMap<>();

        int range = t+1;

        for (int i = 0; i < n; i++) {
            int num = list[i];
            int m = num / range;

            if (map.containsKey(m))
                return true;
            else if (map.containsKey(m - 1) && Math.abs(num - map.get(m - 1)) < range)
                return true;
            else if (map.containsKey(m + 1) && Math.abs(num - map.get(m + 1)) < range)
                return true;
            map.put(m, num);
            if (i >= k) map.remove(list[i-k] / range);
        }
        return false;
    }


}
