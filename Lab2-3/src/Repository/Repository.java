package Repository;
import Model.ITransport;
import Exception.MyException;

public interface Repository {
    void deleteFromRepo(int idx) throws MyException;
    void addToRepo(ITransport object) throws MyException;
    int getLength();
    ITransport[] getAll();
}
