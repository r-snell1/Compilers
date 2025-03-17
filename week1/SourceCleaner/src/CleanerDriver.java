import java.io.*;
import static java.lang.System.out;

public class CleanerDriver {
    public static void main(String[] args) throws IOException {
        JavaSourceCleaner cleanerJava = new JavaSourceCleaner();
        PascalSourceCleaner cleanerPascal = new PascalSourceCleaner();

        if (args.length != 2) {
            out.println("Usage: java CleanerDriver <input-file> <output-file>");
            return;
        } // end if

        // Open the input file
        File inputFile = new File(args[0]);
        if (!inputFile.exists() || !inputFile.isFile()) {
            out.println("Error: The specified input file does not exist or is not a valid file.");
            return;
        } // end if

        // Open the output file
        File outputFile = new File(args[1]);

        // Read input file
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        StringBuilder sourceCode = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sourceCode.append(line).append("\n");
        }
        reader.close();

        // Determine file type and clean accordingly
        String cleanedCode;
        if (args[0].endsWith(".java")) {
            cleanedCode = cleanerJava.clean(sourceCode.toString());
        } else if (args[0].endsWith(".pas")) {
            cleanedCode = cleanerPascal.clean(sourceCode.toString());
        } else {
            out.println("Error: Unsupported file type. Please provide a .java or .pas file.");
            return;
        }

        // Write cleaned code to output file
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(cleanedCode);
        writer.close();

        out.println("Cleaned code has been written to " + args[1]);
    } // end main
} // end class