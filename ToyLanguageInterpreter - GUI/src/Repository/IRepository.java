package Repository;

import Domain.PrgState;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface IRepository {
    void logPrgStateExec(PrgState prgState) throws IOException;
    List<PrgState> getPrgList();
    List<PrgState> getCopy();
    void setPrgList(List<PrgState> newProgramStateList);
    void setCopy(List<PrgState> newProgramStateList);

    PrgState getCurrentProgram();
    PrgState getProgramStateWithId(int currentId);
    void addProgramState(PrgState initialProgramState);
}