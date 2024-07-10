package minux.model.parsing;

import minux.model.Divided;
import minux.model.Formula;
import minux.model.Minus;
import minux.model.Number;
import minux.model.Plus;
import minux.model.Times;

import java.util.ArrayList;

public class OperationTree {

    private Note<?> root;

    public OperationTree(Note root) {
        this.root = root;
    }

    public void addNote(Note<?> note, OperationNote parent) {
        parent.addChild(note);
    }

    public Formula preOrder() {
        return preOrder(root);
    }

    private Formula preOrder(Note note) {
        if (note.getClass() == Leave.class) {
            Leave leave = (Leave) note;
            return new Number((leave.getContent()));
        } else {
            OperationNote operation = (OperationNote) note;
            ArrayList<Note> children = operation.getChildren();
            Formula[] formulas = new Formula[children.size()];
            children.forEach(note1 -> formulas[children.indexOf(note1)] = preOrder(note1));
            switch (operation.getContent()) {
                case PLUS -> {
                    return new Plus(formulas);
                }
                case MINUS -> {
                    return new Minus(formulas);
                }
                case TIMES -> {
                    return new Times(formulas);
                }
                case DIVEIDE -> {
                    return new Divided(formulas);
                }
            }

        }
    }
}
