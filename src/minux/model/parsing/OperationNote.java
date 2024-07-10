package minux.model.parsing;

import java.util.ArrayList;

public class OperationNote extends Note<Operations>{

    private ArrayList<Note> children;

    protected OperationNote(Operations content) {
        super(content);
    }

    public ArrayList<Note> getChildren() {
        return children;
    }

    public void addChild(Note child){
        children.add(child);
    }
}
