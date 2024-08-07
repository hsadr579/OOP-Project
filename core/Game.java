package core;

import java.security.PublicKey;
import java.util.ArrayList;

import javax.swing.text.html.parser.Element;
import javax.xml.parsers.FactoryConfigurationError;

enum Mode {
    MULTIPLAYER, SINGLE_PLAYER, BET
}

public class Game {
    static Board board;
    static BossBoard bossBoard;
    static Mode mode;
    static User player1;
    static User player2;
    static User currentPlayer;
    static Tower map;
    static int betCost;
    static boolean sCost1, sCost2;
    static String[] chars = { "Darth", "Luke", "Fett", "Count" };
    static Mode[] modes = { Mode.MULTIPLAYER, Mode.BET,Mode.SINGLE_PLAYER };

    public static void newMultiplayerGame() {
        mode = Mode.MULTIPLAYER;
        player1 = DB.getUserById(Session.getInstance().getLoggedUser());
        Session.getInstance().setCurrentMenu(Menus.MULTI_PLAYER_MODE_LOGIN);
        Session.getInstance().setOutput(Outputs.LOGIN_PLAYER2);

    }

    public static void setMode(int moden) {

        if (modes[moden-1] == Mode.MULTIPLAYER) {
            newMultiplayerGame();
        }
        if (modes[moden-1] == Mode.BET) {
            newBetModeGame();
        }
        if (modes[moden-1] == Mode.SINGLE_PLAYER) {
            newSinglePlayerGame();
        }
    }

    public static void loginUser2(String username, String password) {
        if (DB.login(username, password)) {
            player2 = DB.getUserById(DB.getUserId(username));

            if (mode == Mode.MULTIPLAYER)
            {
                Session.getInstance().setCurrentMenu(Menus.MULTI_PLAYER_MODE_CHARACTER);
                }
            else
                Session.getInstance().setCurrentMenu(Menus.BET_COST);

            Session.getInstance().setOutput(Outputs.SUCCESS_LOGIN.get()+"\n"+Outputs.SELECT_CHAR.get());
        } else {
            Session.getInstance().setOutput(Outputs.ERROR_WRONG_PASSWORD);
        }
    }

    public static void seBetCost(String username, int cost) {

        if (player1.username.equals(username)) {
            if (cost > player1.coin)
                Session.getInstance().setOutput(Outputs.NOT_ENOUGH_COIN_BET);
            else {
                player1.coin -= cost;
                betCost += cost;
                sCost1 = true;
            }
        } else if (player2.username.equals(username)) {
            if (cost > player1.coin)
                Session.getInstance().setOutput(Outputs.NOT_ENOUGH_COIN_BET);
            else {
                player2.coin -= cost;
                betCost += cost;
                sCost2 = true;
            }
        } else {
            Session.getInstance().setOutput(Outputs.TRY_AGAIN);

        }
        if (sCost1 && sCost2) {
            sCost1 = false;
            sCost2 = false;
             Session.getInstance().setOutput(Outputs.SELECT_CHAR);
            Session.getInstance().setCurrentMenu(Menus.MULTI_PLAYER_MODE_CHARACTER);
        }
    }

    public static void selectCharacterMultiplayer(String username, int character) {

        if (player1.username.equals(username)) {
            player1.character = chars[character-1];
        } else if (player2.username.equals(username)) {
            player2.character = chars[character-1];
        } else {
            Session.getInstance().setOutput(Outputs.TRY_AGAIN);
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
            board.showBoard();
        }
    }
    public static void selectCharacterSinglePlayer(int character , int level) {

        player1.character = chars[character-1];
        if (level==5) player2 = DB.getUserById(DB.getUserId("Boss"));
        player2 = DB.getUserById(DB.getUserId(chars[character-1]));

        if (player1.character != null && player2.character != null) {
            Session.getInstance().setCurrentMenu(Menus.SINGLE_PLAYER_MODE_GAME);
            ArrayList<Card> c1 = new ArrayList<>();
            ArrayList<Card> c2 = new ArrayList<>();
            for (String i : player1.cardsId) {
                c1.add(DB.getCardByID(i));
            }
            if (level == 5) {
                bossBoard = new BossBoard(player1.username, player1.character, player1.HP, player1.level,c1);
            }
            else {
                for (String i : player2.cardsId) {
                    c2.add(DB.getCardByID(i));
                }
                board = new Board(player1.username, player2.username, player1.character, player2.character, player1.HP,
                        player2.HP, c1, c2, 1, player1.level, player2.level,true);
            }
        }
    }

    public static void selectCard() {
        // ASA->don't need
        // HS->nice
    }

    public static void placeCard(int i, int place) {
        board.placeCard(i, place);
        board.showBoard();
        if (board.getPlayer1_turn() <= 0 && board.getPlayer2_turn() <= 0) {
            if (board.timeLine() == 1) {
                Session.getInstance().setCurrentMenu(Menus.WIN);
                DB.setUsersXP(DB.getUserId(player1.username),
                        player1.XP + board.newXPW);
                DB.setUsersXP(DB.getUserId(player2.username),
                        player2.XP + board.newXPD);
                DB.setUsersCoins(DB.getUserId(player1.username),
                        player1.coin + board.coinGift + ((mode == Mode.BET) ? betCost : 0));
                DB.setUsersCoins(DB.getUserId(player2.username),
                        player2.coin - board.coinDec);
                Win.setUp(player1.username, player2.username, board.coinGift, board.coinDec, board.newXPW,
                        board.newXPD);
                DB.addGame(DB.getUserId(player1.username),DB.getUserId(player2.username),player2.level,true,"+coin +XP","-coin +XP");
            } else if (board.timeLine() == 2) {
                Session.getInstance().setCurrentMenu(Menus.WIN);
                Session.getInstance().setCurrentMenu(Menus.WIN);
                DB.setUsersXP(DB.getUserId(player2.username),
                        player2.XP + board.newXPW);
                DB.setUsersXP(DB.getUserId(player1.username),
                        player1.XP + board.newXPD);
                DB.setUsersCoins(DB.getUserId(player2.username),
                        player2.coin + board.coinGift + ((mode == Mode.BET) ? betCost : 0));
                DB.setUsersCoins(DB.getUserId(player1.username),
                        player1.coin - board.coinDec);
                Win.setUp(player2.username, player1.username, board.coinGift, board.coinDec, board.newXPW,
                        board.newXPD);
                DB.addGame(DB.getUserId(player2.username),DB.getUserId(player1.username),player2.level,false,"+coin +XP","-coin +XP");

            } else {
                board.newTurn();
            }
        }
    }
    public static void placeCardBoss(int i) {
        bossBoard.placeCard(i);
        if (board.getPlayer1_turn() == 0) {
            if (board.timeLine() == 0) {
                //defeat
            } else if (board.timeLine() == 1) {
                //win
            }
        }
    }

    public static void newSinglePlayerGame() {
        mode = Mode.SINGLE_PLAYER;
        player1 = DB.getUserById(Session.getInstance().getLoggedUser());
        Session.getInstance().setOutput(Outputs.SELECT_CHAR);
        Session.getInstance().setCurrentMenu(Menus.SINGLE_PLAYER_MODE_CHARACTER);
    }

    public static void newBetModeGame() {
        betCost = 0;
        sCost1 = false;
        sCost2 = false;
        mode = Mode.BET;
        player1 = DB.getUserById(Session.getInstance().getLoggedUser());
        Session.getInstance().setOutput(Outputs.LOGIN_PLAYER2);
        Session.getInstance().setCurrentMenu(Menus.MULTI_PLAYER_MODE_LOGIN);
    }
}
