package Q2;

public class NonIntResultException extends Exception {

    public NonIntResultException(int x, int y) {
        super("The result of dividing " + x + " by " + y + " is not an integer");
    }
}