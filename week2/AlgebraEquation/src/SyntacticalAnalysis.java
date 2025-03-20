import java.util.List;

public class SyntacticalAnalysis {
    static boolean syntacticalAnalysis(List<EquationChecker.Token> tokens) {
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

        for (EquationChecker.Token token : tokens) {
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
            EquationChecker.Token token = tokens.get(i);
            if (token.type == TokenType.EQUALS) {
                hasEquals = true;
                hasLeftSide = i > 0;
                hasRightSide = i < tokens.size() - 1;
            }// end if
        }// end for

        EquationChecker.Token lastToken = tokens.getLast();
        boolean validLastToken = lastToken.type == TokenType.VARIABLE || lastToken.type == TokenType.INTEGER;

        return hasEquals && hasLeftSide && hasRightSide && validLastToken;
    }// end method
}
