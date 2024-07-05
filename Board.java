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
        this.player1_gap = Useful.getRandomNumber(1,24);
        this.player2_gap = Useful.getRandomNumber(1,24);
    }

    public void placeCard(String card_id ,int place, int player){
        int duration = 1;
        int damage = 1;
        int defence = 1;
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
        for
        System.out.print();
    }
}
