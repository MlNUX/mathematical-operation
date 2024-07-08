package minux.model;

public class Plus extends MathematicalOperation implements Formula {

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
