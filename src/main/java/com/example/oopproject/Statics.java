package com.example.oopproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class Statics {
    static String tempPath = String.valueOf(Statics.class.getResource("1.mp3"));
    static Media tempMedia = new Media(tempPath);
    static public MediaPlayer backgroundMusic = new MediaPlayer(tempMedia);
    public static Stage stage;
    static String tempPath2 = String.valueOf(Statics.class.getResource("click.wav"));
    static AudioClip clickEffect = new AudioClip(tempPath2);
    static public FXMLLoader changeScene(String f) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(f));
        Scene scene = new Scene(fxmlLoader.load(), 829, 628);
        stage.setScene(scene);
        return fxmlLoader;
    }
}
