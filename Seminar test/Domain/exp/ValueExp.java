package Domain.exp;

public class ValueExp implements Exp{
    private int e;

    public ValueExp(int e) {
        this.e = e;
    }

    public int eval() throws Exception {return e;}
    public String toString() {
        return Integer.toString(e);
    }
}
