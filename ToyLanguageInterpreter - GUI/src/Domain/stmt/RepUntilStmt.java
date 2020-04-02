package Domain.stmt;

import Domain.PrgState;
import Domain.adt.MyIDictionary;
import Domain.adt.MyIStack;
import Domain.exp.Exp;
import Domain.exp.NotExpression;
import Domain.types.BoolType;
import Domain.types.Type;
import Exception.MyException;

public class RepUntilStmt implements IStmt {
    private IStmt stmt;
    private Exp exp;

    public RepUntilStmt() {}
    public RepUntilStmt(IStmt stmt, Exp exp) {
        this.stmt = stmt;
        this.exp = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack1();


        IStmt statement = new CompStmt(stmt, new WhileStmt(new NotExpression(exp), stmt));

        stk.push(statement);
        stk.push(stmt);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        MyIDictionary<String, Type> clone = typeEnv.getDictionary();
        if (typexp.equals(new BoolType())) {
            stmt.typecheck(clone);
            return typeEnv;
        } else
            throw new MyException("The condition of REPEAT has not the type bool");
    }

    public String toString() {
        return "repeat " +
                stmt.toString() + " until " +
                exp.toString();
    }
}
