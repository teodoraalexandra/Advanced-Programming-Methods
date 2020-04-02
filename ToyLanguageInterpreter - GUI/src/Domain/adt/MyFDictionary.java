package Domain.adt;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

public class MyFDictionary<String, BufferedReader> implements MyIFDictionary<String, BufferedReader> {
    Hashtable<String, BufferedReader> store = new Hashtable<String, BufferedReader>();

    @Override
    public void add(String k, BufferedReader v) {
        store.put(k, v);
    }

    @Override
    public void remove(String k) {
        store.remove(k);
    }

    @Override
    public BufferedReader lookup(String id) {
        return store.get(id);
    }

    @Override
    public boolean isDefined(String id) {
        return store.containsKey(id);
    }

    @Override
    public void update(String id, BufferedReader val) {
        store.replace(id, val);
    }

    @Override
    public void delete(String id) {
        store.remove(id);
    }

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    public java.lang.String toString() {
        return store.toString();
    }

    @Override
    public Iterable<Map.Entry<String, BufferedReader>> getAll() {
        return store.entrySet();
    }

    public Collection<String> getKeys(){
        return store.keySet();
    }
}
