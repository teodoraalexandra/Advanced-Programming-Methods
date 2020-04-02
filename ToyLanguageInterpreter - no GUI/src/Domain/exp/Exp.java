package Domain.exp;
import Domain.adt.*;
import Domain.types.Type;
import Domain.values.Value;
import Exception.MyException;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, IHeap<Integer,Value> hp) throws MyException;
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
