import java.util.ArrayList;
import java.util.List;

public class LexicalAnalysis {
    static List<EquationChecker.Token> lexicalAnalysis(String equation) {
        List<EquationChecker.Token> tokens = new ArrayList<>();
        boolean inToken = false;

        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);

            if (Character.isWhitespace(c)) {
                inToken = false;
                continue;
            }// end if

            if (!inToken) {
                // Check for valid token types
                if (c >= 'a' && c <= 'z') {
                    tokens.add(new EquationChecker.Token(TokenType.VARIABLE, c));
                    inToken = true;
                } else if (c >= '0' && c <= '9') {
                    tokens.add(new EquationChecker.Token(TokenType.INTEGER, c));
                    inToken = true;
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    tokens.add(new EquationChecker.Token(TokenType.OPERATOR, c));
                    inToken = true;
                } else if (c == '=') {
                    tokens.add(new EquationChecker.Token(TokenType.EQUALS, c));
                    inToken = true;
                } else {
                    return null;
                }// end else if
            } else {
                return null;
            }// end if else
        }// end for

        return tokens;
    }// end method
}
