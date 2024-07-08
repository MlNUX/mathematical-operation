package minux.model;

abstract class MathematicalOperation {

    private Formula[] formulas;

    protected MathematicalOperation(Formula... formulas) {

        this.formulas = formulas;
    }

    public Formula[] getFormulas() {
        return formulas.clone();
    }
}
