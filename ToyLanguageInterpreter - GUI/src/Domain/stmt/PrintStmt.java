package Domain.stmt;

import Domain.PrgState;
import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.adt.MyIList;
import Domain.adt.MyIStack;
import Domain.exp.Exp;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;

public class PrintStmt implements IStmt {
    private Exp exp;
    public PrintStmt() {}
    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack1();
        IHeap<Integer, Value> heap = state.getHeap1();
        MyIDictionary<String, Value> symTable = state.getSymTable1();
        MyIList<Value> out = state.getOut1();
        Value val = exp.eval(symTable, heap);
        out.add(val);
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}

