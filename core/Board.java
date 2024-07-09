package core;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private String player1;
    private String player2;
    private String player1_char;
    private String player2_char;
    private int player1_hp;
    private int player2_hp;
    private int player1_dmg;
    private int player2_dmg;
    private int player1_turn;
    private int player2_turn;
    private int player1_gap;
    private int player2_gap;
    private ArrayList<String> player1_cards;
    private ArrayList<String> player2_cards;
    private ArrayList<String> player1_hand;
    private ArrayList<String> player2_hand;
    private Cell[] player1_board;
    private Cell[] player2_board;

    public Board(String player1, String player2, String player1_char, String player2_char, int player1_hp,
            int player2_hp, ArrayList<String> player1_cards, ArrayList<String> player2_cards) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1_char = player1_char;
        this.player2_char = player2_char;
        this.player1_hp = player1_hp;
        this.player2_hp = player2_hp;
        this.player1_cards = player1_cards;
        this.player2_cards = player2_cards;
        this.player1_gap = Utils.getRandomNumber(1, 21);
        this.player2_gap = Utils.getRandomNumber(1, 21);
        firstHand();
        newTurn();
    }

    // public Board(String player1, String player2) {
    // this.player1 = player1;
    // this.player2 = player2;
    // this.player1_gap = Utils.getRandomNumber(1,21);
    // this.player2_gap = Utils.getRandomNumber(1,21);
    // }
    public void newTurn() {
        setPlayer1_turn(4);
        setPlayer2_turn(4);
    }

    public void placeCard(String card_id, int place, int player) {
        Card temp = DB.getCardByID(card_id);
        int duration = 1;////////////////////////////////////////////////////////
        int damage = 1;/////////////////////////////////////////////////////////
        int defence = 1;////////////////////////////////////////////////////////
        if (player == 1) {
            for (int i = place - 1; i < place + duration - 1; i++) {
                if (i == player1_gap - 1) {
                    System.out.println("You cannot do this because of the gap!");
                    return;
                }
                player1_board[i] = new Cell((int) damage / duration, defence, card_id, true);
            }
            player1_hand.remove(card_id);
            addToHand(1);
        } else {
            for (int i = place - 1; i < place + duration - 1; i++) {
                if (i == player2_gap - 1) {
                    System.out.println("You cannot do this because of the gap!");
                    return;
                }
                player2_board[i] = new Cell((int) damage / duration, defence, card_id, true);
            }
            player1_hand.remove(card_id);
            addToHand(2);
        }
    }

    public void checkActivation() {
        for (int i = 0; i < 21; i++) {
            if (player1_board[i].getDefence() > player2_board[i].getDefence()) {
                player2_board[i].setActive(false);
            }
            if (player1_board[i].getDefence() < player2_board[i].getDefence()) {
                player1_board[i].setActive(false);
            } else {
                player1_board[i].setActive(false);
                player2_board[i].setActive(false);
            }
        }
    }

    public void calculateDamage() {
        for (Cell cell : player1_board) {
            if (cell != null && cell.isActive())
                player1_dmg += cell.getDamage();
        }
        for (Cell cell : player2_board) {
            if (cell != null && cell.isActive())
                player2_dmg += cell.getDamage();
        }
    }

    public int victoryCoinCalculate(int hp, int level) {
        return hp * (level / 3);
    }

    public int victoryXPCalculate(int hp, int level) {
        return hp * (level / 2);
    }

    public int DefeatCoinCalculate(int hp, int level) {
        return hp * (level / 5);
    }

    public int DefeatXPCalculate(int hp, int level) {
        return (level) * 10 - hp;
    }

    public void shuffleCards(ArrayList<String> arr) {
        Random rand = new Random();
        int n = arr.size();
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, temp);
        }
    }

    public void firstHand() {
        shuffleCards(player1_cards);
        shuffleCards(player2_cards);
        for (int i = 0; i < 5; i++) {
            player1_hand.add(player1_cards.get(i));
            player2_hand.add(player2_cards.get(i));
        }
        for (int i = 0; i < 5; i++) {
            player1_cards.removeFirst();
            player1_cards.removeFirst();
        }
    }

    public void addToHand(int player) {
        shuffleCards(player1_cards);
        shuffleCards(player2_cards);
        if (player == 1) {
            player1_hand.add(player1_cards.getFirst());
            player1_cards.removeFirst();
        } else {
            player2_hand.add(player2_cards.getFirst());
            player2_cards.removeFirst();
        }
    }
    public void timeLine() {
        for (int i=0;i<21;i++){
            if (player1_board[i] != null && player1_board[i].isActive()){
                player2_hp-=player1_board[i].getDamage();
            }
            else if (player2_board[i] != null && player2_board[i].isActive()) {
                player1_hp-=player2_board[i].getDamage();
            }

            if (player1_hp <= 0) {
                System.out.println("Game is over! The winner is"+player2+"!");
                System.out.println(player2+": +"+victoryCoinCalculate(player2_hp,level)+"coin | +"+victoryXPCalculate(player2_hp,level)+"XP");
                System.out.println(player1+": -"+DefeatCoinCalculate(player2_hp,level)+"coin | +"+DefeatXPCalculate(player2_hp,level)+"XP");
                /////DB change\\\\\\ \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                return;
            } else if (player2_hp <= 0) {
                System.out.println("Game is over! The winner is"+player1+"!");
                System.out.println(player1+": +"+victoryCoinCalculate(player1_hp,level)+"coin | +"+victoryXPCalculate(player1_hp,level)+"XP");
                System.out.println(player2+": -"+DefeatCoinCalculate(player1_hp,level)+"coin | +"+DefeatXPCalculate(player1_hp,level)+"XP");
                /////DB change\\\\\\ \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                return;
            }
        }
        setPlayer1_dmg(0);
        setPlayer2_dmg(0);
        for (int i = 0; i < 21; i++) {
            player1_board[i] = null;
            player2_board[i] = null;
        }
        newTurn();
        return 2;
    }

    public void showBoard() {
        checkActivation();
        calculateDamage();
        // ####### player1 information #######\\
        System.out.println("Player 1: " + player1 + " " + player1_char + " HP :" + player1_hp + " DMG :" + player1_dmg
                + " Turn: " + player1_turn);
        // ####### player1 cards #######\\
        ////////////////////////////////////////////////////////
        // ####### cells #######\\
        for (int i = 0; i < 21 * 6 + 1; i++) {
            System.out.print("_");
        }
        System.out.println();

        for (Cell cell : player1_board) {
            if (cell.isActive())
                System.out.print("|" + cell.getDamage() + "|" + cell.getDefence());
            else
                System.out.print("|##|##");
        }
        System.out.println("|");
        for (Cell cell : player1_board) {
            if (cell.isActive())
                System.out.print("|  " + cell.getId() + "  ");
            else
                System.out.print("|  ##  ");
        }
        System.out.println("|");

        for (int i = 0; i < 21 * 6 + 1; i++) {
            System.out.print("=");
        }
        System.out.println();

        for (Cell cell : player2_board) {
            if (cell.isActive())
                System.out.print("|" + cell.getDamage() + "|" + cell.getDefence());
            else
                System.out.print("|##|##");
        }
        System.out.println("|");
        for (Cell cell : player2_board) {
            if (cell.isActive())
                System.out.print("|  " + cell.getId() + "  ");
            else
                System.out.print("|  ##  ");
        }
        System.out.println("|");

        for (int i = 0; i < 21 * 6 + 1; i++) {
            System.out.print("_");
        }
        System.out.println();

        // ####### player2 cards #######\\
        ////////////////////////////////////////////////////////
        // ####### player2 information #######\\
        System.out.println("Player 2: " + player2 + " " + player2_char + " HP :" + player2_hp + " DMG :" + player2_dmg
                + " Turn: " + player2_turn);
    }

    public String getPlayer1_char() {
        return player1_char;
    }

    public void setPlayer1_char(String player1_char) {
        this.player1_char = player1_char;
    }

    public String getPlayer2_char() {
        return player2_char;
    }

    public void setPlayer2_char(String player2_char) {
        this.player2_char = player2_char;
    }

    public int getPlayer1_hp() {
        return player1_hp;
    }

    public void setPlayer1_hp(int player1_hp) {
        this.player1_hp = player1_hp;
    }

    public int getPlayer2_hp() {
        return player2_hp;
    }

    public void setPlayer2_hp(int player2_hp) {
        this.player2_hp = player2_hp;
    }

    public int getPlayer1_dmg() {
        return player1_dmg;
    }

    public void setPlayer1_dmg(int player1_dmg) {
        this.player1_dmg = player1_dmg;
    }

    public int getPlayer2_dmg() {
        return player2_dmg;
    }

    public void setPlayer2_dmg(int player2_dmg) {
        this.player2_dmg = player2_dmg;
    }

    public int getPlayer1_turn() {
        return player1_turn;
    }

    public void setPlayer1_turn(int player1_turn) {
        this.player1_turn = player1_turn;
    }

    public int getPlayer2_turn() {
        return player2_turn;
    }

    public void setPlayer2_turn(int player2_turn) {
        this.player2_turn = player2_turn;
    }
}
