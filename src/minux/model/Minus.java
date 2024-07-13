package minux.model;

/**
 * The class for the functionality of the minus-operation.
 *
 * @author minux
 */
public class Minus extends MathematicalOperation implements Formula {
    /**
     * Constructor to create a minus-operation.
     *
     * @param formulas list of subtrahend terms
     *                 -> a subtrahend term doesn't have to be a number.
     *                 It could also be a term like (2 + 1)*3
     */
    public Minus(final Formula... formulas) {
        super(formulas);
    }

    @Override
    public double calculate() {
        double result = getFormulas()[0].calculate();
        for (int i = 1; i < getFormulas().length; i++) {
            result -= getFormulas()[i].calculate();
        }
        return result;
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        for (Formula form : getFormulas()) {
            builder.append(form.print()).append("-");
        }
        return builder.substring(0, builder.length() - 1);
    }
}
