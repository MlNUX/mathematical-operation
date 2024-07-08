package minux.model;

/**
 * Interface with important functions for mathematical-operations.
 *
 * @author minux
 */
public interface Formula {
    /**
     * Returns the term as a String.
     *
     * @return the term as String
     */
    public String print();

    /**
     * Calculate the mathematical-operation and returns the result.
     * -> this function works recursive
     *
     * @return the result as double value
     */
    public double calculate();
}
