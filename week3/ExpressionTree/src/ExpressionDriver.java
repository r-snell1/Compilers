class ExpressionDriver {

   public static void main(String[] args) {
   ExpressionParser myParser = new ExpressionParser();
   ExpressionNode myNode;
   String expression = null;

   if (args.length != 1) {
      System.out.println("Usage: java ExpressionDriver (+(3)(5))");
      return;
      } else {
      expression = removeSpaces(args[0]);
      myNode = myParser.parse(expression);
   
      System.out.println("Parse Tree: " + myNode);
      System.out.println("Result: " + myNode.evaluate());
      } // End if else

   } // End main

// -----------------------------------------------
   private static String removeSpaces(String phrase) {
   final char SPACE = ' ';
   int i;

   StringBuffer list = new StringBuffer(phrase);
   i = 0;
   while (i < list.length()) {
      if (list.charAt(i) == SPACE)
         list.deleteCharAt(i);
      else
         i++;
      } // End while

   return list.toString();
   } // End method

} // End class
