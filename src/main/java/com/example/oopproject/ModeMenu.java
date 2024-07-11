package com.example.oopproject;

import com.example.oopproject.core.Game;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;

public class ModeMenu {
    @FXML
    public void gologin() throws IOException {
        Statics.clickEffect.play();
        Game.setMode(1);
        Statics.changeScene("loginUser2.fxml");
    }
    public void gologin2() throws IOException {
        Statics.clickEffect.play();
        Game.setMode(2);
        Statics.changeScene("loginUser2.fxml");
    }

}
