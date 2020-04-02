package console_only;

import Controller.Controller;

public class RunExample extends Command {
    private Controller ctr;

    public RunExample(int key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try {
            ctr.allStep();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
