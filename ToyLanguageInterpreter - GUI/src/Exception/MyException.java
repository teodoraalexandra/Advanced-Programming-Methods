package Exception;

public class MyException extends Exception {
    // 1. exceptional situations for your 3 ADTs (Stack, Dictionary and List) operations
    // (e.g. writing into a full collection, reading from an empty collection, etc)

    //2. expressions evaluation: Division by zero, variable not defined in symbol table

    //3. statements execution: trying to execute when the execution stack is empty

    public MyException(String errorMessage) {
        super(errorMessage);
    }
}

