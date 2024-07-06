import java.util.ArrayList;

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
    private Cell[] player1_board;
    private Cell[] player2_board;


    public Board(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1_gap = Utils.getRandomNumber(1,21);
        this.player2_gap = Utils.getRandomNumber(1,21);
    }

    public void placeCard(String card_id ,int place, int player){
        int duration = 1;////////////////////////////////////////////////////////
        int damage = 1;////////////////////////////////////////////////////////
        int defence = 1;////////////////////////////////////////////////////////
        if (player==1){
            for (int i=place-1;i<place+duration-1;i++){
                if (i==player1_gap-1){
                    System.out.println("You cannot do this because of the gap!");
                    return;
                }
                player1_board[i] = new Cell((int)damage/duration,defence,card_id);
            }
        }
        else {
            for (int i=place-1;i<place+duration-1;i++){
                if (i==player2_gap-1){
                    System.out.println("You cannot do this because of the gap!");
                    return;
                }
                player2_board[i] = new Cell((int)damage/duration,defence,card_id);
            }
        }
    }

    public void showBoard(){
    //####### player1 information #######\\
        System.out.println("Player 1: "+player1+" "+player1_char+" HP :"+player1_hp+" DMG :"+player1_dmg+" Turn: "+player1_turn);
    //####### player1 cards #######\\
        ////////////////////////////////////////////////////////
    //####### cells #######\\
        for (int i=0;i<21*6+1;i++){
            System.out.print("_");
        }
        System.out.println();

        for (Cell cell : player1_board){
            System.out.print("|"+cell.getDamage()+"|"+cell.getDefence());
        }
        System.out.println("|");
        for (Cell cell : player1_board){
            System.out.print("|  "+cell.getId()+"  ");
        }
        System.out.println("|");

        for (int i=0;i<21*6+1;i++){
            System.out.print("=");
        }
        System.out.println();

        for (Cell cell : player2_board){
            System.out.print("|"+cell.getDamage()+"|"+cell.getDefence());
        }
        System.out.println("|");
        for (Cell cell : player2_board){
            System.out.print("|  "+cell.getId()+"  ");
        }
        System.out.println("|");

        for (int i=0;i<21*6+1;i++){
            System.out.print("_");
        }
        System.out.println();

    //####### player2 cards #######\\
        ////////////////////////////////////////////////////////
    //####### player2 information #######\\
        System.out.println("Player 2: "+player2+" "+player2_char+" HP :"+player2_hp+" DMG :"+player2_dmg+" Turn: "+player2_turn);
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
