package Domain.adt;

public interface MyIStack<T> {
    void push(T value);
    T pop();
    boolean contains(T value);
    int size();
    void clear();
    boolean isEmpty();
    String toString();
}
