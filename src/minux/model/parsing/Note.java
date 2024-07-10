package minux.model.parsing;

public abstract class Note<T> {

    private final T content;

    protected Note(T content){
        this.content = content;
    }

    public T getContent() {
        return content;
    }

}
