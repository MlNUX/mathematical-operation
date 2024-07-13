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
 * the necessary datastructures for the calculation.
 * -> example: - user input: 3*5+5
 * ---> term in the datastructures:
 * new Plus(new Times(new Number(3),new Number(5)),new Number(5))
 *
 * @author minux
 */
public final class Parser {

    private static List<Node> subTrees = new LinkedList<>();

    /**
     * Private constructor.
     */
    private Parser() {
    }

    /**
     * Parse the input string to a formula.
     *
     * @param expression user input via consol
     * @return bill as formula
     */
    public static Formula parse(final String expression) {
        Stack<Character> expressionStack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == ')') {
                int index = subTrees.size();
                subTrees.add(createTree(findOpenBracket(expressionStack)));
                for (char c1 : ("" + index).toCharArray()) {
                    expressionStack.push(c1);
                }
                expressionStack.push('b');
                expressionStack.push('#');
            } else {
                expressionStack.push(c);
            }
        }
        StringBuilder formula = new StringBuilder();
        expressionStack.forEach(formula::append);
        return preOrder(createTree(formula.toString()));
    }

    private static String findOpenBracket(final Stack<Character> stack) {
        StringBuilder content = new StringBuilder();
        while (stack.peek() != '(') {
            content.append(stack.pop());
        }
        stack.pop();
        return content.reverse().toString();
    }

    private static Node createTree(final String input) {
        Operation op = Operation.get(input);
        if (op == null) {
            return new Node(Double.parseDouble(input));
        } else {
            return splitOperation(op, input);
        }
    }

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

    private static Formula preOrder(final Node node) {
        if (node.getContent().isNumeric()) {
            return new Number(node.getContent().getdValue());
        } else {
            ArrayList<Node> children = node.getChildren();
            Formula[] formulas = new Formula[children.size()];
            children.forEach(node1 ->
                    formulas[children.indexOf(node1)] = preOrder(node1));
            switch (node.getContent().getoValue()) {
                case PLUS -> {
                    return new Plus(formulas);
                }
                case MINUS -> {
                    return new Minus(formulas);
                }
                case TIMES -> {
                    return new Times(formulas);
                }
                case DEVIDE -> {
                    return new Divide(formulas);
                }
                default -> {
                    return null;
                }
            }

        }
        return null;
    }
}
