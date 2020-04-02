package Repository;

import Domain.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Repository implements IRepository {

    private List<PrgState> programStateList;
    private List<PrgState> copy;

    private String logFilePath;

    public Repository(String logFilePath){
        programStateList = new ArrayList<>();
        copy = new ArrayList<>();
        this.logFilePath = logFilePath;

        PrintWriter writer;
        try{
            writer = new PrintWriter(logFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open the log file!");
        }
    }

    public Repository(List<PrgState> programStateList, String logFilePath){
        this.programStateList = programStateList;
        this.logFilePath = logFilePath;
    }

    public Repository(PrgState currentState, String logFilePath){
        this.programStateList = new ArrayList<PrgState>();
        this.programStateList.add(currentState);
        this.copy = new ArrayList<PrgState>();
        this.copy.add(currentState);
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
    public List<PrgState> getCopy() {
        return copy;
    }

    @Override
    public void setPrgList(List<PrgState> newProgramStateList) {
        this.programStateList = newProgramStateList;
    }

    @Override
    public void setCopy(List<PrgState> newProgramStateList) {
        this.copy = newProgramStateList;
    }


    @Override
    public PrgState getCurrentProgram() {
        return programStateList.get(programStateList.size()-1);
    }

    @Override
    public PrgState getProgramStateWithId(int currentId) {
        for(PrgState pr : programStateList)
            if(pr.getCounter().equals(currentId))
                return pr;
        return null;
    }

    @Override
    public void addProgramState(PrgState initialProgramState) {
        programStateList.add(initialProgramState);
        copy.add(initialProgramState);
    }
}