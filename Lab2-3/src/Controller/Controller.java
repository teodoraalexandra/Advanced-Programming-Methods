package Controller;
import Repository.Repository;
import Model.ITransport;
import Exception.MyException;

public class Controller {
    Repository repo;

    public Controller(Repository r) {
        this.repo = r;
    }

    public String filterColor(String c) {
        String sol = "";
        for (ITransport t: repo.getAll()) {
            if (t.getColor().compareTo(c) == 0) { sol += t + "\n"; }
        }
        return sol;
    }

    public void delete(int idx) throws MyException {
        repo.deleteFromRepo(idx);
    }

    public void add(ITransport object) throws MyException {
        repo.addToRepo(object);
    }
}
