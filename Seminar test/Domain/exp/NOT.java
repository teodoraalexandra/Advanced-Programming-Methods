package Domain.exp;

public class NOT implements Exp {
    private Exp e;

    public NOT(Exp e) {
        this.e = e;
    }

    public int eval() throws Exception {
        int v;
        v = e.eval();

        if (v == 0) return 1;
        if (v == 1) return 0;
        return 0;
    }
}
