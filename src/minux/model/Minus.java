package minux.model;

public class Minus extends MathematicalOperation implements Formula {

    public Minus(Formula... formulas) {
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
