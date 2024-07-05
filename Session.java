public class Session {
    private static Session instance;

    private String current_menu;
    private int logged_user_id = -1;

    private Session() {}

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public String getCurrentMenu() {
        return current_menu;
    }

    public void setCurrentMenu(String current_menu) {
        this.current_menu = current_menu;
    }

    public int getLoggedUser() {
        return logged_user_id;
    }

    public void setLoggedUser(int logged_user) {
        this.logged_user_id = logged_user;
    }

}