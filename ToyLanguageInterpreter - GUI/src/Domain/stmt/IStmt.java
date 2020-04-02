package Domain.stmt;
import Domain.PrgState;
import Domain.adt.MyIDictionary;
import Domain.types.Type;
import Exception.MyException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
