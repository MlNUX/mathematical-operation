package minux.model.parsing;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a node for a tree.
 * It has a content and a variable number of children
 *
 * @author minux
 */
public class Node {

    private final Content content;
    private List<Node> children;

    /**
     * Create a leave of the tree with a double value.
     *
     * @param content the double value
     */
    public Node(final double content) {
        this.content = new Content(content);
        this.children = new ArrayList<>();
    }

    /**
     * Create a inner node with a mathematical operation as its content.
     *
     * @param operations the operation
     */
    public Node(final Operation operations) {
        this.content = new Content(operations);
        this.children = new ArrayList<>();
    }

    /**
     * Adds a child to this node.
     *
     * @param child the child node
     */
    public void addChild(final Node child) {
        children.add(child);
    }

    /**
     * Returns the content.
     *
     * @return the content
     */
    public Content getContent() {
        return content;
    }

    /**
     * Returns the arraylist of all childrens.
     *
     * @return the arraylist
     */
    public ArrayList<Node> getChildren() {
        return (ArrayList<Node>) children;
    }

    //TODO update this method to a cleaner version
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(content.toString())
                .append("\n");
        children.forEach(node -> builder.append(node.toString()));
        return builder.toString();
    }
}
