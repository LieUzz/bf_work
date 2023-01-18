public class Main {
    public static void main(String[] args) {
        // Q1
        System.out.println("Question1: ");
        System.out.println(convert(3456789));

        // Q2
        System.out.println("Question2: ");
        System.out.println(findMax(new int[] {3, 5, 2, 5, 5, 5}));

        // Q3
        System.out.println("Question3: ");
        System.out.println(findSingle(new int[] {2, 2, 1}));
        System.out.println(findSingle(new int[] {4, 1, 2, 1, 2}));

        // Q4
        System.out.println("Question4: ");
        PrintNumberInWordIf(5);
        PrintNumberInWordSwitch(6);
        PrintNumberInWordIf(0);

        // Q5
        System.out.println("Question5: ");
        MyWrapper tmp = new MyWrapper(1);
        System.out.println(tmp.doubleValue());
        System.out.println(tmp.equals(new MyWrapper(0)));
        System.out.println(tmp.equals(new MyWrapper(1)));


    }

    /**
     * convert minutes into the “years and days” format.
     * @param n number of minutes
     * @return String
     */
    public static String convert(int n) {
        if (n < 0)
            return "input invalid";

        int m = n / 24 / 60;
        int year = 0, day = 0;

        year = m / 365;
        day = m - year * 365;
        return n + " minutes is approximately " + year + " years and " + day + " days";
    }

    /**
     * reads an array of integers, finds the largest of them, and counts its occurrences.
     * @param nums
     * @return String
     */
    public static String findMax(int[] nums) {
        if (nums == null || nums.length == 0)
            return "input invalid";
        int ct = 1;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max == nums[i])
                ct += 1;
            else if (nums[i] > max){
                ct = 1;
                max = nums[i];
            }
        }
        return "The largest number is " + max + ", The occurrence count of the largest number is " + ct;
    }

    /**
     * Given a non-empty array of integers nums,
     * every element appears twice except for one. Find that single one
     * @param nums
     * @return
     */
    public static int findSingle(int[] nums) {
        int res = 0;
        for (int a : nums)
            res ^= a;
        return res;
    }

    /**
     * prints "ONE", "TWO",... , "NINE", "OTHER"
     * if the int variable "number" is 1, 2,... , 9, or other, respectively.
     * Using nestedIf
     * @param n
     */
    public static void PrintNumberInWordIf(int n) {
        String str;
        if (n == 1) str = "ONE";
        else if (n == 2) str = "TWO";
        else if (n == 3) str = "THREE";
        else if (n == 4) str = "FOUR";
        else if (n == 5) str = "FIVE";
        else if (n == 6) str = "SIX";
        else if (n == 7) str = "SEVEN";
        else if (n == 8) str = "EIGHT";
        else if (n == 9) str = "NINE";
        else str= "OTHER";
        System.out.println(str);
    }

    /**
     * prints "ONE", "TWO",... , "NINE", "OTHER"
     * if the int variable "number" is 1, 2,... , 9, or other, respectively.
     * Using switch-case
     * @param n
     */
    public static void PrintNumberInWordSwitch(int n) {
        String str;
        switch (n) {
            case 1: {
                str = "ONE";
                break;}
            case 2: {
                str = "TWO";
                break;}
            case 3: {
                str = "THREE";
                break;}
            case 4: {
                str = "FOUR";
                break;}
            case 5: {
                str = "FIVE";
                break;}
            case 6: {
                str = "SIX";
                break;}
            case 7: {
                str = "SEVEN";
                break;}
            case 8: {
                str = "EIGHT";
                break;}
            case 9: {
                str = "NINE";
                break;}
            default:
                str = "OTHER";
        }
        System.out.println(str);
    }


}

