package Domain;
import Domain.adt.*;
import Domain.stmt.IStmt;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;
import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicInteger;

public class PrgState{
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private MyIFDictionary<String, BufferedReader> fileTable;
    private IHeap<Integer, Value> heap;
    private MyIDictionary<String, Type> typeMyIDictionary;
    static private AtomicInteger id = new AtomicInteger(0);

    private int counter;

    public PrgState(MyIStack<IStmt> exeStack,
                    MyIDictionary<String, Value> symTable,
                    MyIList<Value> out,
                    MyIFDictionary<String, BufferedReader> fileTable,
                    IHeap<Integer, Value> heap,
                    MyIDictionary<String, Type> typeMyIDictionary) {
        id.incrementAndGet();
        counter = id.get();
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.typeMyIDictionary = typeMyIDictionary;
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }
    public void setExeStack(MyIStack<IStmt> newExeStack) { this.exeStack = newExeStack; }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }
    public void setSymTable(MyIDictionary<String, Value> newSymTable) { this.symTable = newSymTable; }

    public MyIList<Value> getOut() {
        return out;
    }
    public void setOut(MyIList<Value> newOut) { this.out = newOut; }

    public MyIFDictionary<String, BufferedReader> getFileTable() { return fileTable; }
    public void setFileTable(MyIFDictionary<String, BufferedReader> newFileTable) { this.fileTable = newFileTable; }

    public IHeap<Integer, Value> getHeap() { return heap; }
    public void setHeap(IHeap<Integer, Value> newHeap) { this.heap = newHeap; }

    public MyIDictionary<String, Type> getTypeMyIDictionary() {return typeMyIDictionary; }
    public void setTypeMyIDictionary(MyIDictionary<String, Type> newTypeMyIDictionary) { this.typeMyIDictionary = newTypeMyIDictionary; }

    public int getId() { return counter; }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if(exeStack.isEmpty()) throw new MyException("Prg state stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public String toString() {
        return "ID" + "\n" + id + "\n" +
                "EXE STACK" + "\n" + exeStack.toString() + "\n" +
                "SYMBOL TABLE" + "\n" + symTable.toString() + "\n" +
                "OUT" + "\n" + out.toString() + "\n" +
                "FILE TABLE" + "\n" + fileTable.toString() + "\n" +
                "HEAP" + "\n" + heap.toString() + "\n";
    }
}
