package Domain.stmt;
import Domain.PrgState;
import Domain.adt.MyIDictionary;
import Domain.types.Type;
import Exception.MyException;

public class NopStmt implements IStmt {
    public NopStmt() {}
    public String toString() {
        return "nop";
    }

    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}
