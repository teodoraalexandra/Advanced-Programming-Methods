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

public class OpenRFile implements IStmt {
    private Exp exp;

    public OpenRFile(Exp exp) {
        this.exp = exp;
    }

    public String toString() {
        return "openRFile(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIFDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        IHeap<Integer, Value> heap = state.getHeap();
        Value val = exp.eval(symTbl, heap);
        StringType stringValue = new StringType();
        if (val.getType().equals(stringValue)) {
            if (!fileTable.isDefined(val.toString())) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(val.toString()));
                    fileTable.add(val.toString(), reader);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else throw new MyException("The string value is already in the file table.");
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
                    ("OpenRFile: exp is not of type String");
    }
}
