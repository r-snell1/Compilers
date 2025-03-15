import java.util.ArrayList;
import java.util.List;

public class EquationChecker {
    static class Token {
        TokenType type;
        char value;

        public Token(TokenType type, char value) {
            this.type = type;
            this.value = value;
        }// end instantiation

        @Override
        public String toString() {
            return type + "(" + value + ")";
        }// end override
    }// end class

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java EquationChecker \"equation\"");
            return;
        }// end if

        String equation = args[0];

        List<Token> tokens = lexicalAnalysis(equation);

        if (tokens == null) {
            System.out.println("LEXICAL ERROR: The equation is not in the correct format");
            return;
        }// end if

        boolean syntaxValid = syntacticalAnalysis(tokens);
        if (syntaxValid) {
            System.out.println("SUCCESS: The equation is in the correct format");
        } else {
            System.out.println("SYNTAX ERROR: The equation is not in the correct format");
        }// end if else
    }// end main

    private static List<Token> lexicalAnalysis(String equation) {
        List<Token> tokens = new ArrayList<>();
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
                    tokens.add(new Token(TokenType.VARIABLE, c));
                    inToken = true;
                } else if (c >= '0' && c <= '9') {
                    tokens.add(new Token(TokenType.INTEGER, c));
                    inToken = true;
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    tokens.add(new Token(TokenType.OPERATOR, c));
                    inToken = true;
                } else if (c == '=') {
                    tokens.add(new Token(TokenType.EQUALS, c));
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

    private static boolean syntacticalAnalysis(List<Token> tokens) {
        // Variable,               Integer,                Operator,               Equals,                 Unknown
        State[][] transitionMatrix = {
                { State.TERM_LEFT,       State.TERM_LEFT,        State.ERROR,            State.ERROR,            State.ERROR }, // START
                { State.ERROR,           State.ERROR,            State.OPERATOR_LEFT,    State.EQUALS,           State.ERROR }, // TERM_LEFT
                { State.TERM_LEFT,       State.TERM_LEFT,        State.ERROR,            State.ERROR,            State.ERROR }, // OPERATOR_LEFT
                { State.TERM_RIGHT,      State.TERM_RIGHT,       State.ERROR,            State.ERROR,            State.ERROR }, // EQUALS
                { State.ERROR,           State.ERROR,            State.OPERATOR_RIGHT,   State.ERROR,            State.ERROR }, // TERM_RIGHT
                { State.TERM_RIGHT,      State.TERM_RIGHT,       State.ERROR,            State.ERROR,            State.ERROR }, // OPERATOR_RIGHT
                { State.ERROR,           State.ERROR,            State.ERROR,            State.ERROR,            State.ERROR }, // ACCEPT
                { State.ERROR,           State.ERROR,            State.ERROR,            State.ERROR,            State.ERROR }  // ERROR
        };

        State currentState = State.START;

        for (Token token : tokens) {
            int row = currentState.ordinal();
            int col = token.type.ordinal();

            currentState = transitionMatrix[row][col];

            if (currentState == State.ERROR) {
                return false;
            }// end if
        }// end for

        if (tokens.size() < 3) return false;
        boolean hasEquals = false;
        boolean hasLeftSide = false;
        boolean hasRightSide = false;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.type == TokenType.EQUALS) {
                hasEquals = true;
                hasLeftSide = i > 0;
                hasRightSide = i < tokens.size() - 1;
            }// end if
        }// end for

        Token lastToken = tokens.getLast();
        boolean validLastToken = lastToken.type == TokenType.VARIABLE || lastToken.type == TokenType.INTEGER;

        return hasEquals && hasLeftSide && hasRightSide && validLastToken;
    }// end method
}// end class