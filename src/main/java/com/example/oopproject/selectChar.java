package com.example.oopproject;

import com.example.oopproject.core.DB;
import com.example.oopproject.core.Game;
import com.example.oopproject.core.Mode;
import com.example.oopproject.core.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class selectChar implements Initializable {
    @FXML
    Label username;
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
    @FXML
    public void fc1()
    {

    }
    @FXML
    public void fc2()
    {

    }@FXML
    public void fc3()
    {

    }@FXML
    public void fc4()
    {

    }

}
