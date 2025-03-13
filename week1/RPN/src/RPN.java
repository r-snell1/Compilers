import java.util.Stack;
import static java.lang.System.out;

public class RPN {
    static int postFixCal(String input) throws ArithmeticException{
        Stack<Integer> stack = new Stack<>();
        String[] elements = input.split(" ");
        Integer op1 = 0;
        Integer op2 = 0;
        try {
            for (String element : elements) {
                if ("+-*/".contains(element)) {
                    if (stack.size() >= 2) {
                        op1 = stack.pop();
                        op2 = stack.pop();
                    } else {
                        out.println("Error: invalid order of string");
                    }
                    switch (element) {
                        case "+":
                            stack.push(op2 + op1);
                            break;
                        case "-":
                            stack.push(op2 - op1);
                            break;
                        case "*":
                            stack.push(op2 * op1);
                            break;
                        case "/":
                            // potential issues with fractional values and remainders
                            if (op1 == 0) {
                                throw new ArithmeticException("Division by zero");
                            } else {
                                stack.push(op2 / op1);
                                break;
                            }// end if else
                    }// end switch
                } else {
                    stack.push(Integer.parseInt(element));
                }// end if else
            }// end for
            // potential issue
            return stack.peek();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }// end try catch
    }// end method
}// end class
