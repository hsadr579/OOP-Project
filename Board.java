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
    private ArrayList<String> player1_board;
    private ArrayList<String> player2_board;


    public Board(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1_gap = Useful.getRandomNumber(1,24);
        this.player2_gap = Useful.getRandomNumber(1,24);
    }

    public void placeCard(String card_id ,int place, int player){
        if (player==1){

        }
        else {

        }
    }
}
