import core.DB;

public class Main {
    public static void main(String[] args) {
        DB.createTables();
        Console console = new Console();
        console.run();
    }
}
