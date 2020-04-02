package Domain.values;
import Domain.types.*;

public class IntValue implements Value {
    private int val;
    public IntValue(int v) {val=v;}
    public int getVal() {return val;}

    public String toString() {
        int val = getVal();
        return String.valueOf(val);
    }

    public boolean equals(Object another) {
        return another instanceof IntValue;
    }

    public Type getType() { return new IntType();}
}
