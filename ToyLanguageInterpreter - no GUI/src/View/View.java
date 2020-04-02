package View;

import Controller.Controller;
import Domain.PrgState;
import Domain.stmt.*;
import Domain.types.*;
import Domain.values.*;
import Domain.exp.*;
import Domain.adt.*;
import Exception.MyException;
import Repository.IRepository;
import Repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;

public class View {
    public static void main(String[] args) throws MyException, IOException {
        try {
            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand(0, "exit"));

            /*----------PROGRAM 1----------EXAMPLE 1 ASSIGNMENT 2*/
            IStmt originalProgram1 = new CompStmt(new VarDeclStmt(new IntType(), "v"),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                            new PrintStmt(new VarExp("v"))));

            MyIStack<IStmt> exeStack1 = new MyStack<>();
            MyIDictionary<String, Value> symTable1 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary1 = new MyDictionary<>();
            MyIList<Value> out1 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable1 = new MyFDictionary<>();
            IHeap<Integer, Value> heap1 = new Heap<>();

            try {
                originalProgram1.typecheck(typeMyIDictionary1);

                PrgState myPrgState1 = new PrgState(exeStack1, symTable1, out1, fileTable1, heap1, typeMyIDictionary1);
                originalProgram1.execute(myPrgState1);

                IRepository repoEx1 = new Repository(myPrgState1, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile1");
                Controller myControllerEx1 = new Controller(repoEx1);

                menu.addCommand(new RunExample(1, originalProgram1.toString(), myControllerEx1));
            } catch (MyException e) {
                System.out.print("Program 1 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 2----------EXAMPLE 2 ASSIGNMENT 2*/
            IStmt originalProgram2 = new CompStmt(new VarDeclStmt(new IntType(), "a"),
                    new CompStmt(new VarDeclStmt(new IntType(), "b"),
                            new CompStmt(new AssignStmt(
                                    "a",
                                    new ArithExp('+',
                                            new ValueExp(new IntValue(2)),
                                            new ArithExp('*',
                                                    new ValueExp(new IntValue(3)),
                                                    new ValueExp(new IntValue(5))))),
                                    new CompStmt(new AssignStmt(
                                            "b",
                                            new ArithExp('+',
                                                    new VarExp("a"),
                                                    new ValueExp(new IntValue(1)))),
                                            new PrintStmt(new VarExp("b"))))));

            MyIStack<IStmt> exeStack2 = new MyStack<>();
            MyIDictionary<String, Value> symTable2 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary2 = new MyDictionary<>();
            MyIList<Value> out2 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable2 = new MyFDictionary<>();
            IHeap<Integer, Value> heap2 = new Heap<>();

            try {
                originalProgram2.typecheck(typeMyIDictionary2);

                PrgState myPrgState2 = new PrgState(exeStack2, symTable2, out2, fileTable2, heap2, typeMyIDictionary2);
                originalProgram2.execute(myPrgState2);

                IRepository repoEx2 = new Repository(myPrgState2, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile2");
                Controller myControllerEx2 = new Controller(repoEx2);

                menu.addCommand(new RunExample(2, originalProgram2.toString(), myControllerEx2));
            } catch (MyException e) {
                System.out.print("Program 2 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 3----------EXAMPLE 3 ASSIGNMENT 2*/
            IStmt originalProgram3 = new CompStmt(new VarDeclStmt(new BoolType(), "a"),
                    new CompStmt(new VarDeclStmt(new IntType(), "v"),
                            new CompStmt(new AssignStmt("a",
                                    new ValueExp(new BoolValue(true))),
                                    new CompStmt(new IfStmt(new VarExp("a"),
                                            new AssignStmt("v", new ValueExp(new IntValue(2))),
                                            new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                            new PrintStmt(new VarExp("v"))))));

            MyIStack<IStmt> exeStack3 = new MyStack<>();
            MyIDictionary<String, Value> symTable3 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary3 = new MyDictionary<>();
            MyIList<Value> out3 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable3 = new MyFDictionary<>();
            IHeap<Integer, Value> heap3 = new Heap<>();

            try {
                originalProgram3.typecheck(typeMyIDictionary3);

                PrgState myPrgState3 = new PrgState(exeStack3, symTable3, out3, fileTable3, heap3, typeMyIDictionary3);
                originalProgram3.execute(myPrgState3);

                IRepository repoEx3 = new Repository(myPrgState3, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile3");
                Controller myControllerEx3 = new Controller(repoEx3);

                menu.addCommand(new RunExample(3, originalProgram3.toString(), myControllerEx3));
            } catch (MyException e) {
                System.out.print("Program 3 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 4----------EXAMPLE WITH FILES*/
            IStmt file_program = new CompStmt(new VarDeclStmt(new StringType(), "varf"),
                    new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("/Users/teodoradan/IdeaProjects/MapLab4.0/src/test.in"))),
                            new CompStmt(new OpenRFile(new VarExp("varf")),
                                    new CompStmt(new VarDeclStmt(new IntType(), "varc"),
                                            new CompStmt(new ReadFile(new VarExp("varf"), new VarExp("varc")),
                                                    new CompStmt(new PrintStmt(new VarExp("varc")),
                                                            new CompStmt(new ReadFile(new VarExp("varf"), new VarExp("varc")),
                                                                    new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                            new CloseRFile(new VarExp("varf"))))))))));

            MyIStack<IStmt> exeStack4 = new MyStack<>();
            MyIDictionary<String, Value> symTable4 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary4 = new MyDictionary<>();
            MyIList<Value> out4 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable4 = new MyFDictionary<>();
            IHeap<Integer, Value> heap4 = new Heap<>();

            try {
                file_program.typecheck(typeMyIDictionary4);

                PrgState myPrgState4 = new PrgState(exeStack4, symTable4, out4, fileTable4, heap4, typeMyIDictionary4);
                file_program.execute(myPrgState4);

                IRepository repoEx4 = new Repository(myPrgState4, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile4");
                Controller myControllerEx4 = new Controller(repoEx4);

                menu.addCommand(new RunExample(4, file_program.toString(), myControllerEx4));
            } catch (MyException e) {
                System.out.print("Program 4 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 5----------EXAMPLE WITH RELATIONAL EXPRESSION*/
            IStmt originalProgram5 = new CompStmt(new VarDeclStmt(new IntType(), "a"),
                    new CompStmt(new VarDeclStmt(new IntType(), "b"),
                            new CompStmt(new VarDeclStmt(new BoolType(), "ok"),
                                    new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(10))),
                                            new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(5))),
                                                    new CompStmt(new IfStmt(new RelationalExp(new VarExp("a"), new VarExp("b"), ">="),
                                                            new AssignStmt("ok", new ValueExp(new BoolValue(true))),
                                                            new AssignStmt("ok", new ValueExp(new BoolValue(false)))),
                                                            new PrintStmt(new VarExp("ok"))))))));

            MyIStack<IStmt> exeStack5 = new MyStack<>();
            MyIDictionary<String, Value> symTable5 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary5 = new MyDictionary<>();
            MyIList<Value> out5 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable5 = new MyFDictionary<>();
            IHeap<Integer, Value> heap5 = new Heap<>();

            try {
                originalProgram5.typecheck(typeMyIDictionary5);

                PrgState myPrgState5 = new PrgState(exeStack5, symTable5, out5, fileTable5, heap5, typeMyIDictionary5);
                originalProgram5.execute(myPrgState5);

                IRepository repoEx5 = new Repository(myPrgState5, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile5");
                Controller myControllerEx5 = new Controller(repoEx5);

                menu.addCommand(new RunExample(5, originalProgram5.toString(), myControllerEx5));
            } catch (MyException e) {
                System.out.print("Program 5 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 6----------EXAMPLE WITH HEAP*/
            IStmt originalProgram6 = new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v"),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt(new RefType(new RefType(new IntType())), "a"),
                                    new CompStmt(new NewStmt("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new VarExp("v")),
                                                    new PrintStmt(new VarExp("a")))))));

            MyIStack<IStmt> exeStack6 = new MyStack<>();
            MyIDictionary<String, Value> symTable6 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary6 = new MyDictionary<>();
            MyIList<Value> out6 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable6 = new MyFDictionary<>();
            IHeap<Integer, Value> heap6 = new Heap<>();

            try {
                originalProgram6.typecheck(typeMyIDictionary6);

                PrgState myPrgState6 = new PrgState(exeStack6, symTable6, out6, fileTable6, heap6, typeMyIDictionary6);
                originalProgram6.execute(myPrgState6);

                IRepository repoEx6 = new Repository(myPrgState6, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile6");
                Controller myControllerEx6 = new Controller(repoEx6);

                menu.addCommand(new RunExample(6, originalProgram6.toString(), myControllerEx6));
            } catch (MyException e) {
                System.out.print("Program 6 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 7----------EXAMPLE WITH HEAP READING*/
            IStmt originalProgram7 = new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v"),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt(new RefType(new RefType(new IntType())), "a"),
                                    new CompStmt(new NewStmt("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                                    new PrintStmt(new ArithExp('+', new HeapReadingExp(new HeapReadingExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));

            MyIStack<IStmt> exeStack7 = new MyStack<>();
            MyIDictionary<String, Value> symTable7 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary7 = new MyDictionary<>();
            MyIList<Value> out7 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable7 = new MyFDictionary<>();
            IHeap<Integer, Value> heap7 = new Heap<>();

            try {
                originalProgram7.typecheck(typeMyIDictionary7);

                PrgState myPrgState7 = new PrgState(exeStack7, symTable7, out7, fileTable7, heap7, typeMyIDictionary7);
                originalProgram7.execute(myPrgState7);

                IRepository repoEx7 = new Repository(myPrgState7, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile7");
                Controller myControllerEx7 = new Controller(repoEx7);

                menu.addCommand(new RunExample(7, originalProgram7.toString(), myControllerEx7));
            } catch (MyException e) {
                System.out.print("Program 7 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 8----------EXAMPLE WITH HEAP WRITING*/
            IStmt originalProgram8 = new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v"),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                    new CompStmt(new HeapWritingStmt("v", new ValueExp(new IntValue(30))),
                                            new PrintStmt(new ArithExp('+', new HeapReadingExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));

            MyIStack<IStmt> exeStack8 = new MyStack<>();
            MyIDictionary<String, Value> symTable8 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary8 = new MyDictionary<>();
            MyIList<Value> out8 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable8 = new MyFDictionary<>();
            IHeap<Integer, Value> heap8 = new Heap<>();

            try {
                originalProgram8.typecheck(typeMyIDictionary8);

                PrgState myPrgState8 = new PrgState(exeStack8, symTable8, out8, fileTable8, heap8, typeMyIDictionary8);
                originalProgram8.execute(myPrgState8);

                IRepository repoEx8 = new Repository(myPrgState8, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile8");
                Controller myControllerEx8 = new Controller(repoEx8);

                menu.addCommand(new RunExample(8, originalProgram8.toString(), myControllerEx8));
            } catch (MyException e) {
                System.out.print("Program 8 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 9----------EXAMPLE WITH GARBAGE COLLECTOR*/
            IStmt originalProgram9 = new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v"),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt(new RefType(new RefType(new IntType())), "a"),
                                    new CompStmt(new NewStmt("a", new VarExp("v")),
                                            new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                    new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a")))))))));


            MyIStack<IStmt> exeStack9 = new MyStack<>();
            MyIDictionary<String, Value> symTable9 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary9 = new MyDictionary<>();
            MyIList<Value> out9 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable9 = new MyFDictionary<>();
            IHeap<Integer, Value> heap9 = new Heap<>();

            try {
                originalProgram9.typecheck(typeMyIDictionary9);

                PrgState myPrgState9 = new PrgState(exeStack9, symTable9, out9, fileTable9, heap9, typeMyIDictionary9);
                originalProgram9.execute(myPrgState9);

                IRepository repoEx9 = new Repository(myPrgState9, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile9");
                Controller myControllerEx9 = new Controller(repoEx9);

                menu.addCommand(new RunExample(9, originalProgram9.toString(), myControllerEx9));
            } catch (MyException e) {
                System.out.print("Program 9 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 10----------EXAMPLE WITH WHILE*/
            IStmt originalProgram10 = new CompStmt(new VarDeclStmt(new IntType(), "v"),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                            new CompStmt(
                                    new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                            new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v",
                                                    new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                    new PrintStmt(new VarExp("v")))));


            MyIStack<IStmt> exeStack10 = new MyStack<>();
            MyIDictionary<String, Value> symTable10 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary10 = new MyDictionary<>();
            MyIList<Value> out10 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable10 = new MyFDictionary<>();
            IHeap<Integer, Value> heap10 = new Heap<>();

            try {
                originalProgram10.typecheck(typeMyIDictionary10);

                PrgState myPrgState10 = new PrgState(exeStack10, symTable10, out10, fileTable10, heap10, typeMyIDictionary10);
                originalProgram10.execute(myPrgState10);

                IRepository repoEx10 = new Repository(myPrgState10, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile10");
                Controller myControllerEx10 = new Controller(repoEx10);

                menu.addCommand(new RunExample(10, originalProgram10.toString(), myControllerEx10));
            } catch (MyException e) {
                System.out.print("Program 10 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 11----------EXAMPLE WITH FORK*/
            IStmt originalProgram11 = new CompStmt(new VarDeclStmt(new IntType(), "v"),
                    new CompStmt(new VarDeclStmt(new RefType(new IntType()), "a"),
                            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                    new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                            new CompStmt(
                                                    new ForkStmt(
                                                            new CompStmt(new HeapWritingStmt("a", new ValueExp(new IntValue(30))),
                                                                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                            new CompStmt(new PrintStmt(new VarExp("v")),
                                                                                    new PrintStmt(new HeapReadingExp(new VarExp("a"))))))),
                                                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a")))))))));

            MyIStack<IStmt> exeStack11 = new MyStack<>();
            MyIDictionary<String, Value> symTable11 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary11 = new MyDictionary<>();
            MyIList<Value> out11 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable11 = new MyFDictionary<>();
            IHeap<Integer, Value> heap11 = new Heap<>();

            try {
                originalProgram11.typecheck(typeMyIDictionary11);

                PrgState myPrgState11 = new PrgState(exeStack11, symTable11, out11, fileTable11, heap11, typeMyIDictionary11);
                originalProgram11.execute(myPrgState11);

                IRepository repoEx11 = new Repository(myPrgState11, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile11");
                Controller myControllerEx11 = new Controller(repoEx11);

                menu.addCommand(new RunExample(11, originalProgram11.toString(), myControllerEx11));
            } catch (MyException e) {
                System.out.print("Program 11 was not added because -> ");
                System.out.println(e.getMessage());
            }

            /*----------PROGRAM 12----------EXAMPLE FOR TYPE CHECKER*/
            IStmt originalProgram12 = new CompStmt(new VarDeclStmt(new StringType(), "v"),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                            new PrintStmt(new VarExp("v"))));

            MyIStack<IStmt> exeStack12 = new MyStack<>();
            MyIDictionary<String, Value> symTable12 = new MyDictionary<>();
            MyIDictionary<String, Type> typeMyIDictionary12 = new MyDictionary<>();
            MyIList<Value> out12 = new MyList<>();
            MyIFDictionary<String, BufferedReader> fileTable12 = new MyFDictionary<>();
            IHeap<Integer, Value> heap12 = new Heap<>();
            try {
                originalProgram12.typecheck(typeMyIDictionary12);

                PrgState myPrgState12 = new PrgState(exeStack12, symTable12, out12, fileTable12, heap12, typeMyIDictionary12);
                originalProgram12.execute(myPrgState12);

                IRepository repoEx12 = new Repository(myPrgState12, "/Users/teodoradan/IdeaProjects/MapLab4.0/src/repoLogFile12");
                Controller myControllerEx12 = new Controller(repoEx12);

                menu.addCommand(new RunExample(12, originalProgram12.toString(), myControllerEx12));
            } catch (MyException e) {
                System.out.print("Program 12 was not added because -> ");
                System.out.println(e.getMessage());
            }

            menu.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
