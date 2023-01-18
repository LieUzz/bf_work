import java.util.Scanner;
/**
 * (4) Design and build GuessingGameTester class
 * <p>a. This program will create GuessingGame objects and completely test the GuessingGame Class.
 * <p>b. The tester will also provide two loops.
 * <p>c. The first loop will allow the user to play a new game after the previous game is completed.
 * <p>d. The second or nested loop will prompt the user for a new guess and provide a response based on the guess.
 */
public class GuessingGameTester {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Welcome to Guessing Game");
            System.out.println("Enter the Maximum number");
            GuessingGame game = new GuessingGame(scanner.nextInt());
            System.out.println("Enter the number of guessing allowed:");
            game.newGame(scanner.nextInt());

            while (!game.isGameOver()) {

                System.out.println("Enter your guess, remember it must be between 0 and " + game.getMax());
                game.guess(scanner.nextInt());
            }

            System.out.println("Would you like to play game again, enter Y for yes, N for No.");
            if (scanner.next().equals("N")) {
                break;
            }

        }


    }
}
