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

        List<Token> tokens = LexicalAnalysis.lexicalAnalysis(equation);

        if (tokens == null) {
            System.out.println("LEXICAL ERROR: The equation is not in the correct format");
            return;
        }// end if

        boolean syntaxValid = SyntacticalAnalysis.syntacticalAnalysis(tokens);
        if (syntaxValid) {
            System.out.println("SUCCESS: The equation is in the correct format");
        } else {
            System.out.println("SYNTAX ERROR: The equation is not in the correct format");
        }// end if else
    }// end main




}// end class