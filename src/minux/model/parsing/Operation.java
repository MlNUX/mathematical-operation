package minux.model.parsing;

/**
 * All operations that cloud use the calculator.
 *
 * @author minux
 */
public enum Operation {
    /**
     * The plus operation.
     */
    PLUS("+"),
    /**
     * The minus operation.
     */
    MINUS("-"),
    /**
     * The times operation.
     */
    TIMES("*"),
    /**
     * The devide operation.
     */
    DEVIDE("/");
    /**
     * The symbol you use in the consol for this operation.
     */
    private final String symbol;

    /**
     * Constructor.
     *
     * @param symbol symbol of the operation
     */
    Operation(final String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol of the operation.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the highest priority operation containing the expression.
     *
     * @param expression the expression as string
     * @return the highest operation
     */
    public static Operation get(final String expression) {
        for (Operation op : values()) {
            if (expression.contains(op.getSymbol())) {
                return op;
            }
        }
        return null;
    }
}
