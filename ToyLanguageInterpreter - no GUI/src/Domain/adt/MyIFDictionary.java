package Domain.adt;

public interface MyIFDictionary<String, BufferedReader> {
    void add(String k, BufferedReader v);
    void remove(String k);
    int size();
    boolean isEmpty();

    BufferedReader lookup(String id); //returns the value associated with id
    boolean isDefined(String id); //true if key exists in dictionary

    void update(String id, BufferedReader val); //change existent value of key with a new one
    void delete(String id);
    java.lang.String toString();
}
