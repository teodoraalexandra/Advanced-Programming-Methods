package Domain.values;
import Domain.types.*;

public class StringValue implements Value {
    public String val;
    public StringValue(String v) {val = v;}
    public String getVal() {return this.val;}

    public String toString() {
        String val = getVal();
        return String.valueOf(val);
    }

    public boolean equals(Object another) {
        return another instanceof StringValue;
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
