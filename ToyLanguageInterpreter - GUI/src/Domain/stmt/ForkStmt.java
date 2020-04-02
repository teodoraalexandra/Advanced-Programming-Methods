package Domain.stmt;

import Domain.PrgState;
import Domain.adt.*;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;

import java.io.BufferedReader;
import java.util.List;

public class ForkStmt implements IStmt {
    private IStmt statement;

    public ForkStmt(IStmt statemet) {
        this.statement = statemet;
    }

    public String toString() { return "fork(" + statement.toString() + ")"; }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> exeStack = new MyStack<IStmt>();
        exeStack.push(statement);

        MyIDictionary<String, Value> symTable = state.getSymTable1();
        MyIDictionary<String, Value> clonedSymTable = null;
        try {
            clonedSymTable = (MyIDictionary<String, Value>) symTable.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        MyIList<Value> out = state.getOut1();
        MyIFDictionary<String, BufferedReader> fileTable = state.getFileTable1();
        IHeap<Integer, Value> heap = state.getHeap1();
        MyIDictionary<String, Type> typeMyIDictionary = state.getTypeMyIDictionary();
        IBarrier<Integer, Pair<Integer, List<Integer>>> barrier = state.getBarrier1();

        return new PrgState(exeStack,
                clonedSymTable,
                out,
                fileTable,
                heap,
                typeMyIDictionary,
                barrier);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return statement.typecheck(typeEnv);
    }
}
