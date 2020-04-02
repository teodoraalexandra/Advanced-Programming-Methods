package Domain.stmt;

import Domain.PrgState;
import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.adt.MyIFDictionary;
import Domain.exp.Exp;
import Domain.exp.VarExp;
import Domain.types.IntType;
import Domain.types.StringType;
import Domain.types.Type;
import Domain.values.IntValue;
import Domain.values.Value;
import Exception.MyException;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt {
    private Exp exp; //path
    private VarExp variable_name; //integer

    public ReadFile(Exp exp, VarExp variable_name) {
        this.exp = exp;
        this.variable_name = variable_name;
    }

    public String toString() {
        return "readFile(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIFDictionary<String, BufferedReader> fileTable = state.getFileTable1();
        MyIDictionary<String, Value> symTbl = state.getSymTable1();
        IHeap<Integer, Value> heap = state.getHeap1();
        Value val = variable_name.eval(symTbl, heap);
        IntType intType = new IntType();
        if (val.getType().equals(intType)) {
            if (symTbl.isDefined(variable_name.toString())) {
                Value path = exp.eval(symTbl, heap);
                StringType stringType = new StringType();
                if (path.getType().equals(stringType)) {
                    BufferedReader reader = fileTable.lookup(path.toString());
                    String line;
                    int returnedValue = -1;
                    try {
                        if ((line = reader.readLine()) == null) {
                            returnedValue = 0;
                        } else {
                            returnedValue = Integer.parseInt(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    IntValue intValue = new IntValue(returnedValue);
                    symTbl.update(variable_name.toString(), intValue);
                } else throw new MyException(exp + " is not a string.");
            } else throw new MyException("The variable name is not defined in the symbol table.");
        } else throw new MyException("Variable name " + variable_name + " is not in sym table.");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1 = exp.typecheck(typeEnv);
        Type type2 = variable_name.typecheck(typeEnv);
        StringType stringType = new StringType();
        IntType intType = new IntType();
        if (type1.equals(stringType)) {
            if (type2.equals(intType))
                return typeEnv;
            else throw new MyException
                    ("ReadFile: variable_name is not of type String");
        } else { throw new MyException
                ("ReadFile: exp is not of type Int");
        }
    }
}
