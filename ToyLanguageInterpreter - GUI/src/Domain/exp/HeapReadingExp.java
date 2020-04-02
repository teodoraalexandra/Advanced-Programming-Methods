package Domain.exp;

import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.types.IntType;
import Domain.types.RefType;
import Domain.types.Type;
import Domain.values.RefValue;
import Domain.values.Value;
import Exception.MyException;

public class HeapReadingExp implements Exp {
    private Exp e;
    public HeapReadingExp(Exp e) {
        this.e = e;
    }

    public Value eval(MyIDictionary<String, Value> tbl, IHeap<Integer, Value> hp) throws MyException {
        Value v;
        v = e.eval(tbl, hp);
        Type typeOfVarName = v.getType();
        Type refType = new RefType(new IntType());
        if (typeOfVarName.getType().equals(refType.getType())) {
            int address = ((RefValue)v).getAddr();
            if (hp.isDefined(address)) {
                return hp.lookup(address);
            } else throw new MyException("Address is not defined in the heap");
        } else {
            throw new MyException("The expression is not a RefType.");
        }
    }

    public String toString() {
        return "rH(" + e.toString() + ")";
    }

    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ = e.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft = (RefType) typ;
            return reft.getInner();
        } else
            throw new MyException("The rH argument is not a Ref Type.");
    }
}
