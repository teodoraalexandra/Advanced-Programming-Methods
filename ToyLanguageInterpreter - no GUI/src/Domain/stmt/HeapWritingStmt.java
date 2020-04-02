package Domain.stmt;
import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.exp.Exp;
import Domain.types.IntType;
import Domain.types.RefType;
import Domain.types.Type;
import Domain.values.RefValue;
import Domain.values.Value;
import Exception.MyException;
import Domain.PrgState;

public class HeapWritingStmt implements IStmt{
    private String var_name;
    private Exp expression;

    public HeapWritingStmt(String var_name, Exp expression) {
        this.var_name = var_name;
        this.expression = expression;
    }

    public String toString() {
        return "wH(" + var_name + ", " + expression.toString() + ")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        IHeap<Integer, Value> heap = state.getHeap();
        Value v1;
        v1 = expression.eval(symTbl, heap);

        if (symTbl.isDefined(var_name)) {
            Type typeOfVarName = symTbl.lookup(var_name).getType();
            Type refType = new RefType(new IntType());
            if (typeOfVarName.getType().equals(refType.getType())) {
                int address = ((RefValue)symTbl.lookup(var_name)).getAddr();
                if (heap.isDefined(address)) {
                    Type location = ((RefValue)symTbl.lookup(var_name)).getLocationType();
                    if (location.equals(v1.getType())) {
                        heap.update(address, v1);
                    } else throw new MyException("Incompatible location type");
                } else throw new MyException("Address " + address + " is not defined in heap.");
            } else throw new MyException("Variable " + var_name + " is not a refType.");
        } else throw new MyException("Variable " + var_name + " is not defined in symtbl.");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = expression.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("Heap Writing Stmt: right hand side and left hand side have different types ");
    }


}
