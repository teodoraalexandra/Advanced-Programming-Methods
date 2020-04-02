package Domain.stmt;

import Domain.PrgState;
import Domain.adt.MyIDictionary;
import Domain.adt.MyIStack;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;

public class VarDeclStmt implements IStmt {
    private Type type;
    private String name;

    public VarDeclStmt() {}
    public VarDeclStmt(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public String toString() {
        return "(" + type.toString() + " " + name + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack1();
        MyIDictionary<String, Value> symTbl = state.getSymTable1();
        if (symTbl.isDefined(name) ) {
            throw new MyException("The used variable" + name + " was declared before");
        } else {
            if (type.getType().equals("bool")) {
                symTbl.add(name, type.defaultValue());
            } else if (type.getType().equals("int")) {
                symTbl.add(name, type.defaultValue());
            } else if (type.getType().equals("string")) {
                symTbl.add(name, type.defaultValue());
            } else if (type.getType().equals("ref")) {
                symTbl.add(name, type.defaultValue());
            } else {
                throw new MyException("Type " + type + " is neither integer, nor boolean, nor string, nor ref.");
            }
        }
        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        typeEnv.add(name, type);
        return typeEnv;
    }
}
