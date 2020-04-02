package Domain;

import Domain.adt.*;
import Domain.stmt.IStmt;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;

import java.io.BufferedReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PrgState{

    private MyIStack<IStmt> exeStack1;
    private MyIDictionary<String, Value> symTable1;
    private MyIList<Value> out1;
    private MyIFDictionary<String, BufferedReader> fileTable1;
    private IHeap<Integer, Value> heap1;
    private MyIDictionary<String, Type> typeMyIDictionary;
    private IBarrier<Integer, Pair<Integer, List<Integer>>> barrier1;
    private IStmt originalProgram;
    static private AtomicInteger id1 = new AtomicInteger(0);
    private Integer counter;

    public PrgState(MyIStack<IStmt> exeStack1,
                    MyIDictionary<String, Value> symTable1,
                    MyIList<Value> out1,
                    MyIFDictionary<String, BufferedReader> fileTable1,
                    IHeap<Integer, Value> heap1,
                    MyIDictionary<String, Type> typeMyIDictionary1,
                    IBarrier<Integer, Pair<Integer, List<Integer>>> barrier1
                    ) {
        id1.incrementAndGet();
        counter = id1.get();
        this.exeStack1 = exeStack1;
        this.symTable1 = symTable1;
        this.out1 = out1;
        this.fileTable1 = fileTable1;
        this.heap1 = heap1;
        this.typeMyIDictionary = typeMyIDictionary1;
        this.barrier1 = barrier1;
    }

    public PrgState(IStmt prg) {
        exeStack1 = new MyStack<>();
        symTable1 = new MyDictionary<>();
        out1 = new MyList<>();
        fileTable1 = new MyFDictionary<>();
        heap1 = new Heap<>();
        this.originalProgram = prg;

        exeStack1.push(originalProgram);
    }

    public MyIStack<IStmt> getExeStack1() {
        return exeStack1;
    }
    public void setExeStack1(MyIStack<IStmt> newExeStack) { this.exeStack1 = newExeStack; }

    public MyIDictionary<String, Value> getSymTable1() {
        return symTable1;
    }

    public void setSymTable1(MyIDictionary<String, Value> newSymTable) { this.symTable1 = newSymTable; }

    public MyIList<Value> getOut1() {
        return out1;
    }
    public void setOut1(MyIList<Value> newOut) { this.out1 = newOut; }

    public MyIFDictionary<String, BufferedReader> getFileTable1() { return fileTable1; }
    public void setFileTable1(MyIFDictionary<String, BufferedReader> newFileTable) { this.fileTable1 = newFileTable; }

    public IHeap<Integer, Value> getHeap1() { return heap1; }
    public void setHeap1(IHeap<Integer, Value> newHeap) { this.heap1 = newHeap; }

    public MyIDictionary<String, Type> getTypeMyIDictionary() {return typeMyIDictionary; }
    public void setTypeMyIDictionary(MyIDictionary<String, Type> newTypeMyIDictionary) { this.typeMyIDictionary = newTypeMyIDictionary; }

    public IBarrier<Integer, Pair<Integer, List<Integer>>> getBarrier1() { return barrier1; }
    public void setBarrier1(IBarrier<Integer, Pair<Integer, List<Integer>>> newBarrier) { this.barrier1 = newBarrier; }

    public Integer getCounter() { return counter; }
    public AtomicInteger getId() { return id1; }

    public Boolean isNotCompleted() {
        return !exeStack1.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if(exeStack1.isEmpty()) throw new MyException("Prg state stack is empty");
        IStmt crtStmt = exeStack1.pop();
        return crtStmt.execute(this);
    }

    public String toString() {
        return "ID" + "\n" + id1 + "\n" +
                "EXE STACK" + "\n" + exeStack1.toString() + "\n" +
                "SYMBOL TABLE" + "\n" + symTable1.toString() + "\n" +
                "OUT" + "\n" + out1.toString() + "\n" +
                "FILE TABLE" + "\n" + fileTable1.toString() + "\n" +
                "HEAP" + "\n" + heap1.toString() + "\n" +
                "BARRIER" + "\n" + barrier1.toString() + "\n";
    }

}
