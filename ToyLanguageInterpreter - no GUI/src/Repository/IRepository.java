package Repository;
import Domain.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    void logPrgStateExec(PrgState prgState) throws IOException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newProgramStateList);
}
