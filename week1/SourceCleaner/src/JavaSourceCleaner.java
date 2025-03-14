import java.io.*;
import static java.lang.System.out;

public class JavaSourceCleaner {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            out.println("Usage: java JavaSourceCleaner <input-file> <output-file>");
            return;
        }// end if

        // Open the input file
        File inputFile = new File(args[0]);
        if (!inputFile.exists() || !inputFile.isFile()) {
            out.println("Error: The specified input file does not exist or is not a valid file.");
            return;
        }// end if

        // Open the output file
        File outputFile = new File(args[1]);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        // Open the input file for reading
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        StringBuilder cleanedCode = new StringBuilder();
        String line;
        boolean insideBlockComment = false;

        // Process the file line by line
        while ((line = reader.readLine()) != null) {
            if (insideBlockComment) {
                int endIndex = line.indexOf("*/");
                if (endIndex != -1) {
                    line = line.substring(endIndex + 2);
                    insideBlockComment = false;
                } else {
                    continue;
                }// end if else
            }// end if

            int blockCommentStart = line.indexOf("/*");
            while (blockCommentStart != -1) {
                int blockCommentEnd = line.indexOf("*/", blockCommentStart);
                if (blockCommentEnd != -1) {
                    line = line.substring(0, blockCommentStart) + line.substring(blockCommentEnd + 2);
                    blockCommentStart = line.indexOf("/*");
                } else {
                    line = line.substring(0, blockCommentStart);
                    insideBlockComment = true;
                    break;
                }// end if else
            }// end while

            int singleLineComment = line.indexOf("//");
            if (singleLineComment != -1) {
                line = line.substring(0, singleLineComment);
            }// end if

            if (line.trim().isEmpty()) {
                continue;
            }// end if

            line = line.replace("\t", "");
            cleanedCode.append(line.trim()).append("\n");
        }// end while

        reader.close();

        // Write cleaned code to the output file
        writer.write(cleanedCode.toString());
        writer.close();

        out.println("Cleaned code has been written to " + args[1]);
    }// end main
}// end class