package Domain.exp;

import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.types.BoolType;
import Domain.types.Type;
import Domain.values.BoolValue;
import Domain.values.Value;
import Exception.MyException;

public class LogicExp implements Exp {
    private Exp e1;
    private Exp e2;
    private String op; //1-AND, 2-OR

    public LogicExp() throws MyException {}
    public LogicExp(String op, Exp e1, Exp e2) throws MyException {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    public Value eval(MyIDictionary<String,Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;
                boolean n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op.equals("AND")) return new BoolValue(n1 && n2);
                if (op.equals("OR")) return new BoolValue(n1 || n2);
            } else
                throw new MyException("Second operand is not a boolean");
        } else {
            throw new MyException("First operand is not a boolean");
        }
        return null;
    }

    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else throw new MyException("Second operand is not a bool.");
        } else throw new MyException("First operand is not a bool.");
    }
}

