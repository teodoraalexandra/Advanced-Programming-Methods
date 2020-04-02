package Domain.stmt;

import Domain.PrgState;
import Domain.adt.MyIDictionary;
import Domain.adt.MyIStack;
import Domain.types.Type;
import Exception.MyException;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt snd;

    public CompStmt() {}
    public CompStmt(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }

    public String toString() {
        return "(" + first.toString() + "; " + snd.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack1();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        return snd.typecheck(first.typecheck(typeEnv));
    }
}
