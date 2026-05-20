import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lowest = 1, highest = 100;
        System.out.println("Guess a number between " + lowest + " and " + highest);
        Random random = new Random();
        int number  = random.nextInt(highest - lowest + 1) + lowest;
        int guess, attempts = 0;
        do {
            guess = sc.nextInt();
            if(guess < number) {
                System.out.println("Higher");
            }
            else if(guess > number) {
                System.out.println("Lower");
            }
            attempts++;
        }while (guess != number);
        System.out.println("Correct");
        System.out.println("Attempts: " + attempts);
    }
}
