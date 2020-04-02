package Domain.adt;

public interface MyIList<T> {
    void add(T value);
    void remove(T value);
    boolean contains(T value);
    int size();
    void clear();
    boolean isEmpty();
    String toString();
}
