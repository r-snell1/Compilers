import static java.lang.System.out;
import java.util.*;
import java.lang.Math;

public class RPNDriver {
    public static void main(String[] args) {
        try {
            out.println(RPN.postFixCal("23 145 + 75 8 / *"));
        } catch (ArithmeticException e) {
            out.println(e.getMessage());
        } finally {
            // always runs even in the even of the crash
            System.exit(0);
        }
    }// end main
}// end class
