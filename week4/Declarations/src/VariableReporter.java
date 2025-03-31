
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VariableReporter {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java VariableReporter <input-file> <output-file>");
            return;
        }// end if

        List<String> lines = new ArrayList<>();
        String inputFile = args[0];
        String outputFile = args[1];

        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine().trim());
            }// end while
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + inputFile);
            return;
        }// end try catch

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("Parsing variables from file: " + inputFile + "\n\n");

            writeParsedVariables(lines, writer);
        } catch (IOException e) {
            System.out.println("Error: Unable to write to file - " + outputFile);
        }// end try catch
    }// end main

    private static void writeParsedVariables(List<String> lines, BufferedWriter writer) throws IOException {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            writer.write("Line " + (i + 1) + ": " + line + "\n");

            writer.write("Parsed output for line " + (i + 1) + "\n");
            writer.write("\n");
        }// end for
    }// end method
}// end class