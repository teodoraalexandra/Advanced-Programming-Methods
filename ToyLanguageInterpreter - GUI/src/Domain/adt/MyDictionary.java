package Domain.adt;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyDictionary<Key, Value> implements MyIDictionary<Key, Value>, Cloneable {
    private Map<Key, Value> store;
    private AtomicInteger index = new AtomicInteger(1);

    public MyDictionary() {store = new ConcurrentHashMap<>(); }

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

    public Map<Key, Value> getContent() {
        return store;
    }

    @Override
    public MyIDictionary<Key, Value> getDictionary() {
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
    public Set<Key> keySet() {
        return store.keySet();
    }

    @Override
    public AtomicInteger getIndex() {
        return index;
    }

    @Override
    public Iterable<Map.Entry<Key, Value>> getAll() {
        return store.entrySet();
    }

    @Override
    public Value put(Key key, Value value){
        return store.put(key, value);
    }

    @Override
    public MyIDictionary<Key, Value> clone_dict() {
        MyIDictionary<Key, Value> di = new MyDictionary<>();
        for(Key key : this.keySet())
            di.put(key, store.get(key));
        return di;
    }
}
