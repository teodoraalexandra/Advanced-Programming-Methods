package Domain.adt;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface IBarrier<Key, Value> {
    void add(Key k, Value v);
    void remove(Key k);
    int size();
    boolean isEmpty();

    Value lookup(Key id); //returns the value associated with id
    boolean contains(Key id); //true if key exists in dictionary

    void update(Key id, Value val); //change existent value of key with a new one
    String toString();

    Map<Key, Value> getContent();
    Value get(Key key);
    IBarrier<Key, Value> getDictionary();
    void setContent(Map<Key, Value> newHashTable);
    Object clone() throws CloneNotSupportedException;
    AtomicInteger getIndex();

    Iterable<Key> getAll();
}
