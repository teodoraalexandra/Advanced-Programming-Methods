
package GUI;

import Domain.adt.*;
import Domain.exp.*;
import Domain.stmt.*;
import Domain.types.*;
import Domain.values.BoolValue;
import Domain.values.IntValue;
import Domain.values.StringValue;
import GUI.WindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Exception.MyException;

public class IStmtController {
    @FXML
    private ListView<IStmt> listView;

    private IStmt selectedPrg;

    @FXML
    public void initialize() {
        listView.setItems(getIStmtList());
        // To set selection model
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Select item at index = 1
        listView.getSelectionModel().selectIndices(0);

        // Focus - this is a blue rectangle around the field
        //listView.getFocusModel().focus(2);
    }

    private ObservableList<IStmt> getIStmtList() {


/*----------PROGRAM 1----------EXAMPLE 1 ASSIGNMENT 2*/

        IStmt originalProgram1 = new CompStmt(new VarDeclStmt(new IntType(), "v"),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));


/*----------PROGRAM 2----------EXAMPLE 2 ASSIGNMENT 2*/

        IStmt originalProgram2 = new CompStmt(new VarDeclStmt(new IntType(), "v"),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(3))),
                        new PrintStmt(new VarExp("v"))));


/*----------PROGRAM 3----------EXAMPLE 3 ASSIGNMENT 2*/

        IStmt originalProgram3 = new CompStmt(new VarDeclStmt(new BoolType(), "a"),
                new CompStmt(new VarDeclStmt(new IntType(), "v"),
                        new CompStmt(new AssignStmt("a",
                                new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));


/*----------PROGRAM 4----------EXAMPLE WITH FILES*/

        IStmt originalProgram4 = new CompStmt(new VarDeclStmt(new StringType(), "varf"),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("/Users/teodoradan/IdeaProjects/MapLab4.0/src/test.in"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt(new IntType(), "varc"),
                                        new CompStmt(new ReadFile(new VarExp("varf"), new VarExp("varc")),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), new VarExp("varc")),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));


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


/*----------PROGRAM 6----------EXAMPLE WITH HEAP*/

        IStmt originalProgram6 = new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v"),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt(new RefType(new RefType(new IntType())), "a"),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));


/*----------PROGRAM 7----------EXAMPLE WITH HEAP READING*/

        IStmt originalProgram7 = new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v"),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt(new RefType(new RefType(new IntType())), "a"),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new HeapReadingExp(new HeapReadingExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));


/*----------PROGRAM 8----------EXAMPLE WITH HEAP WRITING*/

        IStmt originalProgram8 = new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v"),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                new CompStmt(new HeapWritingStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new HeapReadingExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));


/*----------PROGRAM 9----------EXAMPLE WITH GARBAGE COLLECTOR*/

        IStmt originalProgram9 = new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v"),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt(new RefType(new RefType(new IntType())), "a"),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a")))))))));


/*----------PROGRAM 10----------EXAMPLE WITH WHILE*/

        IStmt originalProgram10 = new CompStmt(new VarDeclStmt(new IntType(), "v"),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(
                                new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v",
                                                new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));


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


/*----------PROGRAM 12----------EXAMPLE FOR TYPE CHECKER*/

        IStmt originalProgram12 = new CompStmt(new VarDeclStmt(new StringType(), "v"),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));


/*----------PROGRAM 13----------MULTIPLE FORKS-> FORK AT THE END*/


        IStmt originalProgram13 = new CompStmt(new VarDeclStmt(new IntType(), "v"),
                new CompStmt(new VarDeclStmt(new IntType(), "a"),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(
                                                new ForkStmt(
                                                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(30))),
                                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                                new CompStmt(new PrintStmt(new VarExp("a")),
                                                                                        new ForkStmt(
                                                                                                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(11))),
                                                                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(42))),
                                                                                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                                                                                        new PrintStmt(new VarExp("a"))))))))))),

                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a"))))))));


/*----------PROGRAM 14----------MULTIPLE FORKS-> FORK AT THE BEGINNING*/


        IStmt originalProgram14= new CompStmt(new VarDeclStmt(new IntType(), "v"),
                new CompStmt(new VarDeclStmt(new IntType(), "a"),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(
                                                new ForkStmt( new CompStmt(
                                                        new ForkStmt(
                                                                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(11))),
                                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(42))),
                                                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                                                        new PrintStmt(new VarExp("a")))))),
                                                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(30))),
                                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                                new PrintStmt(new VarExp("a"))))))),

                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a"))))))));

        /*----------PROGRAM 15----------REPEAT ... UNTIL ... */
        IStmt originalProgram15 = new CompStmt(new VarDeclStmt(new IntType(), "v"), new CompStmt(new VarDeclStmt(new IntType(), "w"),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))), new CompStmt(new AssignStmt(
                        "w", new ValueExp(new IntValue(0))), new CompStmt(new RepUntilStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                        new CompStmt(new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))),
                                new IfStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), "=="), new AssignStmt("w", new ValueExp(new IntValue(0))),
                                        new AssignStmt("w", new ValueExp(new IntValue(1)))))), new RelationalExp(new VarExp("w"), new ValueExp(new IntValue(0)), "==")), new NopStmt())))));

        /*----------PROGRAM 16----------REPEAT ... UNTIL ... */
        IStmt originalProgram16 = new CompStmt(new VarDeclStmt(new IntType(), "v"),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(
                                new RepUntilStmt(
                                        new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v",
                                                new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1))))),
                                        new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), "==")),
                                new PrintStmt(new VarExp("v")))));

        /*----------PROGRAM 17----------Barrier - Await */
        IStmt originalProgram17 = new CompStmt(new VarDeclStmt(new IntType(), "cnt"),
                new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v1"),
                        new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v2"),
                                new CompStmt(new VarDeclStmt(new RefType(new IntType()), "v3"),
                                        new CompStmt(
                                                new NewStmt("v1", new ValueExp(new IntValue(2))),
                                                new CompStmt(new NewStmt("v2", new ValueExp(new IntValue(3))),
                                                        new CompStmt(new NewStmt("v3", new ValueExp(new IntValue(4))),
                                                                new CompStmt(new NewBarrier("cnt", new HeapReadingExp(new VarExp("v2"))),
                                                                        new CompStmt(new ForkStmt(
                                                                                new CompStmt(new Await("cnt"),
                                                                                        new CompStmt(new HeapWritingStmt("v1", new ArithExp('*', new HeapReadingExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                                                                                                new PrintStmt(new HeapReadingExp(new VarExp("v1")))))
                                                                        ),
                                                                                new CompStmt(new ForkStmt(new CompStmt(new Await("cnt"),
                                                                                        new CompStmt(new HeapWritingStmt("v2", new ArithExp('*', new HeapReadingExp(new VarExp("v2")), new ValueExp(new IntValue(10)))),
                                                                                                new CompStmt(new HeapWritingStmt("v2", new ArithExp('*', new HeapReadingExp(new VarExp("v2")), new ValueExp(new IntValue(10)))),
                                                                                                        new PrintStmt(new HeapReadingExp(new VarExp("v2"))))))),
                                                                                        new CompStmt(new Await("cnt"),
                                                                                                new PrintStmt(new HeapReadingExp(new VarExp("v3"))))))))))))));

        /*----------PROGRAM 18----------Barrier - Await */
        IStmt repeat = new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1)))));

        Exp until = new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), "==");
        Exp not_until = new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), "!=");

        IStmt program18 = new CompStmt(new VarDeclStmt(new IntType(), "v"),
                new CompStmt(new VarDeclStmt(new IntType(), "x"),
                        new CompStmt(new VarDeclStmt(new IntType(), "y"),
                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))),
                                        new CompStmt(new RepUntilStmt(repeat, until),
                                                new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))),
                                                        new CompStmt(new NopStmt(),
                                                                new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(3))),
                                                                        new CompStmt(new NopStmt(),
                                                                                new PrintStmt(new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10)))))))))))));

        // To Creating a Observable List
        return FXCollections.observableArrayList(originalProgram1, originalProgram2, originalProgram3,
                originalProgram4, originalProgram5, originalProgram6,
                originalProgram7, originalProgram8, originalProgram9,
                originalProgram10, originalProgram11, originalProgram12,
                originalProgram13, originalProgram14, originalProgram15,
                originalProgram16, originalProgram17, program18);
    }

    public IStmt getSelectedPrg() {
        return selectedPrg;
    }

    @FXML
    public void newpage() throws MyException {
        try {
            selectedPrg = listView.getSelectionModel().getSelectedItem();
            MyIDictionary<String, Type> typeMyIDictionary1 = new MyDictionary<>();
            selectedPrg.typecheck(typeMyIDictionary1);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainWindow.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,880,600);

            scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());

            WindowController controller = loader.getController();
            controller.initData(selectedPrg);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            Alert b = new Alert(Alert.AlertType.NONE);
            // set alert type
            b.setAlertType(Alert.AlertType.ERROR);
            // set alert message
            b.setContentText("TypeChecker. Program can not be added.");
            // show the dialog
            b.show();
        }
    }
}