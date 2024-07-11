package com.example.oopproject;

import com.example.oopproject.core.DB;
import com.example.oopproject.core.Outputs;
import com.example.oopproject.core.Profile;
import com.example.oopproject.core.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileControl implements Initializable {
    @FXML
    TextField usernameF,nickname,password,email;
    @FXML
    Label username;
    @FXML
    Label coin;
    @FXML
    Label error;
    @FXML
    Label XP;
    @FXML
    Label XP1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(DB.getUsernameById(Session.getInstance().getLoggedUser()));
        coin.setText(DB.getUserCoins(Session.getInstance().getLoggedUser())+"");
        XP.setText(DB.getUserLevel(Session.getInstance().getLoggedUser())+"");
        XP1.setText(DB.getUserById(Session.getInstance().getLoggedUser()).XP+"");
    }
    @FXML
    public void back1() throws IOException {
        Statics.clickEffect.play();
        Statics.changeScene("mainMenu.fxml");
    }
    @FXML
    public void changeUserName()
    {
        Profile.changeUsername(usernameF.getText());
        if(!(Session.getInstance().getOutput()== Outputs.SUCCESS_CHANGE_USERNAME))
        {
            error.setText(Session.getInstance().getOutput().get());
        }
    }
    @FXML
    public void changeNickname()
    {
        Profile.changeUsername(nickname.getText());
        if(!(Session.getInstance().getOutput()== Outputs.SUCCESS_CHANGE_NICKNAME))
        {
            error.setText(Session.getInstance().getOutput().get());
        }
    }
    @FXML
    public void changePass()
    {
        Profile.changeUsername(password.getText());
        if(!(Session.getInstance().getOutput()== Outputs.SUCCESS_CHANGE_PASSWORD))
        {
            error.setText(Session.getInstance().getOutput().get());
        }
    }
    @FXML
    public void changeEmail()
    {
        Profile.changeUsername(email.getText());
        if(!(Session.getInstance().getOutput()== Outputs.SUCCESS_CHANGE_EMAIL))
        {
            error.setText(Session.getInstance().getOutput().get());
        }
    }
}
