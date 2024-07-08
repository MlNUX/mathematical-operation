package minux.model;

public class Number implements Formula {

    private double x;

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
