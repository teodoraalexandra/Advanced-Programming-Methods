package Domain.stmt;

import Domain.PrgState;
import Domain.adt.*;
import Domain.types.*;
import Domain.values.RefValue;
import Exception.MyException;
import Domain.exp.Exp;
import Domain.values.Value;

public class NewStmt implements IStmt{
    private String var_name;
    private Exp exp;

    public NewStmt(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    public String toString() {
        return "new(" + var_name + ", " + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        IHeap<Integer, Value> heap = state.getHeap();
        if (symTbl.isDefined(var_name)) {
            Type typeOfVarName = (symTbl.lookup(var_name)).getType();
            Type refType = new RefType(new IntType());
            if (typeOfVarName.getType().equals(refType.getType())) {
                Value val = exp.eval(symTbl, heap);
                if (val.getType().equals(((RefValue) symTbl.lookup(var_name)).getLocationType())) {
                    int newLocation = heap.getIndex().get();
                    //int newLocation = heap.getNewFreeAddress();
                    heap.add(newLocation, val);
                    Value newRefValue = new RefValue(newLocation, ((RefValue) symTbl.lookup(var_name)).getLocationType());
                    symTbl.update(var_name, newRefValue);
                }
                else throw new MyException("The type of the value and the location Type from the value associated to var_name are not equal.");
            }
            else
                throw new MyException("The variable " + var_name + " is not a RefType.");
        } else throw new MyException("The used variable " + var_name + " was not declared before.");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }
}
