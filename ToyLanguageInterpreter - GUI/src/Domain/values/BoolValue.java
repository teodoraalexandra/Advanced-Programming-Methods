package Domain.values;

import Domain.types.BoolType;
import Domain.types.Type;

public class BoolValue implements Value {
    private boolean val;
    public BoolValue(boolean v) {val=v;}
    public boolean getVal() {return val;}

    public String toString() {
        boolean val = getVal();
        return String.valueOf(val);
    }

    public boolean equals(Object another) {
        return another instanceof BoolValue;
    }

    public Type getType() { return new BoolType();}
}
