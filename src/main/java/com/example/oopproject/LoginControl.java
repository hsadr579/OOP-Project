package com.example.oopproject;

import com.example.oopproject.core.Auth;
import com.example.oopproject.core.Outputs;
import com.example.oopproject.core.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginControl implements Initializable {

    @FXML
    TextField username;
    @FXML
    PasswordField pass;
    @FXML
    TextField answer;
    @FXML
    Button forget;
    @FXML
    Button login;
    @FXML
    Label error;
    @FXML
    public void forgetPass() throws IOException {
        Statics.clickEffect.play();

    }
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
            Statics.changeScene("mainMenu.fxml");
        }
        else{
            error.setText(Session.getInstance().getOutput().get());
        }
    }
@FXML
public void back() throws IOException {
    Statics.clickEffect.play();
    Statics.changeScene("firstMenu.fxml");
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
