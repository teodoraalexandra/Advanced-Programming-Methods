package View;

public class ExitCommand extends Command {
    public ExitCommand(int key, String desc) {
        super(key, desc);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
