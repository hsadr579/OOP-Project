package core;

public class Session {
    private static Session instance;

    private Menus current_menu;
    private Menus previous_menu;
    private int logged_user_id = -1;
    private boolean killed = false;
    private String output = "";

    public void setOutput(Outputs o) {
        output = o.get();
    }

    public void setOutput(String s) {
        output = s;
    }

    public void back() {
        Session.getInstance().setOutput("Back to " + previous_menu.get());
        current_menu = previous_menu;
        previous_menu = null;
    }

    public String getOutput() {
        return output;
    }

    private Session() {
        current_menu = Menus.SIGN_UP;
        previous_menu = null;
    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public boolean isKilled() {
        return killed;
    }

    public Menus getCurrentMenu() {
        return current_menu;
    }

    public Menus getPreviousMenu() {
        return current_menu;
    }

    public void setCurrentMenu(Menus current_menu) {
        previous_menu = this.current_menu;
        this.current_menu = current_menu;
    }

    public int getLoggedUser() {
        return logged_user_id;
    }

    public void setLoggedUser(int logged_user) {
        this.logged_user_id = logged_user;
    }

}