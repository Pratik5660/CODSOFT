import java.util.Random;
import java.util.Scanner;
public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int min = 1;
        int max = 100;
        int maxAttempts = 10;
        int totalRounds = 0;
        int totalScore = 0;

        boolean playAgain = true;

        while (playAgain) {
            totalRounds++;
            int attempts = 0;
            int number = random.nextInt(max - min + 1) + min;
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + totalRounds);
            System.out.println("I have generated a number between " + min + " and " + max + ". Try to guess it!");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess < number) {
                    System.out.println("Too low!");
                } else if (guess > number) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct! You've guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    totalScore++;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + number);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Game over! You played " + totalRounds + " rounds and your score is " + totalScore + ".");
        scanner.close();
    }
}
