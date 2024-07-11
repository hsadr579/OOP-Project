package com.example.oopproject;

import com.example.oopproject.core.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class selectChar implements Initializable {
    @FXML
    Label username;
    @FXML
    Label error;
    @FXML
    TextField bet;
    int user=1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user=1;
        username.setText(Game.player1.username);
        if(!(Game.getMode()== Mode.BET))
        bet.setDisable(true);
    }
    public boolean setBet()
    {
        if((Game.getMode()== Mode.BET))return true;
        int c=Integer.valueOf(bet.getText());
        if(user==1)
        Game.seBetCost(Game.player1.username,c);
        else Game.seBetCost(Game.player2.username,c);
        if(Session.getInstance().getOutput()== Outputs.NOT_ENOUGH_COIN_BET)
        {
            error.setText(Outputs.NOT_ENOUGH_COIN_BET.get());
            return false;
        }
        return true;
    }
    @FXML
    public void fc1() throws IOException {
        Statics.clickEffect.play();
        if(setBet())
        {
        if(user==1)
        {
            Game.selectCharacterMultiplayer(Game.player1.username,1);
            username.setText(Game.player2.username);
            user=2;
        }else if(user==2)
        {
            Game.selectCharacterMultiplayer(Game.player2.username,1);
            Statics.changeScene("startGame.fxml");
        }

    }}
    @FXML
    public void fc2() throws IOException {
        Statics.clickEffect.play();
        if(setBet())
        {
            if(user==1)
            {
                Game.selectCharacterMultiplayer(Game.player1.username,2);
                username.setText(Game.player2.username);
                user=2;
            }else if(user==2)
            {
                Game.selectCharacterMultiplayer(Game.player2.username,2);
                Statics.changeScene("startGame.fxml");
            }

        }}@FXML
    public void fc3() throws IOException {
        Statics.clickEffect.play();
        if(setBet())
        {
            if(user==1)
            {
                Game.selectCharacterMultiplayer(Game.player1.username,3);
                username.setText(Game.player2.username);
                user=2;
            }else if(user==2)
            {
                Game.selectCharacterMultiplayer(Game.player2.username,3);
                Statics.changeScene("startGame.fxml");
            }

        }}@FXML
    public void fc4() throws IOException {
        Statics.clickEffect.play();
        if(setBet())
        {
            if(user==1)
            {
                Game.selectCharacterMultiplayer(Game.player1.username,4);
                username.setText(Game.player2.username);
                user=2;
            }else if(user==2)
            {
                Game.selectCharacterMultiplayer(Game.player2.username,4);
                Statics.changeScene("startGame.fxml");
            }

        }}

}
