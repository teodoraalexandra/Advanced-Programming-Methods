package Domain.exp;

import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.types.BoolType;
import Domain.types.IntType;
import Domain.types.Type;
import Domain.values.BoolValue;
import Domain.values.IntValue;
import Domain.values.Value;
import Exception.MyException;

// exp1 < exp2
// exp1<=exp2
// exp1==exp2
// exp1!=exp2
// exp1> exp2
// exp1>=exp2
// The expressions exp1 and exp2 must be int expressions.

public class RelationalExp implements Exp {
    private Exp e1;
    private Exp e2;
    private String op;

    public RelationalExp(Exp e1, Exp e2, String op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public String toString() { return e1.toString() + op + e2.toString(); }

    public Value eval(MyIDictionary<String,Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op.equals("<")) return new BoolValue(n1 < n2);
                if (op.equals("<=")) return new BoolValue(n1 <= n2);
                if (op.equals("==")) return new BoolValue(n1 == n2);
                if (op.equals("!=")) return new BoolValue(n1 != n2);
                if (op.equals(">")) return new BoolValue(n1 > n2);
                if (op.equals(">=")) return new BoolValue(n1 >= n2);
            } else {
                throw new MyException("Second operand is not an int.");
            }
        } else {
            throw new MyException("First operand is not an int.");
        }
        return null;
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else throw new MyException("Second operand is not an integer.");
        } else throw new MyException("First operand is not an integer.");
    }
}
