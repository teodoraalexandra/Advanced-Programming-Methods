package Domain.types;
import Domain.values.IntValue;
import Domain.values.Value;

public class IntType implements Type {
    public IntType() {}

    public boolean equals(Object another) {
        return another instanceof IntType;
    }

    public String getType() {return "int";}

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }

    public String toString() { return "int";}
}
