import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizSystem {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private boolean timerExpired;

    public QuizSystem() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        score = 0;
        timer = new Timer();
        timerExpired = false;
    }

    public void addQuestion(String question, List<String> options, int correctAnswerIndex) {
        questions.add(new QuizQuestion(question, options, correctAnswerIndex));
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        askQuestion();
    }

    private void askQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((char) ('A' + i) + ". " + options.get(i));
            }

            // Start timer
            timerExpired = false;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    timerExpired = true;
                    askQuestion();
                }
            }, 10000); // Timer set for 10 seconds

            // User input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your answer (A/B/C/D): ");
            String userAnswer = scanner.nextLine().toUpperCase();
            if (!timerExpired) {
                checkAnswer(currentQuestion, userAnswer);
            }
        } else {
            endQuiz();
        }
    }

    private void checkAnswer(QuizQuestion question, String userAnswer) {
        int correctAnswerIndex = question.getCorrectAnswerIndex();
        if (userAnswer.length() == 1 && userAnswer.charAt(0) >= 'A' && userAnswer.charAt(0) <= 'Z') {
            int userAnswerIndex = userAnswer.charAt(0) - 'A';
            if (userAnswerIndex == correctAnswerIndex) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + (char) ('A' + correctAnswerIndex));
            }
        } else {
            System.out.println("Invalid input. Please enter A/B/C/D.");
        }
        currentQuestionIndex++;
        askQuestion();
    }

    private void endQuiz() {
        System.out.println("Quiz ended.");
        System.out.println("Your final score: " + score + "/" + questions.size());
    }

    public static void main(String[] args) {
        QuizSystem quiz = new QuizSystem();

        // Add questions
        quiz.addQuestion("What is the capital of France?", List.of("Paris", "London", "Rome", "Berlin"), 0);
        quiz.addQuestion("What is 2 + 2?", List.of("3", "4", "5", "6"), 1);
        quiz.addQuestion("Who painted the Mona Lisa?", List.of("Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo"), 0);

        // Start the quiz
        quiz.startQuiz();
    }
}

