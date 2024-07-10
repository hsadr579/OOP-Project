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
    static int betCost;
    static String[] chars = { "Darth", "Luke", "Fett", "Count" };
    static Mode[] modes = { Mode.MULTIPLAYER, Mode.SINGLE_PLAYER, Mode.BET };

    public static void newMultiplayerGame() {
        mode = Mode.MULTIPLAYER;
        player1 = DB.getUserById(Session.getInstance().getLoggedUser());
        Session.getInstance().setCurrentMenu(Menus.MULTI_PLAYER_MODE_LOGIN);

    }

    public static void setMode(int moden) {

        if (modes[moden] == Mode.MULTIPLAYER) {
            newMultiplayerGame();
        }

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

    public static void selectStringMultiplayer(String username, int character) {

        if (player1.username.equals(username)) {
            player1.character = chars[character];
        } else {
            player2.character = chars[character];
        }
        if (player1.character != null && player2.character != null) {
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
        // ASA->don't need
        // HS->nice
    }

    public static void placeCard(int i, int place) {
        board.placeCard(i, place);
        board.showBoard();
        if (board.getPlayer1_turn() == 0 && board.getPlayer2_turn() == 0) {
            if (board.timeLine() == 1) {
                Session.getInstance().setCurrentMenu(Menus.WIN);
      
            } else if (board.timeLine() == 2) {
                Session.getInstance().setCurrentMenu(Menus.WIN);
            } else {
                board.newTurn();
            }
        }
    }

    public static void newSinglePlayerGame() {

    }

    public static void newBetModeGame() {

    }
}
