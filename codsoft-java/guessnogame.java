import java.util.Scanner;
import java.util.Random;

public class guessnogame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int minNum = 1;
        int maxNum = 100;
        int maxAttempts = 5;
        int rounds = 0;
        int totalAttempts = 0;
        
        boolean playAgain = true;
        
        while (playAgain) {
            rounds++;
            System.out.println("\nRound " + rounds + ":");
            int targetNumber = random.nextInt(maxNum - minNum + 1) + minNum;
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Guess the number between " + minNum + " and " + maxNum + ": ");
                int guess = scanner.nextInt();
                attempts++;
                
                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number " + targetNumber + " correctly in " + attempts + " attempts.");
                    guessedCorrectly = true;
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
            }
            
            totalAttempts += attempts;
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            if (!playAgainInput.equals("yes")) {
                playAgain = false;
            }
        }
        
        scanner.close();
        System.out.println("\nGame Over! You played " + rounds + " rounds with an average of " + (double)totalAttempts / rounds + " attempts per round.");
    }
}
