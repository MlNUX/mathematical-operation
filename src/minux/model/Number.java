package minux.model;

/**
 * This class represents a number as a formula.
 *
 * @author minux
 */
public class Number implements Formula {

    private double x;

    /**
     * Constructor to create a number-formula-object.
     *
     * @param x the number as double
     */
    public Number(double x) {
        this.x = x;
    }

    @Override
    public double calculate() {
        return x;
    }

    @Override
    public String print() {
        return String.valueOf(x);
    }
}
