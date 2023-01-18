package Q3;
import Q2.NonIntResultException;
/**
 * Create the IntegerArrayMath class with integer division method:
 * <p>a. Loops thru instance field array and attempts to divide each value of number array by the corresponding value of denom instance field array. such as number[0]/denom[0] and number[1]/denom[1],etc
 * <p>b. If the result of the division is an integer, then print out a message indicating the result of the division such as 8/4 is 2.
 * <p>c. If the result of the division is not an integer, then throw and handle a NonIntResultException and continue processing the result of the number array elements.
 * <p>d. The method should, using exception handling also handle any attempt to divide by zero (arithmetic exception) the program should display an appropriate message and then continue processing the rest of the number array elements
 * <p>e. Assume both arrays are the same length.
 */
public class IntegerArrayMath {
    public static void intDivision(int[] numbers, int[] denom) {

        for (int i = 0; i < numbers.length; i++) {
            int x = numbers[i];
            int y = denom[i];
            try {
                if (y == 0) {
                    throw new ArithmeticException("The divisor cannot be zero");
                }
                else if (x % y != 0) {
                    throw new NonIntResultException(x, y);
                }
                else {
                    System.out.println("The result of dividing auch as " + x + "/" + y + " is " + x / y);
                }
            } catch (NonIntResultException | ArithmeticException e) {
                e.printStackTrace();
            }
        }

    }
}
