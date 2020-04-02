package Domain.stmt;
import Domain.PrgState;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;
import Domain.adt.*;
import Domain.exp.*;

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
        MyIStack<IStmt> stk = state.getExeStack();
        IHeap<Integer, Value> heap = state.getHeap();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIList<Value> out = state.getOut();
        Value val = exp.eval(symTable, heap);
        out.add(val);
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}

