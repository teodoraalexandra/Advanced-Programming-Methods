package Repository;
import Model.ITransport;
import Exception.MyException;

public class MyRepo implements Repository {
    private ITransport[] array;
    private int crtidx;

    public MyRepo(int capacity) {
        array = new ITransport[capacity];
        crtidx = 0;
    }

    public int getLength() {
        return crtidx;
    }

    public void addToRepo(ITransport object) throws MyException {
        if (crtidx >= array.length) throw new MyException("Max capacity.");
        else {
            array[crtidx] = object;
            crtidx += 1;
            System.out.println("We've added: " + object.toString());
        }
    }

    public void deleteFromRepo(int idx) throws MyException {
        if (idx >= 0 && idx < crtidx) {
            for (int i = idx; i < crtidx; i++)
                array[i] = array[i+1];
            crtidx--;
            System.out.println("We've deleted at index: " + idx);
        } else {
            throw new MyException("Invalid index");
        }
    }

    public ITransport[] getAll() {
        ITransport[] copy = new ITransport[crtidx];
        for(int i = 0; i < crtidx; i++) {
            copy[i] = array[i];
        }
        return copy;
    }
}
