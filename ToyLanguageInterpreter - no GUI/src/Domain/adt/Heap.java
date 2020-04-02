package Domain.adt;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Heap<Key, Value> implements IHeap<Key, Value> {
    private Map<Key, Value> store;
    private AtomicInteger index = new AtomicInteger(1);

    public Heap() {store = new ConcurrentHashMap<>(); }

    @Override
    public void add(Key k, Value v) {
        store.put(k, v);
    }

    @Override
    public void remove(Key k) {
        store.remove(k);
    }

    @Override
    public Value lookup(Key id) {
        return store.get(id);
    }

    @Override
    public boolean isDefined(Key id) {
        return store.containsKey(id);
    }

    @Override
    public void update(Key id, Value val) {
        index.incrementAndGet();
        store.replace(id, val);
    }

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    public String toString() {
        return store.toString();
    }

    @Override
    public Map<Key, Value> getContent() {
        return store;
    }

    @Override
    public IHeap<Key, Value> getDictionary() {
        return this;
    }

    @Override
    public void setContent(Map<Key, Value> newHashTable) {
        store = newHashTable;
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    @Override
    public AtomicInteger getIndex() {
        return index;
    }

    @Override
    public int getNewFreeAddress() {
        return store.size() + 1;
    }
}
