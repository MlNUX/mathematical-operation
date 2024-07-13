package minux.model.parsing;

/**
 * Wrapper class for nodecontent datatype.
 * The content could have two different datatypes.
 * -> double or operation
 *
 * @author minux
 */
public class Content {

    private final boolean numeric;
    private double dValue;
    private Operation oValue;

    /**
     * Constructor to create a content object with double value.
     *
     * @param dValue double value
     */
    public Content(final double dValue) {
        this.dValue = dValue;
        numeric = true;
    }

    /**
     * Constructor to create a content object with a operation.
     *
     * @param oValue
     */
    public Content(final Operation oValue) {
        this.oValue = oValue;
        numeric = false;
    }

    /**
     * Returns the double value.
     *
     * @return the double value
     */
    public double getdValue() {
        return dValue;
    }

    /**
     * Returns the operation value.
     *
     * @return the operation value
     */
    public Operation getoValue() {
        return oValue;
    }

    /**
     * Returns whether content has type double.
     *
     * @return true if content type is double
     */
    public boolean isNumeric() {
        return numeric;
    }

    @Override
    public String toString() {
        return numeric ? "" + dValue : oValue.name();
    }
}
