package Domain.exp;

import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.types.BoolType;
import Domain.types.Type;
import Domain.values.BoolValue;
import Domain.values.Value;
import Exception.MyException;

public class NotExpression implements Exp {

    private Exp exp;

    public NotExpression(Exp expression) {
        this.exp = expression;
    }

    public String toString() {
        return "!" + exp.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, IHeap<Integer, Value> hp) throws MyException {
        Value init = exp.eval(tbl, hp);
        BoolValue cond = (BoolValue) init;
        boolean finalCond = cond.getVal();

        return finalCond ? new BoolValue(false) : new BoolValue(true);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1;
        typ1 = exp.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            return new BoolType();
        } else throw new MyException("Expression is not a bool.");
    }
}