package com.example.oopproject;

import com.example.oopproject.core.Auth;
import com.example.oopproject.core.Game;
import com.example.oopproject.core.Outputs;
import com.example.oopproject.core.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginUser2 {
    @FXML
    TextField username;
    @FXML
    PasswordField pass;
    @FXML
    Label error;

    @FXML
    public void login() throws IOException {
        Statics.clickEffect.play();
        Auth.login(username.getText(),pass.getText());
        if(Session.getInstance().getOutput()== Outputs.TRY_AGAIN)
        {
            error.setText(Session.getInstance().getOutputs());
            return;
        }
        if(Session.getInstance().getOutput()==Outputs.SUCCESS_LOGIN)
        {
            Game.loginUser2(username.getText(),pass.getText());
            Statics.changeScene("selectChar.fxml");
        }
        else{
            error.setText(Session.getInstance().getOutput().get());
        }
    }
    @FXML
    public void  back() throws IOException {
        Statics.clickEffect.play();
        Statics.changeScene("modeMenu.fxml");
    }


}
