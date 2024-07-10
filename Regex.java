import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regex {
    END("end\\s*"),
    // $##################### user creating #####################
    CREATE_NEW_USER(
            "user\\s+create\\s+-u (?<username>[\\w\\s]+) -p (?<password>[\\w\\s@#$%&*]+) (?<passwordConfirmation>[\\w\\s@#$%&*]+) –email (?<email>[\\w\\s@\\.]+) -n (?<nickname>[\\w\\s]+)"),
    CREATE_NEW_USER_WITH_RANDOM_PASSWORD(
            "user\\s+create\\s+-u (?<username>[\\w\\s]+) -p random –email (?<email>[\\w\\s@\\.]+) -n (?<nickname>[\\w\\s]+)"),
    PROPER_USERNAME("[A-Za-z0-9]+"),
    PROPER_PASSWORD("(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9]).{8,}"),
    PROPER_EMAIL("\\w+@\\w+\\.com"),
    // $##################### security question #####################
    PICK_QUESTION("question\\s+pick -q (?<questionNumber>\\d+) -a (?<answer>\\w+) -c (?<answerConfirm>\\w+)"),
    // $##################### login #####################
    LOGIN("user\\s+login\\s+-u (?<username>[A-Za-z0-9]+) -p (?<password>[\\w\\s@#$%&*]+)"),
    FORGOT_PASSWORD("forgot\\s+my\\s+password\\s+-u (?<username>[A-Za-z0-9]+)"),
    LOGOUT("log out"),
    // $##################### profile menu #####################
    SHOW_INFORMATION("show\\s+information"),
    CHANGE_USERNAME("profile\\s+change\\s+-u (?<username>\\w+)"),
    CHANGE_NICKNAME("profile\\s+change\\s+-n (?<nickname>\\w+)"),
    CHANGE_PASSWORD("profile\\s+change\\s+password\\s+-o (?<oldPassword>[\\w@#$%&*]+) -n (?<newPassword>[\\w@#$%&*]+)"),
    CHANGE_EMAIL("profile\\s+change\\s+-e (?<email>[\\w@\\.]+)"),
    // $##################### main menu #####################
    START_GAME("start game"),
    SHOW_MY_CARDS("show my cards"),
    SHOW_HISTORY("show history"),
    GO_TO_SHOP("shop menu"),
    GO_TO_PROFILE("profile menu"),
    // $##################### shop menu #####################
    BACK("back"),
    BUY_CARD("buy card (?<id>\\d+)\\s*"),
    UPGRADE_CARD("upgrade card (?<id>\\d+)\\s*"),
    // ##################### history #####################
    SORT("sort"),
    GO_TO_NEXT_PAGE("next page"),
    GO_TO_PREVIOUS_PAGE("previous page"),
    GO_TO_PAGE_NUMBER("page number (?<number>\\d+)\\s*"),
    // ##################### admin menu #####################
    LOGIN_ADMIN("login admin (?<password>[\\w@#$%&*]+)"),
    ADD_CARD(
            "add new card (?<id>\\d+) (?<name>\\w+) (?<defence>\\d+) (?<duration>\\d+) (?<damage>\\d+) (?<upgradeLevel>\\d+) (?<upgradeCost>\\d+)\\s*"),
    EDIT_CARD("edit card (?<id>\\d+)\\s*"),
    EDIT_CARD_FIELD("card field (?<number>\\d+) to (?<new>\\w+)\\s*"),
    REMOVE_CARD("remove card (?<id>\\d+)\\s*"),
    SHOW_ALL_USERS("show all users"),
    // ##################### mode menu #####################
    SELECT_MODE("select mode number (?<number>\\d+)\\s*"),

    // ##################### multiplayer mode #####################
    LOGIN_PLAYER_TWO("login player two -u (?<username>\\w+) -p (?<password>[\\w@#$%&*]+)"),
    SELECT_CHARACTER_M("select character (?<number>\\d+) for player (?<player>\\w+)\\s*"),
    SELECT_CARD_M("select card (?<id>\\d+) player (?<player>\\w+)\\s*"),
    PLACE_CARD_M("place card (?<id>\\d+) in block (?<block>\\d+)\\s*"),
    // ##################### bet mode #####################
    
    SET_BET_COST("(?<username>\\w+) bet for (?<cost>\\d+)"),
    // ##################### single player mode #####################
    SELECT_CHARACTER("select character (?<number>\\d+)"),
    SELECT_CARD("select card (?<id>\\d+)"),
    PLACE_CARD("place card (?<id>\\d+) in block (?<block>\\d+)\\s*"),
    // ##################### clan war mode #####################
    MY_CLAN_MENU("my clan"),
    JOIN_CLAN_MENU("join clan"),
    CREATE_CLAN_MENU("create clan"),
    // ##################### my clan mode #####################
    PLAY_CLAN_WAR("play clan war"),
    START_CLAN_WAR("start clan war with clan (?<code>\\d+)"),
    // ##################### join clan mode #####################
    SHOW_ALL_CLANS("show all clans"),
    JOIN_CLAN("join clan (?<code>\\d+)\\s*"),
    // ##################### create clan mode #####################
    CREATE_CLAN("create clan with name (?<name>\\w+)");

    private final String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher result = Pattern.compile(this.pattern).matcher(input);
        if (result.matches())
            return result;
        return null;
    }

}
