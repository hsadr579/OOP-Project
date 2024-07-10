package core;

public enum Menus {
    SIGN_UP("Entrance Menu"),
    PROFILE("Profile Menu"), SHOP("Shop"),
    ADMIN("Admin Menu"), HISTORY("History"),
    MODE("Select Mode Of Game"),
    MULTI_PLAYER_MODE_LOGIN("Player Two Login"),
    MULTI_PLAYER_MODE_CHARACTER("Select Character"),
    MULTI_PLAYER_MODE_GAME("Multi Player Game"),
    SINGLE_PLAYER_MODE_GAME("Single Player Game"),
    SINGLE_PLAYER_MODE_CHARACTER("Select Character"),
    MAIN("Main Menu"), MY_CLAN("Your Clan"),
    JOIN_CLAN("Join Clan Page"),
    CREATE_CLAN("Create Clan"),
    SECURITY_QUESTION("Enter With Security Question"), WIN("You are the winner"), BET_COST("Bet cost");

    private final String pattern;

    Menus(String pattern) {
        this.pattern = pattern;
    }

    public String get() {
        return pattern;
    }
}
