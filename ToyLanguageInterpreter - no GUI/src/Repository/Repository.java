package Repository;

import Domain.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<PrgState> programStateList;
    private String logFilePath;

    public Repository(PrgState currentState, String logFilePath){
        this.programStateList = new ArrayList<PrgState>();
        this.programStateList.add(currentState);
        this.logFilePath = logFilePath;
    }

    @Override
    public void logPrgStateExec(PrgState prgState) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            out.println(prgState.toString());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public List<PrgState> getPrgList() {
        return programStateList;
    }

    @Override
    public void setPrgList(List<PrgState> newProgramStateList) {
        this.programStateList = newProgramStateList;
    }
}