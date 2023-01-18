package Q2;

/**
 * Create a user defined exception class called Q2.NonIntResultException which is generated
 * when the result of dividing two integer values produces a result with a fractional component
 * [i.e the result is not an integer]
 */
public class Q2test {
    public static void main(String[] args) {

        divide(5, 2);
        divide(4, 2);

    }

    public static void divide(int x, int y) {

        try {
            if (x % y != 0) {
                throw new NonIntResultException(x, y);
            } else {
                System.out.println("The result of dividing " + x + " by " + y + " is " + x / y);
            }
        } catch (NonIntResultException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
