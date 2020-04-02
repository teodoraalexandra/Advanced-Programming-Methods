package Repository;
import Domain.exp.Exp;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<Exp> expList;

    public Repository(Exp currentExp){
        this.expList = new ArrayList<>();
        this.expList.add(currentExp);
    }

    @Override
    public Exp getCurrentExp(){
        return this.expList.get(0);
    }
}
