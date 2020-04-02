package Controller;
import Repository.IRepository;
import Domain.exp.*;

public class Controller {
    private IRepository repo;
    public Controller(IRepository r) {this.repo = r;}

    public String run() throws Exception {
        Exp expression = this.repo.getCurrentExp();
        int res = expression.eval();
        return Integer.toString(res);
    }
}
