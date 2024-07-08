package minux.model;

public class Bracket implements Formula{

    private Formula formula;

    public Bracket(Formula formula) {
        this.formula = formula;
    }
    @Override
    public String print() {
        return "(" + formula.print() + ")";
    }

    @Override
    public double calculate() {
        return formula.calculate();
    }
}