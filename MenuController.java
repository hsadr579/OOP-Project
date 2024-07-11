import java.util.regex.Matcher;

import core.*;

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

    private String command;

    public MenuController() {

    }

    public void input(String command) {
        this.command = command;
        RegexManager.setInput(command);
        switch (Session.getInstance().getCurrentMenu()) {
            case SIGN_UP -> signUpMenu();
            case PROFILE -> profileMenu();
            case MAIN -> mainMenu();
            case SHOP -> shopMenu();
            case ADMIN -> adminMenu();
            case HISTORY -> historyMenu();
            case MODE -> modeMenu();
            case MULTI_PLAYER_MODE_LOGIN -> multiplayerModeMenuLoginLevel();
            case MULTI_PLAYER_MODE_CHARACTER -> multiplayerModeMenuCharacterLevel();
            case MULTI_PLAYER_MODE_GAME -> multiplayerModeMenuGameLevel();
            case SINGLE_PLAYER_MODE_CHARACTER -> singlePlayerModeMenuCharacterLevel();
            case SINGLE_PLAYER_MODE_GAME -> singlePlayerModeMenuGameLevel();
            case MY_CLAN -> myClanMenu();
            case JOIN_CLAN -> joinClanModeMenu();
            case CREATE_CLAN -> createClanModeMenu();
            case SECURITY_QUESTION -> securityQuestion();
            case WIN -> win();
            case BET_COST -> betCost();
            case CAPCHA -> capcha();
            default -> invalidCommand();

        }

    }

    private void capcha() {
        Auth.checkCapcha(command);
    }

    private void betCost() {
        if (RegexManager.matches(Regex.SET_BET_COST)) {
            setBetCost();
            return;
        }
        invalidCommand();
    }

    private void win() {
        Session.getInstance().setCurrentMenu(Menus.MAIN);
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
        invalidCommand();
    }

    private void securityQuestion() {
        if (RegexManager.matches(Regex.PICK_QUESTION)) {
            pickQuestion();
            return;
        }
        invalidCommand();
    }

    private void profileMenu() {
        if (RegexManager.matches(Regex.BACK)) {
            back();
            return;
        }
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
        invalidCommand();

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
        invalidCommand();
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
        if (RegexManager.matches(Regex.SHOW_MY_CARDS)) {
            showMyCardsShop();
        }
        invalidCommand();
    }

    private void modeMenu() {
        if (RegexManager.matches(Regex.SELECT_MODE)) {
            modeSelect();
            return;
        }
        invalidCommand();

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
        if (RegexManager.matches(Regex.LOGOUT)) {
            logout();
            return;
        }
        invalidCommand();
    }

    private void historyMenu() {
        if (RegexManager.matches(Regex.BACK)) {
            back();
            return;
        }
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
        invalidCommand();
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
        invalidCommand();

    }

    private void multiplayerModeMenuGameLevel() {

       
        if (RegexManager.matches(Regex.PLACE_CARD_M)) {
            placeCardMultiplayer();
            return;
        }
        invalidCommand();
    }

    private void singlePlayerModeMenuCharacterLevel() {

        if (RegexManager.matches(Regex.SELECT_CHARACTER)) {
            selectCharacterSinglePlayer();
            return;
        }
        invalidCommand();

    }

    private void singlePlayerModeMenuGameLevel() {

   
        if (RegexManager.matches(Regex.PLACE_CARD)) {
            placeCardSinglePlayer();
            return;
        }
        invalidCommand();
    }

    private void myClanMenu() {

        if (RegexManager.matches(Regex.PLAY_CLAN_WAR)) {

            return;
        }
        if (RegexManager.matches(Regex.START_CLAN_WAR)) {

            return;
        }
        invalidCommand();
    }

    private void joinClanModeMenu() {

        if (RegexManager.matches(Regex.SHOW_ALL_CLANS)) {

            return;
        }
        if (RegexManager.matches(Regex.JOIN_CLAN)) {

            return;
        }
        invalidCommand();

    }

    private void createClanModeMenu() {

        if (RegexManager.matches(Regex.CREATE_CLAN)) {

            return;
        }
        invalidCommand();

    }

    // ############################################################
    // #################### Connector methods #####################
    // ############################################################
    private void invalidCommand() {
        Session.getInstance().setOutput("invalid command!");
    }

    private void setBetCost() {
        String userName;
        int cost;
        userName = RegexManager.get("username");
        cost = Integer.valueOf(RegexManager.get("cost"));

        // call...
        Game.seBetCost(userName, cost);
    }

    private void createNewUser() {
        String userName, password, passwordConf, email, nickname;
        userName = RegexManager.get("username");

        passwordConf = RegexManager.get("passwordConfirmation");

        password = RegexManager.get("password");
        email = RegexManager.get("email");
        nickname = RegexManager.get("nickname");
        // call...
        Auth.preRegister(userName, password, passwordConf, nickname, email);

    }

    private void createNewUserWithRandomPassword() {
        String userName, email, nickname;
        userName = RegexManager.get("username");
        email = RegexManager.get("email");
        nickname = RegexManager.get("nickname");
        // call...
        Auth.preRegister(userName, "random", null, nickname, email);
    }

    private void pickQuestion() {
        String answer, answerConf;
        int questionNumber;
        answer = RegexManager.get("answer");
        answerConf = RegexManager.get("answerConfirm");
        questionNumber = Integer.valueOf(RegexManager.get("questionNumber"));
        // call...
        Auth.setSecurityQ(questionNumber, answer, answerConf);
    }

    private void login() {
        String userName, password;
        userName = RegexManager.get("username");
        password = RegexManager.get("password");

        // call...
        Auth.login(userName, password);
    }

    private void forgetPassword() {
        String userName;
        userName = RegexManager.get("username");
        // call...
        Auth.forgotPassword(userName);
    }

    private void logout() {
        // call...
        Auth.logout();
    }

    private void showInfo() {
        // call...
        Profile.showInfo();
    }

    private void changeUsername() {
        String userName;
        userName = RegexManager.get("username");
        // call...
        Profile.changeUsername(userName);
    }

    private void changePassword() {
        String oldPass, newPass;
        oldPass = RegexManager.get("oldPassword");
        newPass = RegexManager.get("newPassword");
        // call...
        Profile.changePassword(oldPass, newPass);
    }

    private void changeNickname() {
        String nickName;
        nickName = RegexManager.get("nickname");
        // call...
        Profile.changeNickname(nickName);
    }

    private void changeEmail() {
        String email;
        email = RegexManager.get("email");
        // call...
        Profile.changeEmail(email);
    }

    private void startGame() {
        // call...
        MainMenu.startGame();
    }

    private void showMyCards() {
        // call...
        MainMenu.showMyCards();
    }

    private void showHistory() {
        // call...
        MainMenu.hist();
    }

    private void goShopMenu() {
        // call...
        Shop.configShop();
    }

    private void goProfileMenu() {
        // call...
        MainMenu.goProfile();

    }

    private void back() {
        // call...
        Session.getInstance().back();
    }

    private void buyCard() {
        String ID;
        ID = RegexManager.get("id");
        // call...
        Shop.buyCard(ID);
    }

    private void upgradeCard() {
        String ID;
        ID = RegexManager.get("id");
        // call...
        Shop.upgradeCard(ID);
    }

    private void showMyCardsShop() {
        Shop.showMyCards();
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
        Game.setMode(number);
    }

    private void loginPlayerTwo() {
        String userName, password;
        userName = RegexManager.get("username");
        password = RegexManager.get("password");
        // call...
        Game.loginUser2(userName, password);
    }

    private void selectCharacterMultiplayer() {
        String player;
        player = RegexManager.get("player");
        int number;
        number = Integer.valueOf(RegexManager.get("number"));
        // call...
        Game.selectCharacterMultiplayer(player, number);

    }

    

    private void placeCardMultiplayer() {
        int id;
        id = Integer.valueOf(RegexManager.get("id"));
        int block;
        block = Integer.valueOf(RegexManager.get("block"));
        // call...
        Game.placeCard(id, block);
    }

    private void selectCharacterSinglePlayer() {

        int number;
        number = Integer.valueOf(RegexManager.get("number"));
        int level;
        number = Integer.valueOf(RegexManager.get("level"));
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