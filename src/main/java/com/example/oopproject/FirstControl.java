package com.example.oopproject;

import javafx.fxml.FXML;

import java.io.IOException;

public class FirstControl
{

    @FXML
    public void signUP() throws IOException {
            Statics.clickEffect.play();
            Statics.changeScene("signUp.fxml");

    }
    @FXML
    public void login() throws IOException {
        Statics.clickEffect.play();
        Statics.changeScene("login.fxml");
    }
}
