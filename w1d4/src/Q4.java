import java.util.*;

/**
 * Given a pattern and a string str,
 * <p>find if str follows the same pattern. Here follow means a full match,
 * <p>such that there is a bijection between a letter in pattern and a non- empty word in str.
 */

public class Q4 {
    public static void main(String[] args) {
        // "abba" -> "dog cat cat dog"

        System.out.println(wordPattern("abba", "dog cat cat dog"));
        System.out.println(wordPattern("abba", "dog cat cat fish"));

    }

    public static boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");

        Map<Character, String> map = new HashMap<>();
        Map<String, Character> mapBack = new HashMap<>();
        if (pattern.length() != strs.length)
            return false;

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(strs[i]))
                    return false;
            } else {
                if (mapBack.containsKey(strs[i]))
                    return false;
                else {
                    map.put(ch, strs[i]);
                    mapBack.put(strs[i], ch);
                }
            }
        }
        return true;

    }
}
