import java.util.Random;
import java.util.Scanner;

public class numberGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int score = 0;

        while (playAgain) {
            int generatedNumber = random.nextInt(100) + 1; // Generates a random number between 1 and 100
            int attempts = 0;
            boolean guessed = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("Guess a number between 1 and 100.");

            while (!guessed) {
                System.out.print("Enter your guess: ");
                int userGuess = input.nextInt();
                attempts++;

                if (userGuess == generatedNumber) {
                    System.out.println("Congratulations! You guessed it right in " + attempts + " attempts.");
                    guessed = true;
                    score++;
                } else if (userGuess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                if (attempts >= 10) {
                    System.out.println("Sorry, you've reached the maximum number of attempts.");
                    System.out.println("The correct number was: " + generatedNumber);
                    break;
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = input.next().toLowerCase();

            if (!playChoice.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thank you for playing! Your score is: " + score);
        input.close();
    }
}
