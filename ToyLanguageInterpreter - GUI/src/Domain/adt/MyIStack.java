package Domain.adt;

import java.util.List;

public interface MyIStack<T> {
    void push(T value);
    T pop();
    boolean contains(T value);
    int size();
    void clear();
    boolean isEmpty();
    String toString();
    T firstElement();
    T secondElement();

    List<T> getAll();
}
