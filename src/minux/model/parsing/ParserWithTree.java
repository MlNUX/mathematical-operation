package minux.model.parsing;

import minux.model.Formula;
import minux.model.Number;
import minux.model.Plus;
import minux.model.Minus;
import minux.model.Times;
import minux.model.Divide;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * This class parses the user input to
 * the necessary datastructures for the calculation with a tree.
 * -> example: - user input: 3*5+5
 * ---> term in the datastructures:
 * new Plus(new Times(new Number(3),new Number(5)),new Number(5))
 *
 * @author minux
 */
public final class ParserWithTree {

    private static final char CLOSED_BRACKET = ')';
    private static final char B = 'b';
    private static final char TREESIGN = '#';
    private static final char OPEN_BRACKET = '(';
    private static List<Node> subTrees = new LinkedList<>();

    /**
     * Private constructor.
     */
    private ParserWithTree() {
    }

    /**
     * Parse the input string to a formula.
     *
     * @param expression user input via consol
     * @return bill as formula
     */
    public static Formula parse(final String expression) {
        //TODO add method to check input whether its correcty
        Stack<Character> expressionStack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == CLOSED_BRACKET) {
                int index = subTrees.size();
                subTrees.add(createTree(findOpenBracket(expressionStack)));
                for (char c1 : ("" + index).toCharArray()) {
                    expressionStack.push(c1);
                }
                expressionStack.push(B);
                expressionStack.push(TREESIGN);
            } else {
                expressionStack.push(c);
            }
        }
        StringBuilder formula = new StringBuilder();
        expressionStack.forEach(formula::append);
        return preOrder(createTree(formula.toString()));
    }

    //TODO add java-doc
    private static String findOpenBracket(final Stack<Character> stack) {
        StringBuilder content = new StringBuilder();
        while (stack.peek() != OPEN_BRACKET) {
            content.append(stack.pop());
        }
        stack.pop();
        return content.reverse().toString();
    }

    //TODO add java-doc
    private static Node createTree(final String input) {
        Operation op = Operation.get(input);
        if (op == null) {
            return new Node(Double.parseDouble(input));
        } else {
            return splitOperation(op, input);
        }
    }

    //TODO add java-doc
    private static Node splitOperation(final Operation operation,
                                       final String expression) {
        String[] expressionArray = expression.split("\\"
                + operation.getSymbol());
        Node parent = new Node(operation);

        for (String s : expressionArray) {
            Operation nextOp = Operation.get(s);
            if (nextOp == null) {
                if (s.matches("\\#b[0-9]+\\b")) {
                    parent.addChild(subTrees.get(
                            Integer.parseInt(s.substring(2))));
                } else if (s.matches("[0-9]+b#")) {
                    parent.addChild(subTrees.get(Integer.parseInt(
                            String.valueOf(s.charAt(0)))));
                } else {
                    parent.addChild(new Node(s.isEmpty() ? 0
                            : Double.parseDouble(s)));
                }
            } else {
                parent.addChild(splitOperation(nextOp, s));
            }
        }
        return parent;
    }

    //TODO add java-doc
    private static Formula preOrder(final Node node) {
        if (node.getContent().isNumeric()) {
            return new Number(node.getContent().getdValue());
        } else {
            ArrayList<Node> children = node.getChildren();
            Formula[] formulas = new Formula[children.size()];
            children.forEach(node1 ->
                    formulas[children.indexOf(node1)] = preOrder(node1));
            Formula result;
            switch (node.getContent().getoValue()) {
                case PLUS -> {
                    result = new Plus(formulas);
                }
                case MINUS -> {
                    result = new Minus(formulas);
                }
                case TIMES -> {
                    result = new Times(formulas);
                }
                case DEVIDE -> {
                    result = new Divide(formulas);
                }
                default -> {
                    result = null;
                }
            }
            return result;
        }
    }
}
