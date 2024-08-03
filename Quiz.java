import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
public class Quiz {
    private ArrayList<QuizQuestion> questions;
    private int score;
    private int currentQuestionIndex;
    private boolean answered;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        currentQuestionIndex = 0;
        answered = false;
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new QuizQuestion("What is the capital of France?",
                new String[]{"1. Paris", "2. London", "3. Berlin", "4. Madrid"}, 0));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 1));
        questions.add(new QuizQuestion("What is the largest ocean on Earth?",
                new String[]{"1. Atlantic", "2. Indian", "3. Arctic", "4. Pacific"}, 3));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("\n" + currentQuestion.getQuestion());

            String[] options = currentQuestion.getOptions();
            for (String option : options) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    if (!answered) {
                        System.out.println("\nTime's up! Moving to the next question.");
                        currentQuestionIndex++;
                        start(); // restart the quiz for the next question
                    }
                }
            };

            timer.schedule(task, 10000); // 10 seconds timer

            System.out.print("Enter your answer (1-4): ");
            int answer = scanner.nextInt() - 1;
            answered = true;

            if (currentQuestion.isCorrectAnswer(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was: " + options[currentQuestion.getCorrectAnswerIndex()]);
            }

            timer.cancel();
            currentQuestionIndex++;
            answered = false;
        }

        System.out.println("\nQuiz Over! Your score is: " + score + "/" + questions.size());
        scanner.close();
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.start();
    }

}
