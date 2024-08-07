package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

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
    private int level_player1, level_player2;
    private ArrayList<Card> player1_cards = new ArrayList<>();
    private ArrayList<Card> player2_cards= new ArrayList<>();
    private ArrayList<Card> player1_hand= new ArrayList<>();
    private ArrayList<Card> player2_hand= new ArrayList<>();
    private Cell[] player1_board = new Cell[21];
    private Cell[] player2_board= new Cell[21];
    private int current_player;
    private boolean isSingleMode;

    public Board(String player1, String player2, String player1_char, String player2_char, int player1_hp,
            int player2_hp, ArrayList<Card> player1_cards, ArrayList<Card> player2_cards, int current_player,
            int level_player1, int level_player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1_char = player1_char;
        this.player2_char = player2_char;
        this.player1_hp = player1_hp;
        this.player2_hp = player2_hp;
        this.player1_cards = player1_cards;
        this.player2_cards = player2_cards;
        this.current_player = current_player;
        this.level_player1 = level_player1;
        this.level_player2 = level_player2;
        this.isSingleMode = false;
        this.player1_gap = Utils.getRandomNumber(1, 21);
        this.player2_gap = Utils.getRandomNumber(1, 21);
        buffType();
        firstHand();
        newTurn();
    }
    public Board(String player1, String player2, String player1_char, String player2_char, int player1_hp,
                 int player2_hp, ArrayList<Card> player1_cards, ArrayList<Card> player2_cards, int current_player,
                 int level_player1, int level_player2 , boolean isSingleMode) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1_char = player1_char;
        this.player2_char = player2_char;
        this.player1_hp = player1_hp;
        this.player2_hp = player2_hp;
        this.player1_cards = player1_cards;
        this.player2_cards = player2_cards;
        this.current_player = current_player;
        this.level_player1 = level_player1;
        this.level_player2 = level_player2;
        this.isSingleMode = isSingleMode;
        this.player1_gap = Utils.getRandomNumber(1, 21);
        this.player2_gap = Utils.getRandomNumber(1, 21);
        buffType();
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


    public void placeCard(int placeHand, int place) {
        int player = current_player;
        Card temp = DB.getCardByID(player1_hand.get(placeHand - 1).getId());
        Spells tempType = null;
        if (temp.getGroup().equals("spell")) {
            if (temp.getId().equals(Spells.FIXER.get())) {
                doFixer();
                if (player == 1) {
                    player1_turn--;
                } else
                    player2_turn--;
            } else if (temp.getId().equals(Spells.ALARM.get())) {
                doAlarm();
                if (player == 1) {
                    player1_turn--;
                } else
                    player2_turn--;
            } else if (temp.getId().equals(Spells.HOLE.get())) {
                doHole();
                if (player == 1) {
                    player1_turn--;
                } else
                    player2_turn--;
            } else if (temp.getId().equals(Spells.THIEF.get())) {
                doThief();
                if (player == 1) {
                    player1_turn--;
                } else
                    player2_turn--;
            } else if (temp.getId().equals(Spells.SWAMP.get())) {
                doSwamp();
                if (player == 1) {
                    player1_turn--;
                } else
                    player2_turn--;
            } else if (temp.getId().equals(Spells.CLONE.get())) {
                doClone();
                if (player == 1) {
                    player1_turn--;
                } else
                    player2_turn--;
            } else if (temp.getId().equals(Spells.HIDDEN.get())) {
                doHide();
                if (player == 1) {
                    player1_turn--;
                } else
                    player2_turn--;
            } else {

                if (temp.getId().equals(Spells.STAR_DESTROYER.get())) {
                    tempType = Spells.STAR_DESTROYER;
                } else if (temp.getId().equals(Spells.SHIELD.get())) {
                    tempType = Spells.SHIELD;
                } else if (temp.getId().equals(Spells.HEAL.get())) {
                    tempType = Spells.HEAL;
                } else if (temp.getId().equals(Spells.CLOVER.get())) {
                    tempType = Spells.CLOVER;
                } else if (temp.getId().equals(Spells.POISON.get())) {
                    tempType = Spells.POISON;
                }
                if (player == 1) {
                    for (int i = place - 1; i < place + 1 - 1; i++) {
                        if (i == player1_gap - 1) {
                            Session.getInstance().setOutput(Outputs.ERROR_GAP);
                            return;
                        }
                        if (player1_board[i] != null) {
                            Session.getInstance().setOutput(Outputs.ERROR_NOT_EMPTY);
                            return;
                        }

                    }
                    for (int i = place - 1; i < place + 1 - 1; i++) {
                        player1_board[i] = new Cell(tempType);
                    }
                    player1_hand.remove(placeHand-1);
                    addToHand(1);
                    player1_turn--;
                    switchPlayer();
                    if (isSingleMode){
                        int[] out = botPlacing();
                        placeCard(out[0],out[1]);
                    }
                } else {
                    for (int i = place - 1; i < place + 1 - 1; i++) {
                        if (i == player2_gap - 1) {
                            Session.getInstance().setOutput(Outputs.ERROR_GAP);
                            return;
                        }
                        if (player2_board[i] != null) {
                            Session.getInstance().setOutput(Outputs.ERROR_NOT_EMPTY);
                            return;
                        }

                    }
                    for (int i = place - 1; i < place + 1 - 1; i++) {
                        player2_board[i] = new Cell(tempType);
                    }
                    player2_hand.remove(placeHand-1);
                    addToHand(2);
                    player2_turn--;
                    switchPlayer();
                }
            }
        } else {
            int duration = temp.getDuration();
            int damage = temp.getDamage();
            int defence = temp.getDefence();
            if (player == 1) {
                for (int i = place - 1; i < place + duration - 1; i++) {
                    if (i == player1_gap - 1) {
                        Session.getInstance().setOutput(Outputs.ERROR_GAP);
                        return;
                    }
                    if (player1_board[i] != null) {
                        Session.getInstance().setOutput(Outputs.ERROR_NOT_EMPTY);
                        return;
                    }

                }
                for (int i = place - 1; i < place + duration - 1; i++) {
                    player1_board[i] = new Cell((int) damage / duration, defence, temp.getId(), true);
                }
                player1_hand.remove(placeHand-1);
                addToHand(1);
                player1_turn--;
                switchPlayer();
            } else {
                for (int i = place - 1; i < place + duration - 1; i++) {
                    if (i == player2_gap - 1) {
                        Session.getInstance().setOutput(Outputs.ERROR_GAP);
                        return;
                    }
                    if (player2_board[i] != null) {
                        Session.getInstance().setOutput(Outputs.ERROR_NOT_EMPTY);
                        return;
                    }

                }
                for (int i = place - 1; i < place + duration - 1; i++) {
                    player2_board[i] = new Cell((int) damage / duration, defence, temp.getId(), true);
                }
                player2_hand.remove(placeHand-1);
                addToHand(2);
                player2_turn--;
                switchPlayer();
            }
        }
        checkActivation();
    }

    // ######################################################################
    // ######################################################################
    private void doAlarm() {
        player1_turn--;
        player2_turn--;

    }

    private void doClone() {
        try {
            if (current_player == 1) {
                player1_hand.add((Card) player1_hand.get(0).clone());

            } else {
                player2_hand.add((Card) player2_hand.get(0).clone());
            }
        } catch (Exception e) {
        }
    }

    private void doFixer() {
        if (current_player == 1) {
            player1_gap = -1;
        } else {
            player2_gap = -1;
        }
    }

    private void doHole() {
        if (current_player == 1) {
            player1_gap = Utils.getRandomNullIndex(player1_board);
        } else {
            player2_gap = Utils.getRandomNullIndex(player2_board);
        }
    }

    private boolean hide_player1 = false, hide_player2 = false;

    private void doHide() {
        if (current_player == 1) {
            hide_player2 = true;
            shuffleCards(player2_hand);
        } else {
            hide_player1 = true;
            shuffleCards(player2_hand);
        }
    }

    private int DAMAGE_REDUCE = 10;
    private int DEFENSE_REDUCE = 10;

    private void doSwamp() {
        if (current_player == 1) {
            Cell c1 = player2_board[Utils.getRandomNotNullIndex(player2_board)];
            c1.setDamage(c1.getDamage() - DAMAGE_REDUCE);
            Cell c2 = player2_board[Utils.getRandomNotNullIndex(player2_board)];
            c2.setDefence(c2.getDefence() - DEFENSE_REDUCE);
        } else {
            Cell c1 = player1_board[Utils.getRandomNotNullIndex(player1_board)];
            c1.setDamage(c1.getDamage() - DAMAGE_REDUCE);
            Cell c2 = player1_board[Utils.getRandomNotNullIndex(player1_board)];
            c2.setDefence(c2.getDefence() - DEFENSE_REDUCE);
        }
    }

    private void doThief() {
        if (current_player == 1) {
            player1_hand.add(player2_hand.get(0));
            player2_hand.remove(0);
        } else {
            player2_hand.add(player1_hand.get(0));
            player1_hand.remove(0);
        }
    }

    // ######################################################################
    // ######################################################################
    public void switchPlayer() {
        if (current_player == 1)
            current_player = 2;
        else
            current_player = 1;
    }

    public void checkActivation() {
        for (int i = 0; i < 21; i++) {
            if (player1_board[i]==null && player2_board[i]!=null){
                player2_board[i].setActive(true);
            }
            else if (player1_board[i]!=null && player2_board[i]==null){
                player1_board[i].setActive(true);
            }
            else if (player1_board[i]==null && player2_board[i]==null){

            }
            else {
                if (player1_board[i].getDefence() > player2_board[i].getDefence()) {
                    player2_board[i].setActive(false);
                }
                if (player1_board[i].getDefence() < player2_board[i].getDefence()) {
                    player1_board[i].setActive(false);
                } else if (player1_board[i].getDefence() == player2_board[i].getDefence()) {
                    player1_board[i].setActive(false);
                    player2_board[i].setActive(false);
                }
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

    public void firstHand() {
        shuffleCards(player1_cards);
        shuffleCards(player2_cards);
        for (int i = 0; i < 5; i++) {
            player1_hand.add(player1_cards.getFirst());
            player1_cards.removeFirst();
            player2_hand.add(player2_cards.getFirst());
            player2_cards.removeFirst();
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

    public int coinGift, coinDec;
    public int newXPW, newXPD;

    public int timeLine() {
        for (int i = 0; i < 21; i++) {

            if (player1_board[i] != null && player1_board[i].isSpell()) {
                if (player2_board[i] != null && player2_board[i].isSpell()) {
                    continue;
                } else if (player2_board[i] != null && player2_board[i].isActive()) {
                    if (player1_board[i].spellType() == Spells.STAR_DESTROYER) {
                        player2_hp -= player2_board[i].getDamage() * 2;
                    } else if (player1_board[i].spellType() == Spells.SHIELD) {

                    } else if (player1_board[i].spellType() == Spells.HEAL) {
                        player1_hp += 40;
                    } else if (player1_board[i].spellType() == Spells.CLOVER) {
                        player1_hp -= player2_board[i].getDamage() / 2;
                    } else if (player1_board[i].spellType() == Spells.POISON) {
                        player2_hp -= 30;
                    }
                }

            } else if (player2_board[i] != null && player2_board[i].isSpell()) {
                if (player1_board[i] != null && player1_board[i].isSpell()) {
                    continue;
                } else if (player1_board[i] != null && player1_board[i].isActive()) {
                    if (player2_board[i].spellType() == Spells.STAR_DESTROYER) {
                        player1_hp -= player1_board[i].getDamage() * 2;
                    } else if (player2_board[i].spellType() == Spells.SHIELD) {

                    } else if (player2_board[i].spellType() == Spells.HEAL) {
                        player2_hp += 40;
                    } else if (player2_board[i].spellType() == Spells.CLOVER) {
                        player2_hp -= player1_board[i].getDamage() / 2;
                    } else if (player2_board[i].spellType() == Spells.POISON) {
                        player1_hp -= 30;
                    }
                }
            } else {
                if (player1_board[i] != null && player1_board[i].isActive()) {
                    player2_hp -= player1_board[i].getDamage();
                } else if (player2_board[i] != null && player2_board[i].isActive()) {
                    player1_hp -= player2_board[i].getDamage();
                }

                if (player1_hp <= 0) {
                    coinGift=victoryCoinCalculate(player2_hp, level_player2);
                    newXPW=victoryXPCalculate(player2_hp, level_player2);
                    coinDec=DefeatCoinCalculate(player1_hp, level_player1);
                    newXPD=DefeatXPCalculate(player1_hp, level_player1);
                    
                    return 2;
                } else if (player2_hp <= 0) {
                    coinGift = victoryCoinCalculate(player1_hp, level_player1);
                    newXPW = victoryXPCalculate(player1_hp, level_player1);
                    coinDec = DefeatCoinCalculate(player2_hp, level_player2);
                    newXPD = DefeatXPCalculate(player2_hp, level_player2);
                    
                    return 1;
                }
            }
        }
        setPlayer1_dmg(0);
        setPlayer2_dmg(0);
        for (int i = 0; i < 21; i++) {
            player1_board[i] = null;
            player2_board[i] = null;
        }
        // newTurn();
        return 0;
    }

    public void showBoard() {

        calculateDamage();
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
            if (player1_board[i]==null){
                System.out.print("|  |  ");
            }
            else {
                if (player1_board[i].isActive())
                    System.out.print("|" + player1_board[i].getDamage() + "|" + player1_board[i].getDefence());
                else if (i==player1_gap-1)
                    System.out.print("| GAP ");
                else
                    System.out.print("|##|##");


            }

        }
        System.out.println("|");
        for (int i = 0; i < 21 ; i++) {
            if (player1_board[i]==null){
                System.out.print("|     ");
            }
            else {
                if (player1_board[i].isActive())
                    System.out.print("| " + player1_board[i].getId() + "  ");
                else if (i==player1_gap-1)
                    System.out.print("| GAP ");
                else
                    System.out.print("| ##  ");

            }

        }
        System.out.println("|");

        for (int i = 0; i < 21 * 6 + 1; i++) {
            System.out.print("=");
        }
        System.out.println();

        for (int i = 0; i < 21 ; i++) {
            if (player2_board[i]==null){
                System.out.print("|  |  ");
            }
            else {
                if (player2_board[i].isActive())
                    System.out.print("|" + player2_board[i].getDamage() + "|" + player2_board[i].getDefence());
                else if (i==player2_gap-1)
                    System.out.print("| GAP ");
                else
                    System.out.print("|##|##");
            }

        }
        System.out.println("|");
        for (int i = 0; i < 21 ; i++) {
            if (player2_board[i]==null){
                System.out.print("|     ");
            }
            else {
                if (player2_board[i].isActive())
                    System.out.print("| " + player2_board[i].getId() + "  ");
                else if (i==player2_gap-1)
                    System.out.print("| GAP ");
                else
                    System.out.print("| ##  ");

            }

        }
        System.out.println("|");

        for (int i = 0; i < 21 * 6 + 1; i++) {
            System.out.print("_");
        }
        System.out.println();

        // ####### player2 cards #######\\
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    (i + 1) + "- " + player2_hand.get(i).getName() + " |dur=" + player2_hand.get(i).getDuration()
                            + " |def=" + player2_hand.get(i).getDefence() + " |dmg=" + player2_hand.get(i).getDamage());
        }
        // ####### player2 information #######\\
        System.out.println("Player 2: " + player2 + " " + player2_char + " HP :" + player2_hp + " DMG :" + player2_dmg
                + " Turn: " + player2_turn);
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
        for (Card card : player2_cards) {
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

    public int[] botPlacing(){
        int card = Utils.getRandomNumber(0,4);
        int place = 1;
        int[] out = {card,place};
        if (player2_turn==4){
            place=Utils.findRandomIndex(player2_board, player2_hand.get(card).getDuration(),player2_gap-1);
            out[1]=place;
            return out;
        }
        else if (player2_turn==2 || player2_turn==3) {
            for (int i=0;i<21;i++){
                if (player1_board[i].getDefence()< player2_hand.get(card).getDefence()){
                    if (i>player2_gap-1 && (21-i)>=player2_hand.get(card).getDuration()){
                        place = i+1;
                        out[1]=place;
                        return out;
                    }
                    else if (i>player2_gap-1) {
                        place=Utils.findRandomIndex(player2_board, player2_hand.get(card).getDuration(),player2_gap-1);
                        out[1]=place;
                        return out;
                    }
                    else if (i<player2_gap-1 && (player2_gap-1-i)>=player2_hand.get(card).getDuration()) {
                        place = i+1;
                        out[1]=place;
                        return out;
                    }
                    else if (i<player2_gap-1) {
                        place=Utils.findRandomIndex(player2_board, player2_hand.get(card).getDuration(),player2_gap-1);
                        out[1]=place;
                        return out;
                    }
                }
            }
        }
        else {
            place=Utils.findRandomIndex(player2_board, player2_hand.get(card).getDuration(),player2_gap-1);
            out[1]=place;
            return out;
        }
        return out;
    }
}
