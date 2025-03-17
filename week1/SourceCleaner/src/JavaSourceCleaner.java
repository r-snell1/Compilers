public class JavaSourceCleaner extends CommentCleaner {
    @Override
    public String clean(String text) {
        StringBuilder cleanedCode = new StringBuilder();
        String[] lines = text.split("\n");
        boolean insideBlockComment = false;

        for (String line : lines) {
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

            if (!line.trim().isEmpty()) {
                cleanedCode.append(line.trim()).append("\n");
            }// end if
        }// end for

        return cleanedCode.toString();
    }// end method
}// end class