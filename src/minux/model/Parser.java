package minux.model;

/**
 * This class parses the user input to the necessary datastructures for the calculation.
 * -> example: - user input: 3*5+5 ---> term in the datastructures: new Plus(new Times(new Number(3),new Number(5)),new Number(5))
 *
 * @author minux
 */
public final class Parser {
    /**
     * the priority of operations as a regex array
     */
    private static final String[] PRIOLIST = {"\\+", "\\-", "\\/", "\\*"};

    /**
     * Private constructor.
     */
    private Parser() {
    }

    /**
     * Parse a user input string to the correct datastructures
     *
     * @param expression the user input as string
     * @return term in the datastructures
     */
    public static Formula parse(String expression) {
        // TODO Cleanup this method
        // TODO Code a method the check whether the user input syntax is correct
        //      -> user input without whitespaces

        if (expression.contains("(")) {
            expression = splitBrackets(expression);
        }

        return split(expression.split(PRIOLIST[0]), 1);
    }

    /**
     * Support method to split the user input into smaller pieces with the same operation.
     *
     * @param expressionArray smaller pieces there are connected with the same operation
     * @param index           index of the current operation
     * @return the user input to the necessary datastructures for the calculation
     */
    private static Formula split(String[] expressionArray, int index) {
        Formula[] formulas = new Formula[expressionArray.length];

        for (int i = 0; i < expressionArray.length; i++) {
            String currentExpression = expressionArray[i].trim();
            // Regex for all possible double values
            if (currentExpression.matches("-?\\d+(\\.\\d+)?")) {
                formulas[i] = new Number(Double.parseDouble(currentExpression));
            } else {
                String[] subExpressions = currentExpression.split(PRIOLIST[index], index + 1);
                formulas[i] = split(subExpressions, index + 1);
            }
        }
        return switch (index - 1) {
            case 0 -> new Plus(formulas);
            case 1 -> new Minus(formulas);
            case 2 -> new Divided(formulas);
            case 3 -> new Times(formulas);
            default -> null;
        };
    }

    /**
     * Support structure to handle brackets in the term.
     * -> method in progress
     *
     * @param expression the user input
     * @return the user input with simplified all parentheses
     */
    private static String splitBrackets(String expression) {
        /**
         *
         * ()(), (())
         * 3*(5+(3*5)-3)+(5*3)
         */
        // TODO Longer terms are solved to slow!!
        //      Needs a faster algorithm!!!!
        String subString = "";
        int firstOpenBracket = 0;
        int lastClosedBracket = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                int openBrackets = 1;
                firstOpenBracket = i;
                for (int t = i + 1; t < expression.length(); t++) {
                    if (expression.charAt(t) == '(') {
                        openBrackets++;
                    } else if (expression.charAt(t) == ')') {
                        openBrackets--;
                    }
                    if (openBrackets == 0) {
                        subString = expression.substring(firstOpenBracket + 1, t);
                        lastClosedBracket = t;
                        break;
                    }
                }
                break;
            }
        }
        String prefix = firstOpenBracket > 0 ? expression.substring(0, firstOpenBracket) : "";
        String postfix = lastClosedBracket < expression.length() - 1 ? expression.substring(lastClosedBracket + 1, expression.length()) : "";
        if (subString.contains("(")) {
            return prefix + splitBrackets(subString) + postfix;
        } else {
            String resultPart = prefix + split(subString.split(PRIOLIST[0]), 1).calculate() + postfix;
            return resultPart.contains("(") ? splitBrackets(resultPart) : String.valueOf(split(resultPart.split(PRIOLIST[0]), 1).calculate());
        }
    }
}
