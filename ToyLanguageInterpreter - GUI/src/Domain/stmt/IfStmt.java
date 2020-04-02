package Domain.stmt;

import Domain.PrgState;
import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.adt.MyIStack;
import Domain.exp.Exp;
import Domain.types.BoolType;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;

public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt() {}
    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    public String toString() {
        return "IF("+ exp.toString()+") THEN(" +thenS.toString()
            +")ELSE("+elseS.toString()+")";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack1();
        IHeap<Integer, Value> heap = state.getHeap1();
        MyIDictionary<String, Value> symTable = state.getSymTable1();
        Value val = exp.eval(symTable, heap);
        BoolType trueValue = new BoolType();
        if (val.getType().equals(trueValue))
            stk.push(thenS);
        else
            stk.push(elseS);
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        MyIDictionary<String, Type> clone = typeEnv.getDictionary();
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(clone);
            elseS.typecheck(clone);
            return typeEnv;
        } else
            throw new MyException("The condition of IF has not the type bool");
    }
}
