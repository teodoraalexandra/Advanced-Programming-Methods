package Domain.stmt;
import Domain.*;
import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.adt.MyIFDictionary;
import Domain.exp.Exp;
import Domain.types.StringType;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;

import java.io.*;

public class CloseRFile implements IStmt {
    private Exp exp;

    public CloseRFile() {}
    public CloseRFile(Exp exp) {
        this.exp = exp;
    }

    public String toString() {
        return "closeRFile(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIFDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        IHeap<Integer, Value> heap = state.getHeap();
        Value val = exp.eval(symTbl, heap);
        StringType stringValue = new StringType();
        if (val.getType().equals(stringValue)) {
            BufferedReader reader = fileTable.lookup(val.toString());
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileTable.delete(val.toString());
        } else throw new MyException("Expresion " + exp + " is not String.");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = exp.typecheck(typeEnv);
        StringType stringType = new StringType();
        if (type.equals(stringType)) return typeEnv;
        else
            throw new MyException
                    ("CloseRFile: exp is not of type String ");
    }
}
