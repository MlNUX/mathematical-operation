package minux.model;

/**
 * Parent class to save the parts of the mathematical operations.
 *
 * @author minux
 */
abstract class MathematicalOperation {

    private Formula[] formulas;

    /**
     * Constructor to save the parts of the operation.
     *
     * @param formulas the parts of the operation
     */
    protected MathematicalOperation(Formula... formulas) {

        this.formulas = formulas;
    }

    /**
     * Returns the parts of the operation.
     *
     * @return the parts of the operation as static array
     */
    public Formula[] getFormulas() {
        return formulas.clone();
    }
}
