import java.util.Scanner;
import static java.lang.System.out;

public class SentenceDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sentence sentence = null;
        boolean running = true;

        while (running) {
            out.println("\n(1) Enter a sentence.");
            out.println("(2) Shuffle the sentence.");
            out.println("(3) Display the original sentence.");
            out.println("(4) Display the sentence table contents.");
            out.println("(5) Exit");
            out.println("Enter your choice: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                out.println("Error: Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    out.println("Enter a sentence.");
                    String inputSentence = scanner.nextLine();
                    try {
                        sentence = new Sentence(inputSentence);
                    } catch (IllegalArgumentException e) {
                        out.println(e.getMessage());
                    }
                    break;
                case 2:
                    if (sentence == null) {
                        out.println("Error: No sentence entered.");
                    } else {
                        sentence.shuffle();
                        out.println("Shuffled the sentence.");
                    }// end if else
                    break;
                case 3:
                    if (sentence == null) {
                        out.println("Error: No sentence entered.");
                    } else {
                        out.println("Display the original sentence: \n" + sentence.getOriginalSentence());
                    }
                    break;
                case 4:
                    if (sentence == null) {
                        out.println("Error: No sentence entered.");
                    } else {
                        out.println("Sentence table contents: \n" + sentence.getShuffledSentence());
                    }
                    break;
                case 5:
                    running = false;
                    out.println("Goodbye!");
                    break;
                default:
                    out.println("Error: Invalid choice. Enter a number between 1 and 5.");
            }// end switch
        }// end while

        scanner.close();

    }// end main
}// end class
