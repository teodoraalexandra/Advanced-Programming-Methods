package Domain.exp;

import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;

public class VarExp implements Exp {
    private String id;

    public VarExp() {}
    public VarExp(String id) {
        this.id = id;
    }

    public Value eval(MyIDictionary<String,Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        return tbl.lookup(id);
    }

    public String toString() {return id;}
    public String getId() { return this.id; }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return typeEnv.lookup(id);
    }
}

