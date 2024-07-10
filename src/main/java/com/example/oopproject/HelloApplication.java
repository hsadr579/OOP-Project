package com.example.oopproject;

import com.example.oopproject.core.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 829, 628);
        stage.setTitle("OOP PROJECT");
        stage.setScene(scene);
        Statics.stage=stage;
        stage.resizableProperty().setValue(false);
        Statics.backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        Statics.backgroundMusic.play();
        DB.createTables();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}