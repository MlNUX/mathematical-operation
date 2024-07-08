package minux.model;

public final class Parser {

    private static final String[] PRIOLIST = {"\\+", "\\-", "\\/", "\\*"};

    private Parser() {
    }

    public static Formula parse(String expression) {

        expression.trim();

        if (expression.contains("(")) {
            expression = splitBrackets(expression);
        }

        Formula result = split(expression.split(PRIOLIST[0]), 1);
        return result;
    }

    private static Formula split(String[] expressionArray, int index) {
        Formula[] formulas = new Formula[expressionArray.length];

        for (int i = 0; i < expressionArray.length; i++) {
            String currentExpression = expressionArray[i].trim();

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

    private static String splitBrackets(String expression) {
        /**
         *
         * ()(), (())
         * 3*(5+(3*5)-3)+(5*3)
         */
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
