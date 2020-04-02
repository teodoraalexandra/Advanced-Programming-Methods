package Domain.stmt;
import Domain.PrgState;
import Domain.exp.Exp;
import Domain.types.Type;
import Exception.MyException;
import Domain.adt.*;
import Domain.values.Value;

public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;

    public AssignStmt() {}
    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString() {
        return id + " = " + exp.toString();
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        IHeap<Integer, Value> heap = state.getHeap();
        Value val = exp.eval(symTbl, heap);
        if (symTbl.isDefined(id)) {
            Type typId = (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new MyException("Declared type of variable " + id + " and type of the assigned expression do not match.");
        } else throw new MyException("The used variable " + id + " was not declared before.");
            return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp)) return typeEnv;
        else
            throw new MyException
                    ("Assignment: right hand side and left hand side have different types ");
    }
}

