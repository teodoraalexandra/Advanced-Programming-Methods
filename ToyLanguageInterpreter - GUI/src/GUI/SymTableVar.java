package GUI;

import Domain.values.Value;

public class SymTableVar {
    private String key;
    private Value value;

    SymTableVar(String key, Value value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String fKey) {
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
