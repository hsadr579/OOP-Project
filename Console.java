import java.util.Scanner;

import core.Session;

public class Console {
    Scanner scanner;
    String command;
    String output;
    MenuController menus;

    public Console() {
        scanner = new Scanner(System.in);
        menus = new MenuController();

    }

    public void printOutput() {
        System.out.println(output);
    }

    public void prompt() {
        System.out.print(Session.getInstance().getCurrentMenu().get() + ">> ");
    }

    public void run() {
        while (!Session.getInstance().isKilled()) {
            prompt();
            command = scanner.nextLine();
            menus.input(command);
            output = Session.getInstance().getOutput();
            Session.getInstance().setOutput("");
            printOutput();
        }
    }
}
