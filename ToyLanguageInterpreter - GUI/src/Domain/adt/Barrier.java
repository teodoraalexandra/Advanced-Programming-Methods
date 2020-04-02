package Domain.adt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Barrier<Key, Value> implements IBarrier<Key, Value> {
    private Map<Key, Value> store;
    private AtomicInteger index = new AtomicInteger(1);

    public Barrier() {store = new ConcurrentHashMap<>(); }

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
    public boolean contains(Key id) {
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
    public Value get(Key key) {
        return store.get(key);
    }

    @Override
    public IBarrier<Key, Value> getDictionary() {
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
    public Iterable<Key> getAll() {
        return store.keySet();
    }

    @Override
    public AtomicInteger getIndex() {
        index.incrementAndGet();
        return index;
    }
}
