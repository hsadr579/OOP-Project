import java.util.regex.Matcher;

class RegexManager {
    static String input;
    static Matcher matcher;

    public static void setInput(String i) {
        input = i;
    }

    public static boolean matches(Regex r) {
        boolean o = input.matches(r.getPattern());
        if (o) {
            setMatcher(r);
        }
        return o;
    }

    public static void setMatcher(Regex r) {
        matcher = r.getMatcher(input);
        matcher.find();
    }

    public static String get(String ID) {
        return matcher.group(ID);
    }
}

public class MenuController {
    private Menus currentMenu;
    private String command;
    private String output;

    public MenuController() {

        currentMenu = Menus.SIGN_UP;
    }

    public void input(String command) {
        this.command = command;
        RegexManager.setInput(command);
        switch (currentMenu) {
            case Menus.SIGN_UP -> signUpMenu();
            case Menus.PROFILE -> profileMenu();
            case Menus.MAIN -> mainMenu();
            case Menus.SHOP -> shopMenu();
            case Menus.ADMIN -> adminMenu();
            case Menus.HISTORY -> historyMenu();
            case Menus.MODE -> modeMenu();
            case Menus.MULTI_PLAYER_MODE_LOGIN -> multiplayerModeMenuLoginLevel();
            case Menus.MULTI_PLAYER_MODE_CHARACTER -> multiplayerModeMenuCharacterLevel();
            case Menus.MULTI_PLAYER_MODE_GAME -> multiplayerModeMenuGameLevel();
            case Menus.SINGLE_PLAYER_MODE_CHARACTER -> singlePlayerModeMenuCharacterLevel();
            case Menus.SINGLE_PLAYER_MODE_GAME -> singlePlayerModeMenuGameLevel();
            case Menus.MY_CLAN -> myClanMenu();
            case Menus.JOIN_CLAN -> joinClanModeMenu();
            case Menus.CREATE_CLAN -> createClanModeMenu();
            case Menus.SECURITY_QUESTION -> securityQuestion();

        }
    }

    public String output() {
        return output;

    }

    private void signUpMenu() {
        if (RegexManager.matches(Regex.CREATE_NEW_USER)) {
            createNewUser();
            return;
        }
        if (RegexManager.matches(Regex.CREATE_NEW_USER_WITH_RANDOM_PASSWORD)) {
            createNewUserWithRandomPassword();
            return;
        }
        if (RegexManager.matches(Regex.LOGIN)) {
            login();
            return;
        }
        if (RegexManager.matches(Regex.FORGOT_PASSWORD)) {
            forgetPassword();
            return;
        }
        if (RegexManager.matches(Regex.LOGOUT)) {
            logout();
            return;
        }
        if (RegexManager.matches(Regex.LOGIN_ADMIN)) {
            loginAdmin();
            return;
        }
    }

    private void securityQuestion() {
        if (RegexManager.matches(Regex.PICK_QUESTION)) {
            pickQuestion();
            return;
        }
    }

    private void profileMenu() {
        if (RegexManager.matches(Regex.SHOW_INFORMATION)) {
            showInfo();
            return;
        }
        if (RegexManager.matches(Regex.CHANGE_USERNAME)) {
            changeUsername();
            return;
        }
        if (RegexManager.matches(Regex.CHANGE_NICKNAME)) {
            changeNickname();
            return;
        }
        if (RegexManager.matches(Regex.CHANGE_EMAIL)) {
            changeEmail();
            return;
        }
        if (RegexManager.matches(Regex.CHANGE_PASSWORD)) {
            changePassword();
            return;
        }

    }

    private void mainMenu() {
        if (RegexManager.matches(Regex.START_GAME)) {
            startGame();
            return;
        }
        if (RegexManager.matches(Regex.SHOW_MY_CARDS)) {
            showMyCards();
            return;
        }
        if (RegexManager.matches(Regex.SHOW_HISTORY)) {
            showHistory();
            return;
        }
        if (RegexManager.matches(Regex.GO_TO_SHOP)) {
            goShopMenu();
            return;
        }
        if (RegexManager.matches(Regex.GO_TO_PROFILE)) {
            goProfileMenu();
            return;
        }
        if (RegexManager.matches(Regex.MY_CLAN_MENU)) {
            myClanMenu();
            return;
        }
        if (RegexManager.matches(Regex.JOIN_CLAN_MENU)) {
            joinClanModeMenu();
            return;
        }
        if (RegexManager.matches(Regex.CREATE_CLAN_MENU)) {
            createClanModeMenu();
            return;
        }

    }

    private void shopMenu() {
        if (RegexManager.matches(Regex.BACK)) {
            back();
            return;
        }
        if (RegexManager.matches(Regex.BUY_CARD)) {
            buyCard();
            return;
        }
        if (RegexManager.matches(Regex.UPGRADE_CARD)) {
            upgradeCard();
            return;
        }
    }

    private void modeMenu() {
        if (RegexManager.matches(Regex.SELECT_MODE)) {
            modeSelect();
            return;
        }

    }

    private void adminMenu() {

        if (RegexManager.matches(Regex.ADD_CARD)) {
            addNewCard();
            return;
        }
        if (RegexManager.matches(Regex.EDIT_CARD)) {
            editCard();
            return;
        }
        if (RegexManager.matches(Regex.EDIT_CARD_FIELD)) {
            editCardField();
            return;
        }
        if (RegexManager.matches(Regex.REMOVE_CARD)) {
            removeCard();
            return;
        }
        if (RegexManager.matches(Regex.SHOW_ALL_USERS)) {
            showAllUsers();
            return;
        }
    }

    private void historyMenu() {
        if (RegexManager.matches(Regex.SORT)) {
            sort();
            return;
        }
        if (RegexManager.matches(Regex.GO_TO_NEXT_PAGE)) {
            nextPage();
            return;
        }
        if (RegexManager.matches(Regex.GO_TO_PREVIOUS_PAGE)) {
            previousPage();
            return;
        }
        if (RegexManager.matches(Regex.GO_TO_PAGE_NUMBER)) {
            goPageNumber();
            return;
        }
    }

    private void multiplayerModeMenuLoginLevel() {
        if (RegexManager.matches(Regex.LOGIN_PLAYER_TWO)) {
            loginPlayerTwo();
            return;
        }

    }

    private void multiplayerModeMenuCharacterLevel() {

        if (RegexManager.matches(Regex.SELECT_CHARACTER_M)) {
            selectCharacterMultiplayer();
            return;
        }

    }

    private void multiplayerModeMenuGameLevel() {

        if (RegexManager.matches(Regex.SELECT_CARD_M)) {
            selectCardMultiplayer();
            return;
        }
        if (RegexManager.matches(Regex.PLACE_CARD_M)) {
            placeCardMultiplayer();
            return;
        }
    }

    private void singlePlayerModeMenuCharacterLevel() {

        if (RegexManager.matches(Regex.SELECT_CHARACTER)) {
            selectCharacterSinglePlayer();
            return;
        }

    }

    private void singlePlayerModeMenuGameLevel() {

        if (RegexManager.matches(Regex.SELECT_CARD)) {
            selectCardSinglePlayer();
            return;
        }
        if (RegexManager.matches(Regex.PLACE_CARD)) {
            placeCardSinglePlayer();
            return;
        }
    }

    private void myClanMenu() {

        if (RegexManager.matches(Regex.PLAY_CLAN_WAR)) {

            return;
        }
        if (RegexManager.matches(Regex.START_CLAN_WAR)) {

            return;
        }
    }

    private void joinClanModeMenu() {

        if (RegexManager.matches(Regex.SHOW_ALL_CLANS)) {

            return;
        }
        if (RegexManager.matches(Regex.JOIN_CLAN)) {

            return;
        }

    }

    private void createClanModeMenu() {

        if (RegexManager.matches(Regex.CREATE_CLAN)) {

            return;
        }

    }

    // ############################################################
    // #################### Connector methods #####################
    // ############################################################
    private void createNewUser() {
        String userName, password, email, nickname;
        userName = RegexManager.get("username");
        password = RegexManager.get("password");
        email = RegexManager.get("email");
        nickname = RegexManager.get("nickname");
        // call...

    }

    private void createNewUserWithRandomPassword() {
        String userName, email, nickname;
        userName = RegexManager.get("username");
        email = RegexManager.get("email");
        nickname = RegexManager.get("nickname");
        // call...
    }

    private void pickQuestion() {
        String answer, answerConf;
        int questionNumber;
        answer = RegexManager.get("answer");
        answerConf = RegexManager.get("answerConfirm");
        questionNumber = Integer.valueOf(RegexManager.get("questionNumber"));
        // call...
    }

    private void login() {
        String userName, password;
        userName = RegexManager.get("username");
        password = RegexManager.get("password");

        // call...
    }

    private void forgetPassword() {
        String userName;
        userName = RegexManager.get("username");
        // call...
    }

    private void logout() {
        // call...
    }

    private void showInfo() {
        // call...
    }

    private void changeUsername() {
        String userName;
        userName = RegexManager.get("username");
        // call...
    }

    private void changePassword() {
        String oldPass, newPass;
        oldPass = RegexManager.get("oldPassword");
        newPass = RegexManager.get("newPassword");
        // call...
    }

    private void changeNickname() {
        String nickName;
        nickName = RegexManager.get("nickname");
        // call...
    }

    private void changeEmail() {
        String email;
        email = RegexManager.get("email");
        // call...
    }

    private void startGame() {
        // call...
    }

    private void showMyCards() {
        // call...
    }

    private void showHistory() {
        // call...
    }

    private void goShopMenu() {
        // call...

    }

    private void goProfileMenu() {
        // call...
    }

    private void back() {
        // call...
    }

    private void buyCard() {
        int ID;
        ID = Integer.valueOf(RegexManager.get("id"));
        // call...
    }

    private void upgradeCard() {
        int ID;
        ID = Integer.valueOf(RegexManager.get("id"));
        // call...
    }

    private void sort() {
        // call...
    }

    private void nextPage() {
        // call...
    }

    private void previousPage() {
        // call...
    }

    private void goPageNumber() {
        int number;
        number = Integer.valueOf(RegexManager.get("number"));
        // call...
    }

    private void loginAdmin() {
        String password;
        password = RegexManager.get("password");
        // call...
    }

    private void addNewCard() {
        String name;
        int ID;
        ID = Integer.valueOf(RegexManager.get("id"));
        int defense;
        defense = Integer.valueOf(RegexManager.get("defence"));
        int duration;
        duration = Integer.valueOf(RegexManager.get("duration"));
        int damage;
        damage = Integer.valueOf(RegexManager.get("damage"));
        int upgradeLevel;
        upgradeLevel = Integer.valueOf(RegexManager.get("upgradeLevel"));
        int upgradeCost;
        upgradeCost = Integer.valueOf(RegexManager.get("upgradeCost"));
        name = RegexManager.get("name");
        // call...
    }

    private void editCard() {
        int ID;
        ID = Integer.valueOf(RegexManager.get("id"));
        // call...
    }

    private void editCardField() {
        String name;
        int number;
        number = Integer.valueOf(RegexManager.get("number"));
        String new_;
        new_ = RegexManager.get("new");
        // call...
    }

    private void removeCard() {
        int ID;
        ID = Integer.valueOf(RegexManager.get("id"));
        // call...
    }

    private void showAllUsers() {
        // call...
    }

    private void modeSelect() {
        int number;
        number = Integer.valueOf(RegexManager.get("number"));
        // call...
    }

    private void loginPlayerTwo() {
        String userName, password;
        userName = RegexManager.get("username");
        password = RegexManager.get("password");
        // call...
    }

    private void selectCharacterMultiplayer() {
        String player;
        player = RegexManager.get("player");
        int number;
        number = Integer.valueOf(RegexManager.get("number"));
        // call...
    }

    private void selectCardMultiplayer() {
        int id;
        id = Integer.valueOf(RegexManager.get("id"));
        String player;
        player = RegexManager.get("player");
        // call...
    }

    private void placeCardMultiplayer() {
        int id;
        id = Integer.valueOf(RegexManager.get("id"));
        int block;
        block = Integer.valueOf(RegexManager.get("block"));
        // call...
    }

    private void selectCharacterSinglePlayer() {

        int number;
        number = Integer.valueOf(RegexManager.get("number"));
        // call...
    }

    private void selectCardSinglePlayer() {
        int id;
        id = Integer.valueOf(RegexManager.get("id"));

        // call...
    }

    private void placeCardSinglePlayer() {
        int id;
        id = Integer.valueOf(RegexManager.get("id"));
        int block;
        block = Integer.valueOf(RegexManager.get("block"));
        // call...
    }

}