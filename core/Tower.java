package core;

import java.util.ArrayList;

public class Tower {
   private int floor;

    public Tower() {
        this.floor = 1;
    }

    public void startFloor1(String player1, String player2, String player1_char, String player2_char, int player1_hp, int player2_hp, ArrayList<String> player1_cards, ArrayList<String> player2_cards){
        Board board = new Board(player1,"Luke", player1_char, "Luke", player1_hp, 100, player1_cards, player2_cards);

    }
}
