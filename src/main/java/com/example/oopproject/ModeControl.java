package com.example.oopproject;

import com.example.oopproject.core.Game;
import javafx.fxml.FXML;

import java.io.IOException;

public class ModeControl {
    @FXML
    public void gologin() throws IOException {
        Statics.clickEffect.play();
        Game.setMode(1);
        Statics.changeScene("loginUser2.fxml");
    }
    @FXML
    public void gologin2() throws IOException {
        Statics.clickEffect.play();
        Game.setMode(2);
        Statics.changeScene("loginUser2.fxml");
    }
    @FXML
    public void back1() throws IOException {
        Statics.clickEffect.play();
        Statics.changeScene("mainMenu.fxml");
    }

}
