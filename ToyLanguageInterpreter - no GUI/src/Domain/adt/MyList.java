package Domain.adt;
import java.util.*;

public class MyList<T> implements MyIList<T> {
    List<T> store = new ArrayList<T>(10);

    @Override
    public void add(T value) {
        store.add(value);
    }

    @Override
    public void remove(T value) {
        store.remove(value);
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
}
