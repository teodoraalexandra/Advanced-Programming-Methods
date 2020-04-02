package GUI;

import Domain.values.Value;

public class HeapVar {
    private Integer key;
    private Value value;

    HeapVar(Integer key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return this.key;
    }

    public void setKey(Integer fKey) {
        this.key = fKey;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value fValue) {
        this.value = fValue;
    }

    public String toString() {
        return this.key + " " + this.value;
    }
}

