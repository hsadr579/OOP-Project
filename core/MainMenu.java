package core;

public class MainMenu {
    public static void startGame() {
        Session.getInstance().setCurrentMenu(Menus.MODE);
        Session.getInstance().setOutput(Outputs.SUCCESS_START_GAME);
    }

    public static void showMyCards() {
        String[] usersCards = DB.getUserCardsIDs(Session.getInstance().getLoggedUser());
        int j = 0;
        String output = "";
        for (String i : usersCards) {

            j++;
            output += j + DB.getUserCardByID(Session.getInstance().getLoggedUser(), i).toStringUpgrade() + "\n";

        }
        Session.getInstance().setOutput(output);
    }

    public static void hist() {
        Session.getInstance().setCurrentMenu(Menus.HISTORY);

    }

    public static void goProfile() {
        Session.getInstance().setCurrentMenu(Menus.PROFILE);
    }
}
