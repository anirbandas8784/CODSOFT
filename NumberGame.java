package CodsoftInternship;
import java.util.*;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        final int MIN = 1, MAX = 100;
        final int MAX_ATTEMPTS = 7;
        int rounds = 0, totalScore = 0;
        boolean playAgain;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            int numberToGuess = rand.nextInt(MAX - MIN + 1) + MIN;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nI'm thinking of a number between " + MIN + " and " + MAX + ".");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it!");

            
            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess (" + (attempts + 1) + "): ");
                int userGuess;

                
                try {
                    userGuess = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid integer.");
                    sc.next();
                    continue;
                }

                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed it in " + attempts + " attempts.");
                    
                    int score = Math.max(1, (MAX_ATTEMPTS - attempts + 1));
                    totalScore += score;
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry! You've used all " + MAX_ATTEMPTS + " attempts.");
                System.out.println("The number was: " + numberToGuess);
            }

            rounds++;
            System.out.println("Current Score: " + totalScore);

            
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = sc.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("\nThanks for playing!");
        System.out.println("Rounds played: " + rounds);
        System.out.println("Final Score: " + totalScore);
        sc.close();
    }
}

    

