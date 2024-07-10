package core;

import java.security.PublicKey;
import java.util.ArrayList;

enum Mode {
    MULTIPLAYER, SINGLE_PLAYER, BET
}

public class Game {
    static Board board;
    static Mode mode;
    static User player1;
    static User player2;
    static User currentPlayer;
    static Tower map;
    static Character[] chars = {};

    public static void newMultiplayerGame() {
        mode = Mode.MULTIPLAYER;
        player1 = DB.getUserById(Session.getInstance().getLoggedUser());
        Session.getInstance().setCurrentMenu(Menus.MULTI_PLAYER_MODE_LOGIN);

    }

    public static void loginUser2(String username, String password) {
        if (DB.login(username, password)) {
            player2 = DB.getUserById(DB.getUserId(username));
            Session.getInstance().setCurrentMenu(Menus.MULTI_PLAYER_MODE_CHARACTER);
            Session.getInstance().setOutput(Outputs.SUCCESS_LOGIN);
        } else {
            Session.getInstance().setOutput(Outputs.ERROR_WRONG_PASSWORD);
        }
    }

    public static void selectCharacterMultiplayer(String username, int character) {

        if (player1.username.equals(username)) {
            player1.character = chars[character];
        } else {
            player2.character = chars[character];
        }
        if (player1.character != 0 && player2.character != 0) {
            Session.getInstance().setCurrentMenu(Menus.MULTI_PLAYER_MODE_GAME);
            ArrayList<Card> c1 = new ArrayList<>();
            ArrayList<Card> c2 = new ArrayList<>();
            for (String i : player1.cardsId) {
                c1.add(DB.getCardByID(i));
            }
            for (String i : player2.cardsId) {
                c2.add(DB.getCardByID(i));
            }
            board = new Board(player1.username, player2.username, player1.character, player2.character, player1.HP,
                    player2.HP, c1, c2, 1, player1.level, player2.level);
        }
    }

    public static void selectCard() {

    }

    public static void placeCard() {

    }

    public static void newSinglePlayerGame() {

    }

    public static void newBetModeGame() {

    }
}
