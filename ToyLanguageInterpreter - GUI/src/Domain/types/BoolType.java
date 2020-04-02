package Domain.types;

import Domain.values.BoolValue;
import Domain.values.Value;

public class BoolType implements Type {
    public BoolType() {}

    public boolean equals(Object another) {
        return another instanceof BoolType;
    }

    public String getType() { return "bool";}

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    public String toString() { return "bool";}
}
