package Domain.values;

import Domain.types.RefType;
import Domain.types.Type;

public class RefValue implements Value{
    private int address;
    private Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public String toString() {
        int addr = getAddr();
        Type location = getLocationType();
        return "(" + addr + ", " + location.toString() + ")";
    }

    public boolean equals(Object another) {
        return another instanceof RefValue;
    }

    public int getAddr() {return address;}
    public Type getLocationType() {return locationType;}
    public Type getType() { return new RefType(locationType);}
}
