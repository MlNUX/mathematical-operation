package minux.model;

public class X implements Formula {

    private double value;

    public X() {
        value = 0;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String print() {
        return "x";
    }

    @Override
    public double calculate() {
        return 0;
    }
}
