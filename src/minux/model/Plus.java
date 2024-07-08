package minux.model;

/**
 * The class for the functionality of the plus-operation.
 *
 * @author minux
 */
public class Plus extends MathematicalOperation implements Formula {
    /**
     * Constructor to create a plus-operation.
     *
     * @param formulas list of addend
     *                 -> a addend doesn't have to be a number. It could also be a term like (2 + 1)*3
     */
    public Plus(Formula... formulas) {
        super(formulas);
    }

    @Override
    public double calculate() {
        double result = 0;
        for (Formula form : getFormulas()) {
            result += form.calculate();
        }
        return result;
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        for (Formula form : getFormulas()) {
            builder.append(form.print()).append("+");
        }
        return builder.substring(0, builder.length() - 1);
    }
}
