package GUI;

import Domain.PrgState;
import Domain.adt.*;
import Domain.stmt.IStmt;
import Domain.types.Type;
import Domain.values.Value;
import Controller.Controller;
import Repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import Exception.MyException;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class WindowController {
    private Controller controller;
    private PrgState myPrgState1;

    private IHeap<Integer, Value> heap1 = new Heap<>();
    private MyIList<Value> out1 = new MyList<>();
    private MyIFDictionary<String, BufferedReader> fileTable1 = new MyFDictionary<>();
    private MyIDictionary<String, Value> symTable1 = new MyDictionary<>();
    private MyIStack<IStmt> exeStack1 = new MyStack<>();
    private IBarrier<Integer, Pair<Integer, List<Integer>>> barrier1 = new Barrier<>();
    private AtomicInteger id1;

    // PRG TEXT FIELD
    @FXML
    private TextField prgTextField;

    //HEAP TABLE
    @FXML
    private TableView<HeapVar> heapTable;
    @FXML
    private TableColumn<Object, Object> address = new TableColumn<>("Key");
    @FXML
    private TableColumn<Object, Object> value = new TableColumn<>("Value");

    //BARRIER TABLE
    @FXML
    private TableView<BarrierVar> barrierTable;
    @FXML
    private TableColumn<Object, Object> bindex = new TableColumn<Object, Object>("First");
    @FXML
    private TableColumn<Object, Object> bvalue = new TableColumn<Object, Object>("Second");
    @FXML
    private TableColumn<Object, Object> blist = new TableColumn<Object, Object>("Third");

    //OUT
    @FXML
    private ListView<MyIList<Value>> out;

    //FILE TABLE
    @FXML
    private ListView<MyIFDictionary<String, BufferedReader>> fileTable;

    //SYM TABLE
    @FXML
    private TableView<SymTableVar> symTable;
    @FXML
    private TableColumn<Object, Object> varName = new TableColumn<>("Key");
    @FXML
    private TableColumn<Object, Object> symValue = new TableColumn<>("Value");

    //PRG STATE ID
    @FXML
    private ListView<Integer> prgStateId;

    //EXE STACK
    @FXML
    private ListView<IStmt> exeStack;

    private List<Integer> getProgramStateIds(List<PrgState> programStateList) {
        return programStateList.stream().map(PrgState::getCounter).collect(Collectors.toList());
    }

    private void populateProgramStateIdentifiers() {
        List<PrgState> programStates = controller.getRepository().getCopy();
        prgStateId.setItems(FXCollections.observableList(getProgramStateIds(programStates)));

        prgTextField.setText("" + programStates.size());
    }

    void initData(IStmt selectedPrg) {

        MyIDictionary<String, Type> typeMyIDictionary1 = new MyDictionary<>();

        try {
            selectedPrg.typecheck(typeMyIDictionary1);

            myPrgState1 = new PrgState(exeStack1, symTable1, out1, fileTable1, heap1, typeMyIDictionary1, barrier1);
            selectedPrg.execute(myPrgState1);
            id1 = myPrgState1.getId();

            IRepository repoEx1 = new Repository(myPrgState1, "/Users/teodoradan/IdeaProjects/JavaGUI/src/repolog");
            controller = new Controller(repoEx1);

            this.populateProgramStateIdentifiers();

            prgStateId.setOnMouseClicked(mouseEvent -> { changeProgramState(getCurrentProgramState()); });

        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeProgramState(PrgState currentProgramState) {
        if(currentProgramState == null)
            return;
        if (!currentProgramState.isNotCompleted()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing to do here!", ButtonType.OK);
            alert.showAndWait();
        }

        this.exeStack.getItems().clear();
        this.symTable.getItems().clear();
        populateExeStack(currentProgramState);
        populateSymTable(currentProgramState);
    }

    private void populateHeapTable() {
        address.setCellValueFactory(new PropertyValueFactory<>("key"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));

        Map<Integer, Value> heap_map = heap1.getContent();
        for (Map.Entry<Integer, Value> entry : heap_map.entrySet()) {
            GUI.HeapVar variable = new HeapVar(entry.getKey(), entry.getValue());
            heapTable.getItems().add(variable);
        }

        heapTable.refresh();
    }

    private void populateBarrierTable() {
        bindex.setCellValueFactory(new PropertyValueFactory<>("first"));
        bvalue.setCellValueFactory(new PropertyValueFactory<>("second"));
        blist.setCellValueFactory(new PropertyValueFactory<>("third"));

        Map<Integer, Pair<Integer, List<Integer>>> barrier_map = barrier1.getContent();
        for (Map.Entry<Integer, Pair<Integer, List<Integer>>> entry : barrier_map.entrySet()) {
            GUI.BarrierVar variable = new BarrierVar(entry.getKey(), entry.getValue().getFirst(), entry.getValue().getSecond());
            barrierTable.getItems().add(variable);
        }

        barrierTable.refresh();
    }

    private void populateOutTable() {
        out.getItems().add(out1);
        out.refresh();
    }

    private void populateFileTable() {
        fileTable.getItems().add(fileTable1);
        fileTable.refresh();
    }

    private void populateSymTable(PrgState currentPrgState) {
        MyIDictionary<String, Value> currentSymTable = currentPrgState.getSymTable1();
        varName.setCellValueFactory(new PropertyValueFactory<>("key"));
        symValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        Map<String, Value> sym_map = currentSymTable.getContent();
        for (Map.Entry<String, Value> entry : sym_map.entrySet()) {
            GUI.SymTableVar variable = new SymTableVar(entry.getKey(), entry.getValue());
            symTable.getItems().add(variable);
        }

        symTable.refresh();
    }

    private void populateExeStack(PrgState currentPrgState) {
        MyIStack<IStmt> currentExeStack = currentPrgState.getExeStack1();
        if (currentExeStack.size() == 2) {
            exeStack.getItems().add(currentExeStack.secondElement());
            exeStack.getItems().add(currentExeStack.firstElement());
        }

        if (currentExeStack.size() == 1) {
            exeStack.getItems().add(currentExeStack.firstElement());
        }
    }

    private void initTables() {
        /*INITIALIZE ALL 4 TABLES FROM WINDOW CONTROLLER - except exeStack, symTable and Identifiers*/
        this.populateHeapTable();
        this.populateOutTable();
        this.populateFileTable();
        this.populateBarrierTable();
    }

    private PrgState getCurrentProgramState(){
        if(prgStateId.getSelectionModel().getSelectedIndex() == -1)
            return null;

        int currentId = prgStateId.getSelectionModel().getSelectedItem();
        return controller.getRepository().getProgramStateWithId(currentId);
    }

    private void deleteTables() {
        /*DELETE ALL 4 TABLES FROM WINDOW CONTROLLER - except exeStack, symTable and Identifiers*/
        heapTable.getItems().clear();
        out.getItems().clear();
        fileTable.getItems().clear();
        barrierTable.getItems().clear();
    }

    // CREATE AN ALERT
    private Alert a = new Alert(Alert.AlertType.NONE);

    public void runbutton() throws InterruptedException {
        ObservableList<Integer> list = prgStateId.getSelectionModel().getSelectedItems();
        try {
            MyIStack<IStmt> currentExeStack = Objects.requireNonNull(getCurrentProgramState()).getExeStack1();
            if (exeStack1.isEmpty() && controller.getPrgListSize() == 0) {
                this.exeStack.getItems().clear();
                // set alert type
                a.setAlertType(Alert.AlertType.INFORMATION);
                // set alert message
                a.setContentText("Program finished. ExeStack is empty.");
                // show the dialog
                a.show();
            } else {
                if (list.size() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "You must select an ID!", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    this.deleteTables();
                    controller.allStep();
                    this.initTables();

                    changeProgramState(getCurrentProgramState());
                    this.populateProgramStateIdentifiers();
                }

                if (currentExeStack.isEmpty() && controller.getPrgListSize() != 0) {
                    this.exeStack.getItems().clear();
                    // set alert type
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    // set alert message
                    a.setContentText("Nothing left here.");
                    // show the dialog
                    a.show();
                }
            }
        } catch (Exception e) {
            this.exeStack.getItems().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Program finished. ExeStack is empty.", ButtonType.OK);
            alert.showAndWait();
        }
    }
}