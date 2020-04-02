package Domain.exp;

import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;

public class ValueExp implements Exp{
    private Value e;

    public ValueExp() {}
    public ValueExp(Value e) {
        this.e = e;
    }

    public Value eval(MyIDictionary<String,Value> tbl, IHeap<Integer,Value> hp) throws MyException {return e;}
    public String toString() {return e.toString();}

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return e.getType();
    }
}
