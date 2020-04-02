package Domain.adt;

import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> store = new Stack<T>();

    @Override
    public T pop() {
        return store.pop();
    }

    @Override
    public void push(T value) {
        store.push(value);
    }

    @Override
    public boolean contains(T value) {
        return store.contains(value);
    }

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public void clear() {
        store.clear();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    public String toString() {
        return store.toString();
    }

    public T firstElement() {
        return store.get(0);
    }

    public T secondElement() {
        return store.get(1);
    }

    @Override
    public List<T> getAll() {
        return store;
    }
}
