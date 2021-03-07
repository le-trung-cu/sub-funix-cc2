import java.util.Scanner;

public class App {
    // private int[] nums = new int[]{31, 64, 49};
    private final int MAX_RANDOM = 100;
    // lucky number
    private int luckyNumber;
    // count user guess
    private int x;
    private int totalGame;
    // tổng số lần dự đoán
    private int totalOfGuess;
    // là game có số lần dự đoán ít nhất
    private int bestGame;

    private final String QUESTION = "\nI'm thinking of a number between 1 and 100...";
    private final String MESSAGE_LOWER = "It's lower.";
    private final String MESSAGE_HIGHER = "It's higher.";
    private final String MESSAGE_FORMAT_SUCCESS = "You get it right in %d quesses!";
    private final String ASK_PLAY_AGAIN = "Do you want to play again? ";

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.play();
        
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            totalGame++;
            // create luckyNumber, between 0 and MAX_RANDOM
            luckyNumber = (int) Math.floor(Math.random() * (MAX_RANDOM + 1));
            // luckyNumber = nums[totalGame-1];
            int numberOfGuess = 0;
            int countSugesses = 0;

            System.out.println(QUESTION);

            while (true) {
                totalOfGuess++;
                countSugesses++;
                System.out.print("Your guess? ");
                numberOfGuess = scanner.nextInt();

                if (numberOfGuess > luckyNumber) {
                    System.out.println(MESSAGE_LOWER);
                } else if (numberOfGuess < luckyNumber) {
                    System.out.println(MESSAGE_HIGHER);
                } else {
                    // sugess right.
                    System.out.println(String.format(this.MESSAGE_FORMAT_SUCCESS, countSugesses));

                    // update bestGame.
                    if (this.bestGame > countSugesses || this.bestGame == 0) {
                        this.bestGame = countSugesses;
                    }
                    break;
                }

            }
            // ask for play again game.
            scanner.nextLine();
            System.out.print(ASK_PLAY_AGAIN);
            String answerContinue = scanner.nextLine();
            if (!answerContinue.equalsIgnoreCase("Y") && !answerContinue.equalsIgnoreCase("YES")) {
                break;
            }
        }

        report();

        scanner.close();
    }

    private void report() {
        double avg = Math.round(10.0*this.totalOfGuess/this.totalGame)/10.0;
        System.out.println("Overall results:");
        System.out.println("Total games    = " + this.totalGame);
        System.out.println("Total guesses  = " + this.totalOfGuess);
        System.out.println("Guesses/game   = " + avg);
        System.out.println("Best game      = " + this.bestGame);
    }
}
