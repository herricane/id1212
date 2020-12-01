package extra;

import java.util.Random;

public class GuessGame {
    private final int UPPER_BOUND = 100;
    private int answer;
    private String htmlPage;
    private int count = 0;
    private String uid;

    public GuessGame(String uid) {
        this.uid = uid;
        Random rand = new Random();
        this.answer = rand.nextInt(UPPER_BOUND) + 1;
    }

    public String initPage() {
        htmlPage = "<html><title>Guessing Game</title><body>" +
                "<p>Welcome to the guessing game!</p>" +
                "<p>I'm thinking a number between 1 and 100.</p>" +
                "<p>What's your guess?</p>" +
                "<form method='get'>" +
                "<input type='text' name='guess'><input type='submit' value='Guess!'>" +
                "</form></body></html>";
        return htmlPage;
    }

    public String failPage(boolean higher) {
        htmlPage = "<html><title>Guessing Game</title><body>" +
                "<p>Nope, guess " + (higher ? "higher" : "lower") + ".</p>" +
                "<p>You have made " + count + " guess(es).</p>" +
                "<p>What's your guess?</p>" +
                "<form method='get'>" +
                "<input type='text' name='guess'><input type='submit' value='Guess!'>" +
                "</form>" +
                "<p>Hint: " + answer + "</p>" +
                "</body></html>";
        return htmlPage;
    }

    public String successPage() {
        htmlPage = "<html><title>Guessing Game</title><body>" +
                "<p>You made it in " + count + " guess(es)!</p>" +
                "<button onclick='location.href=\"http://localhost:8090\"'>Play again!</button>" +
                "</body></html>";
        return htmlPage;
    }

    public String guessPage(int guess) {
        count = count + 1;
        if (guess > answer) {
            return failPage(false);
        } else if (guess < answer) {
            return failPage(true);
        } else {
            return successPage();
        }
    }

    public String errorPage() {
        htmlPage = "<html><title>Guessing Game</title><body>" +
                "<p>Please enter an integer between 1 and 100!</p>" +
                "<button onclick='location.href=\"http://localhost:8090\"'>Play again!</button>" +
                "</body></html>";
        return htmlPage;
    }

    public String getId() {
        return this.uid;
    }
}
