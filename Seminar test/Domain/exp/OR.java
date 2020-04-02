package Domain.exp;

public class OR implements Exp {
    private Exp e1;
    private Exp e2;

    public OR(Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public int eval() throws Exception {
        int v1, v2;
        v1 = e1.eval();
        v2 = e2.eval();

        if (v1 == 1 && v2 == 1) return 1;
        if (v1 == 1 && v2 == 0) return 1;
        if (v1 == 0 && v2 == 1) return 1;
        if (v1 == 0 && v2 == 0) return 0;
        return 0;
    }
}
