package Domain.stmt;
import Domain.PrgState;
import Domain.adt.*;
import Domain.exp.Exp;
import Domain.types.BoolType;
import Domain.types.Type;
import Domain.values.BoolValue;
import Domain.values.Value;
import Exception.MyException;

public class WhileStmt implements IStmt  {
    private Exp exp;
    private IStmt statement;

    public WhileStmt(Exp exp, IStmt statement) {
        this.exp = exp;
        this.statement = statement;
    }

    public String toString() {
        return "while(" + exp.toString() + ")" + statement.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        IHeap<Integer, Value> heap = state.getHeap();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value val = exp.eval(symTable, heap);
        BoolValue trueValue = new BoolValue(true);
        if (val.getType().equals(new BoolType())) {
            BoolValue someValue = (BoolValue)val;
            if (someValue.toString().equals(trueValue.toString())) {
                stk.push(new WhileStmt(exp, statement));
                stk.push(statement);
            } else return null;
        } else throw new MyException("Condition is not a boolean type.");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        MyIDictionary<String, Type> clone = typeEnv.getDictionary();
        if (typexp.equals(new BoolType())) {
            statement.typecheck(clone);
            return typeEnv;
        } else
            throw new MyException("The condition of WHILE has not the type bool");
    }
}
