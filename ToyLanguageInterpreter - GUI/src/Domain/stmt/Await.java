package Domain.stmt;

import Domain.PrgState;
import Domain.adt.MyIDictionary;
import Domain.adt.Pair;
import Domain.types.IntType;
import Domain.types.Type;
import Domain.values.IntValue;
import Domain.values.Value;
import Exception.MyException;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Await implements IStmt{
    private String var;
    private static Lock lock = new ReentrantLock();

    public Await(String var) {
        this.var = var;
    }

    public String toString() {
        return "await(" + var + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        //check if var is defined in the sym table
        boolean isFound = state.getSymTable1().isDefined(var);
        if (!isFound) return null;

        //here we found the value of the var from the SymTable
        Value index = state.getSymTable1().lookup(var);
        IntValue intValue = (IntValue) index;
        Integer foundIndex = intValue.getVal();

        //if foundIndex is not an index in the BarrierTable return null
        if (!state.getBarrier1().contains(foundIndex)) return null;

        synchronized (state.getBarrier1()) {
            //retrieve an entry for that foundIndex
            Pair<Integer, List<Integer>> entry = state.getBarrier1().get(foundIndex);
            Integer N1 = entry.getFirst();
            List<Integer> List1 = entry.getSecond();

            Integer NL = List1.size();

            if (N1 > NL) {
                if (List1.contains(state.getCounter())) {
                    state.getExeStack1().push(new Await(var));
                } else {
                    state.getBarrier1().get(foundIndex).getSecond().add(state.getCounter());
                    state.getExeStack1().push(new Await(var));
                }
            } else return null;
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        //verify if var has type int
        Type typeVar = typeEnv.lookup(var);
        Type intType = new IntType();
        if (typeVar.equals(intType)) return typeEnv;
        else
            throw new MyException
                    ("Await: var is not of type int. ");
    }
}
