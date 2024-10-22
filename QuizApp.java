Here's a simple Java program that implements a quiz application with a timer. 
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizApp {
    static String[] questions = {
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "What is the largest mammal in the world?"
    };
    
    static String[][] options = {
        {"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
        {"1. Earth", "2. Mars", "3. Jupiter", "4. Venus"},
        {"1. Elephant", "2. Blue Whale", "3. Great White Shark", "4. Giraffe"}
    };
    
    static int[] answers = {3, 2, 2}; // correct options (Paris, Mars, Blue Whale)
    static boolean[] userAnswers = new boolean[questions.length];
    static int score = 0;
    static int timeLimit = 10; // 10 seconds per question
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }
            
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up!");
                    scanner.nextLine(); // Clear the scanner buffer
                }
            };
            timer.schedule(task, timeLimit * 1000);
            
            System.out.print("Your answer: ");
            int userChoice = scanner.nextInt();
            
            timer.cancel();
            
            if (userChoice == answers[i]) {
                score++;
                userAnswers[i] = true;
            } else {
                userAnswers[i] = false;
            }
            System.out.println();
        }
        
        scanner.close();
        
        System.out.println("Quiz Over!");
        System.out.println("Your final score: " + score + "/" + questions.length);
        System.out.println("Summary:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + (userAnswers[i] ? "Correct" : "Incorrect"));
        }
    }
}
