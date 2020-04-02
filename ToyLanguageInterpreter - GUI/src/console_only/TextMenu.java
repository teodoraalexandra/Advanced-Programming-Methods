package console_only;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Exception.MyException;

public class TextMenu {
    private Map<Integer, Command> commands;
    public TextMenu() {
        commands = new HashMap<>();
    }

    public void addCommand(Command c) {
        commands.put(c.getKey(),c);
    }

    private void printMenu() {
        for(Command com : commands.values()) {
            String line=String.format("%4s : %s", com.getKey(), com.getDescription()); System.out.println(line);
        }
    }

    public void show() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while(true){
            printMenu();
            System.out.print("Input the option: ");

            if(scanner.hasNextInt()){
                int key = Integer.parseInt(scanner.nextLine());
                Command com = commands.get(key);
                if (com == null) {
                    System.out.println("Invalid Option");
                    continue;
                }
                com.execute();
            } else {
                throw new IOException("The command you entered is not an integer !!!");
            }
        }
    }
}
