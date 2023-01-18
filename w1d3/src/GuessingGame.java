import java.util.Random;

/**
 * Develop a simple class that represents a number guessing game.
 * <p>The program will allow the user to play multiple games.
 * <p>Design and build a GuessingGame class.
 * <p>Design and build GuessingGameTester class
 */
public class GuessingGame {
    /**
     * i. answer - an integer representing the randomly generated number.
     * <p>ii. generator – a random Generator object
     * <p>iii. gameOver – a Boolean, false if game still in progress, true if the game is over.
     * <p>iv. differential – an integer representing the difference between a guess and the answer.
     * <p>v. max – maximum value of the number to guess. For example, if the maximum number is
     *          100 then the number to guess would be between 0 and 100.(inclusive)
     * <p>vi. maxGuessesAllowed – the maximum number of guesses the user gets, once this value is passed the game is over.
     * <p>vii. numGuessesTaken – an integer that stores the number of guessed taken so far in any game.
     */
    private Integer answer;
    private Random generator;
    private Boolean gameOver;
    private Integer differential;
    private Integer max;
    private Integer maxGuessesAllowed;
    private Integer numGuessesTaken;

    /**
     * i. Default Constructor
     * <p> Sets max to zero
     * <p> Generates the random number generator object.
     */
    GuessingGame() {
        max = 0;
        generator = new Random();
    }

    /**
     * ii. Parameterized Constructor
     * <p>Takes an integer parameter representing the maximum value of the number to guess.
     * <p>Creates the random number generator object.
     * @param max
     */
    GuessingGame(int max) {
        this.max = max;
        generator = new Random();
        answer = generator.nextInt(max+1);
    }

    /**
     * iii. newGame method
     * <p>Takes in an integer as a parameter representing the maximum number of guesses and sets maxGuessesAllowed .
     * In other words the parameter represents how many guesses the user gets before the game is over.
     * Generates the answer using the random number generator.(0 - max).
     * <p>Sets gameOver to false.
     * <p>Sets differential to the max value.
     * <p>Sets numGuessTaken to zero.
     * @param maxGuess
     */
    public void newGame(int maxGuess) {
        maxGuessesAllowed = maxGuess;
        gameOver = false;
        differential = max;
        numGuessesTaken = 0;
    }

    /**
     * iv. guess method
     * <p>Takes an integer as a parameter representing a new guess.
     * Compares the new guess with the answer and generates and returns a String
     * representing an appropriate response. The response is based on:
     * <p>a. The relation of the guess and answer (too high, too low or correct).
     * <p>b. The comparison of difference between the current guess and the answer and the previous guess and the answer. (warmer, colder)
     * <p>c. Guess out of range error, if the guess is not between 0 and the max number (inclusive) (see sample run below)
     * <p>d. User has taken too many guess because numGuessesTaken is greater than maxGuessesAllowed. If this is the case set isGameOver to true.
     */
    public void guess(int value) {

        if (value < 0 || value > max) {
            System.out.println("Guess out of range, The guess must be between 0 and " + max);
            return;
        }
        int diff = value - answer;
        if (diff == 0) {
            System.out.println("Congratulation");
            gameOver = true;
        }
        else {
            if (diff > 0) {
                System.out.println("Too High");
            } else
                System.out.println("Too Low");

            if (Math.abs(diff) <= differential)
                System.out.println("Getting Warmer");
            else
                System.out.println("Getting Colder");
            System.out.println();
            differential = Math.abs(diff);
        }
        numGuessesTaken++;
        if (numGuessesTaken == maxGuessesAllowed)
            gameOver = true;
    }

    /**
     * v. isGameOver method - returns the state of game.
     * <p> true if game is over
     * <p> false if still in progress.
     * @return
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Accessor(getter) and mutator(setter) methods for all instance fields except the Random number generator.
     * <p>Use the Accessor or mutator methods within the other methods of the class rather than directly accessing the instance fields.
     * <p>For example, use mutator methods in the parameterized constructor to modify instance variables.
     *
     */
    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public void setGenerator(Random generator) {
        this.generator = generator;
    }

    public void setDifferential(Integer differential) {
        this.differential = differential;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public void setMaxGuessesAllowed(Integer maxGuessesAllowed) {
        this.maxGuessesAllowed = maxGuessesAllowed;
    }

    public void setNumGuessesTaken(Integer numGuessesTaken) {
        this.numGuessesTaken = numGuessesTaken;
    }

    public Integer getAnswer() {
        return answer;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public Integer getDifferential() {
        return differential;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getMaxGuessesAllowed() {
        return maxGuessesAllowed;
    }

    public Integer getNumGuessesTaken() {
        return numGuessesTaken;
    }
}

