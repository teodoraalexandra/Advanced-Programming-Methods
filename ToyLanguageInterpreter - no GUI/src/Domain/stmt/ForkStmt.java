package Domain.stmt;
import Domain.adt.*;
import Domain.types.BoolType;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;
import Domain.PrgState;

import java.io.BufferedReader;

public class ForkStmt implements IStmt {
    private IStmt statement;

    public ForkStmt(IStmt statemet) {
        this.statement = statemet;
    }

    public String toString() { return statement.toString(); }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> exeStack = new MyStack<IStmt>();
        exeStack.push(statement);

        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<String, Value> clonedSymTable = null;
        try {
            clonedSymTable = (MyIDictionary<String, Value>) symTable.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        MyIList<Value> out = state.getOut();
        MyIFDictionary<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heap = state.getHeap();
        MyIDictionary<String, Type> typeMyIDictionary = state.getTypeMyIDictionary();

        return new PrgState(exeStack,
                clonedSymTable,
                out, fileTable,
                heap,
                typeMyIDictionary);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return statement.typecheck(typeEnv);
    }
}
