class ExpressionNode {
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char TIMES = '*';

    private boolean isLeafNode;

    // Leaf node
    private int leafValue;

    // Branch node
    private char operator;
    private ExpressionNode nodeLeft;
    private ExpressionNode nodeRight;

    // --------------------------------------
    public ExpressionNode(int leafValue) {
    isLeafNode = true;
    this.leafValue = leafValue;
    } // End constructor

    // --------------------------------------
    public ExpressionNode(char operator, ExpressionNode nodeLeft, ExpressionNode nodeRight) {
    isLeafNode = false;
    this.operator = operator;
    this.nodeLeft = nodeLeft;
    this.nodeRight = nodeRight;
    } // End constructor

    // --------------------------------------------
    @Override
    public String toString() {
    if (isLeafNode)
       return "(" + leafValue + ")";
    else
       return "(" + operator + " " + nodeLeft + " " + nodeRight + ")";
    } // End method


    // --------------------------------------------
    public int evaluate() {
    int result = 0;
    if (isLeafNode)
       result = leafValue;
    else {
       switch (operator) {
          case PLUS  : result = nodeLeft.evaluate() + nodeRight.evaluate();
                       break;
          case MINUS : result = nodeLeft.evaluate() - nodeRight.evaluate();
                       break;
          case TIMES : result = nodeLeft.evaluate() * nodeRight.evaluate();
                       break;
          default    : System.out.println("ERROR: Unknown operator -> " + operator);
                       result = 0;
          } // End switch
       } // End else

    return result;
    } // End method


    // -----------------------------------
    // For testing purposes only
    public static void main(String[] args) {
    int answer;

    // (2 + 3) * (4 - 5) + 6
    ExpressionNode nodeA = new ExpressionNode(2);
    ExpressionNode nodeB = new ExpressionNode(3);
    ExpressionNode nodeC = new ExpressionNode(PLUS, nodeA, nodeB);

    ExpressionNode nodeD = new ExpressionNode(4);
    ExpressionNode nodeE = new ExpressionNode(5);
    ExpressionNode nodeF = new ExpressionNode(MINUS, nodeD, nodeE);

    ExpressionNode nodeG = new ExpressionNode(TIMES, nodeC, nodeF);
    ExpressionNode nodeH = new ExpressionNode(6);
    ExpressionNode nodeI = new ExpressionNode(PLUS, nodeG, nodeH);

    System.out.println(nodeI);

    System.out.println("Answer: " + nodeI.evaluate());

    } // End main

} // End class
