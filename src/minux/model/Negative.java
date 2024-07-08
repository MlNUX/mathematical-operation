package minux.model;

public class Negative implements Formula {
    private Formula x;

    public Negative(Formula x) {
        this.x = x;
    }

    @Override
    public String print() {
        return "-" + x.print();
    }

    @Override
    public double calculate() {
        return new Minus(new Number(0), x).calculate();
    }
}
