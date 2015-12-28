package generic;

import generic.xml.XMLAttribute;
import interfaces.Nested;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jonathan on 30-10-15.
 */
public class NestedElement<T extends Element> extends Element implements Nested<T> {





    private LinkedList<T> children = new LinkedList<>();
    private T parent;

    public NestedElement(T parent, final String name) {
        super(name);
        this.parent = parent;
    }

    public NestedElement(T parent, final String name, final String value) {
        super(name, value);
        this.parent = parent;
    }

    @Override
    public LinkedList<T> getChildren() {
        return children;
    }

    @Override
    public T getParent() {
        return parent;
    }

    @Override
    public T getChildAt(int index) {
        return children.get(index);
    }

    @Override
    public void addChild(T child) {
        children.addLast(child);
    }

    public void setParent(T parent) {
        this.parent = parent;
    }
}
