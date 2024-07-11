package com.example.oopproject;

import com.example.oopproject.core.Auth;
import com.example.oopproject.core.DB;
import com.example.oopproject.core.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainControl implements Initializable {
    @FXML
    Label username;
    @FXML
    Label coin;
    @FXML
    Label XP;
    @FXML
    Label XP1;
    @FXML
    Button profile,setting,shop,history,start,logout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(DB.getUsernameById(Session.getInstance().getLoggedUser()));
        coin.setText(DB.getUserCoins(Session.getInstance().getLoggedUser())+"");
        XP.setText(DB.getUserLevel(Session.getInstance().getLoggedUser())+"");
        XP1.setText(DB.getUserById(Session.getInstance().getLoggedUser()).XP+"");

    }
    @FXML
    public void logoutFunc() throws IOException {
        Statics.clickEffect.play();
        Auth.logout();
        Statics.changeScene("firstMenu.fxml");
    }
    @FXML
    public void goProfile() throws IOException {
        Statics.clickEffect.play();
        Statics.changeScene("profile.fxml");
    }
    @FXML
    public void startG() throws IOException {
        Statics.clickEffect.play();
        Statics.changeScene("modeMenu.fxml");
    }
    @FXML
    public void setting1() throws IOException {
        Statics.clickEffect.play();
        Statics.changeScene("setting.fxml");
    }
    @FXML
    public void shop1() throws IOException {
        Statics.clickEffect.play();
        Statics.changeScene("shop.fxml");
    }
    @FXML
    public void battleHist() throws IOException {
        Statics.clickEffect.play();
        Statics.changeScene("history.fxml");
    }


}
