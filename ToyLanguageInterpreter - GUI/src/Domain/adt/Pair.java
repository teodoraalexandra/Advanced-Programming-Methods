package Domain.adt;

public class Pair<K, V> {
    private K first;
    private V second;

    public Pair(K k, V v) {
        this.first = k;
        this.second = v;
    }

    public K getFirst() { return this.first; }
    public V getSecond() { return this.second; }

    public void setFirst(K newFirst) { this.first = newFirst; }
    public void setSecond(V newSecond) { this.second = newSecond; }

    public String toString() {
        return first.toString() + " " + second.toString();
    }
}
