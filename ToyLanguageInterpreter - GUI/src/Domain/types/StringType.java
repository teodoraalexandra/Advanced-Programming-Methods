package Domain.types;

import Domain.values.StringValue;
import Domain.values.Value;

public class StringType implements Type {
    public StringType() {}

    public boolean equals(Object another) {
        return another instanceof StringType;
    }

    @Override
    public String getType() {
        return "string";
    }

    @Override
    public Value defaultValue() {
        return new StringValue("Null");
    }

    public String toString() { return "string";}
}
