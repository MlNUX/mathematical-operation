package minux.model.parsing;

import minux.model.*;
import minux.model.Number;

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

    private static final char CLOSED_BRACKET = ')';
    private static final char OPEN_BRACKET = '(';
    private static final String REGEX_PREFIX = "\\";

    /**
     * Private Constructor.
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
        //TODO add method to check input whether its correcty
        Stack<Character> expressionStack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == CLOSED_BRACKET) {
                char[] result = calculateBracket(expressionStack).toCharArray();
                for (char value : result) {
                    expressionStack.push(value);
                }
            } else {
                expressionStack.push(c);
            }
        }
        StringBuilder formula = new StringBuilder();
        expressionStack.forEach(formula::append);
        return converteToFormula(formula.toString());
    }

    //TODO add java-doc
    private static String calculateBracket(final Stack<Character> stack) {
        return "" + findOpenBracket(stack).calculate();
    }

    //TODO add java-doc
    private static Formula findOpenBracket(final Stack<Character> stack) {
        StringBuilder content = new StringBuilder();
        while (stack.peek() != OPEN_BRACKET) {
            content.append(stack.pop());
        }
        stack.pop();
        String input = content.reverse().toString();
        return converteToFormula(input);
    }

    //TODO add java-doc
    private static Formula converteToFormula(final String expression) {
        Operation op = Operation.get(expression);
        if (op == null) {
            return new Number(Double.parseDouble(expression));
        } else {
            return splitOperation(op, expression);
        }
    }

    //TODO add java-doc
    private static Formula splitOperation(final Operation operation,
                                          final String expression) {
        String[] expressionArray = expression.split(REGEX_PREFIX
                + operation.getSymbol());
        Formula[] formulas = new Formula[expressionArray.length];
        for (int i = 0; i < expressionArray.length; i++) {
            String s = expressionArray[i];
            Operation nextOp = Operation.get(s);
            if (nextOp == null) {
                if (s.equalsIgnoreCase("x")) {
                    formulas[i] = new X();
                } else {
                    formulas[i] = new Number(s.isEmpty() ? 0
                            : Double.parseDouble(s));
                }
            } else {
                formulas[i] = splitOperation(nextOp, s);
            }
        }
        switch (operation) {
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

}
