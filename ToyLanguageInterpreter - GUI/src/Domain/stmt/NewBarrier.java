package Domain.stmt;

import Domain.PrgState;
import Domain.adt.IBarrier;
import Domain.adt.IHeap;
import Domain.adt.MyIDictionary;
import Domain.adt.Pair;
import Domain.exp.Exp;
import Domain.types.BoolType;
import Domain.types.IntType;
import Domain.types.StringType;
import Domain.types.Type;
import Domain.values.IntValue;
import Domain.values.Value;
import Exception.MyException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrier implements IStmt {
    private String var_name;
    private Exp exp;
    private static Lock lock = new ReentrantLock();

    public NewBarrier(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    public String toString() {
        return "newBarrier(" + var_name + exp.toString() + ")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        //evaluates the expression exp using symTable and heapTable
        MyIDictionary<String, Value> symTable = state.getSymTable1();
        IHeap<Integer, Value> heap = state.getHeap1();
        try {
            Value nr = exp.eval(symTable, heap);
            IntValue intNr = (IntValue) nr;
            Integer value = intNr.getVal(); //value will be the result of the execution

            IBarrier<Integer, Pair<Integer, List<Integer>>> barrier = state.getBarrier1();
            int newLocation = barrier.getIndex().get(); //newLocation as AtomicOperation
            List<Integer> emptyList = new ArrayList<>(); //create an empty list

            synchronized (state.getBarrier1()) { //we sync the barrier
                //add to barrier { newLocation -> (value, emptyList) }
                barrier.add(newLocation, new Pair<>(value, emptyList));

                if (symTable.isDefined(var_name)) {
                    Value newLoc = new IntValue(newLocation);
                    symTable.update(var_name, newLoc);
                } else {
                    Value newLoc = new IntValue(newLocation);
                    symTable.add(var_name, newLoc);
                    return null;
                }
            }

        } catch (MyException e) {
            System.out.println("Something went wrong -> Expression is not integer.");
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        //verify if exp has type int
        Type type = exp.typecheck(typeEnv);
        IntType intType = new IntType();
        if (type.equals(intType)) {
            //verify if var_name has type int
            Type typeVar = typeEnv.lookup(var_name);
            Type intType2 = new IntType();
            if (typeVar.equals(intType2)) return typeEnv;
            else
                throw new MyException
                        ("NewBarrier: var is not of type Int. ");
        }
        else
            throw new MyException
                    ("NewBarrier: exp is not of type Int");
    }
}
