package core;

import java.util.ArrayList;
import java.util.Random;

public class BossBoard {
    private int boss_hp;
    private int boss_damage;
    private Cell[] boss_cards;
    private String player1;
    private String player1_char;
    private int player1_hp;
    private int player1_dmg;
    private int player1_turn;
    private int level_player1;
    private ArrayList<Card> player1_cards;
    private ArrayList<Card> player1_hand;
    private Cell[] player1_board;
    public int coinGift;
    public int newXPW;

    public BossBoard(String player1, String player1_char, int player1_hp, int level_player1, ArrayList<Card> player1_cards) {
        this.player1 = player1;
        this.player1_char = player1_char;
        this.player1_hp = player1_hp;
        this.player1_dmg = 0;
        this.level_player1 = level_player1;
        this.player1_cards = player1_cards;
        this.player1_hand = player1_hand;
        this.boss_hp=250;
        this.boss_damage=0;
        this.player1_turn=5;
        buffType();
        firstHand();
    }
    public void startFight(){
        for (int i=0;i<21;i++){
            boss_cards[i] = new Cell(30+i,20+i+(int)(2/3*i), true);
            boss_damage+=20+i;
        }
    }
    public void placeCard(int cardIndex){
        int place = 1;
        Card temp = DB.getCardByID(player1_hand.get(cardIndex - 1).getId());
        int duration = temp.getDuration();
        int damage = temp.getDamage();
        int defence = temp.getDefence();
        for (int i=0;i<21;i++){
            if (player1_board[i]==null){
                place = i;
                break;
            }
        }
        for (int i = place - 1; i < place + duration - 1; i++) {
            player1_board[i] = new Cell((int) damage / duration, defence, true);
        }
        player1_turn--;
        addToHand();
        int m = Utils.getRandomNumber(0,20);
        int n = Utils.getRandomNumber(0,20);
        boss_cards[m].setDamage(boss_cards[m].getDamage()+5);
        boss_cards[n].setDamage(boss_cards[m].getDamage()+5);
        checkActivation();
        calculateDamage();
        showBoard();
    }
    public void showBoard(){
        // ####### player1 information #######\\
        System.out.println("Player 1: " + player1 + " " + player1_char + " HP :" + player1_hp + " DMG :" + player1_dmg
                + " Turn: " + player1_turn);
        // ####### player1 cards #######\\
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    (i + 1) + "- " + player1_hand.get(i).getName() + " |dur=" + player1_hand.get(i).getDuration()
                            + " |def=" + player1_hand.get(i).getDefence() + " |dmg=" + player1_hand.get(i).getDamage());
        }
        // ####### cells #######\\
        for (int i = 0; i < 21 * 6 + 1; i++) {
            System.out.print("_");
        }
        System.out.println();

        for (int i = 0; i < 21 ; i++) {
            if (player1_board[i].isActive())
                System.out.print("|" + player1_board[i].getDamage() + "|" + player1_board[i].getDefence());
            else if (player1_board[i]!=null)
                System.out.print("|##|##");
            else
                System.out.print("|  |  ");
        }
        System.out.println("|");
        for (int i = 0; i < 21 ; i++) {
            if (player1_board[i].isActive())
                System.out.print("| " + player1_board[i].getId() + "  ");
            else if (player1_board[i]!=null)
                System.out.print("| ##  ");
            else
                System.out.print("|     ");
        }
        System.out.println("|");

        for (int i = 0; i < 21 * 6 + 1; i++) {
            System.out.print("=");
        }
        System.out.println();
        for (int i = 0; i < 21 ; i++) {
            if (boss_cards[i].isActive())
                System.out.print("|" + boss_cards[i].getDamage() + "|" + boss_cards[i].getDefence());
            else if (boss_cards[i]!=null)
                System.out.print("|##|##");
            else
                System.out.print("|  |  ");
        }
        System.out.println("|");
        for (int i = 0; i < 21 ; i++) {
            if (boss_cards[i].isActive())
                System.out.print("|     ");
            else if (boss_cards[i]!=null)
                System.out.print("| ##  ");
            else
                System.out.print("|     ");
        }
        System.out.println("|");
        for (int i = 0; i < 21 * 6 + 1; i++) {
            System.out.print("_");
        }
        System.out.println();

        System.out.println("Boss :  HP :" + boss_hp + " DMG :" + boss_damage);
    }
    public int timeLine() {
        for (int i = 0; i < 21; i++) {

            if (player1_board[i] != null && player1_board[i].isActive()) {
                boss_hp -= player1_board[i].getDamage();
            }
            if (player1_board[i]==null && boss_hp>0){
                return 0;
            }
            if (boss_hp<=0) {
                coinGift=100;
                newXPW=100;
                return 1;
            }
        }
        return 2;
    }

    public void checkActivation() {
        for (int i = 0; i < 21; i++) {

            if (player1_board[i].getDefence() > boss_cards[i].getDefence()) {
                boss_cards[i].setActive(false);
            }
            if (player1_board[i].getDefence() < boss_cards[i].getDefence()) {
                player1_board[i].setActive(false);
            } else if (player1_board[i].getDefence() == boss_cards[i].getDefence()) {
                player1_board[i].setActive(false);
                boss_cards[i].setActive(false);
            }
        }
    }
    public void calculateDamage() {
        for (Cell cell : player1_board) {
            if (cell != null && cell.isActive())
                player1_dmg += cell.getDamage();
        }
        for (Cell cell : boss_cards) {
            if (cell != null && cell.isActive())
                boss_damage += cell.getDamage();
        }
    }
    public void firstHand() {
        shuffleCards(player1_cards);
        for (int i = 0; i < 5; i++) {
            player1_hand.add(player1_cards.get(i));
        }
        for (int i = 0; i < 5; i++) {
            player1_cards.remove(0);
        }
    }

    public void addToHand() {
        shuffleCards(player1_cards);
        player1_hand.add(player1_cards.get(0));
        player1_cards.remove(0);
    }
    public void shuffleCards(ArrayList<Card> arr) {
        Random rand = new Random();
        int n = arr.size();
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, temp);
        }
    }
    public void buffType() {
        for (Card card : player1_cards) {
            if (player1_char.equals("Darth")) {
                if (card.getType().equals("imperial"))
                    card.setDamage(card.getDamage() + 10);
            } else if (player1_char.equals("Luke")) {
                if (card.getType().equals("jedi"))
                    card.setDamage(card.getDamage() + 10);
            } else if (player1_char.equals("Fett")) {
                if (card.getType().equals("mandalorian"))
                    card.setDamage(card.getDamage() + 10);
            } else if (player1_char.equals("Count")) {
                if (card.getType().equals("separatist"))
                    card.setDamage(card.getDamage() + 10);
            }
        }
    }
}
