package com.example.oopproject;

import com.example.oopproject.core.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class signControl implements Initializable {
    @FXML
    Button sign_upB;
    @FXML
    Button randomB;
    @FXML
    Label error;
    @FXML
    Label randPass;
    @FXML
    TextArea captcha;
    @FXML
    PasswordField pass;
    @FXML
    PasswordField confirm_pass;
    @FXML
    TextField username;
    @FXML
    TextField nickname;
    @FXML
    TextField answer;
    @FXML
    TextField email;
    @FXML
    TextField captchaF;
    @FXML
    RadioButton q1;
    @FXML
    RadioButton q2;
    @FXML
    RadioButton q3;
    @FXML
    public void getRandomPass()
    {
       String passi=Utils.generateRandomString(8);
        pass.setText(passi);

        confirm_pass.setText(passi);
        randPass.setText(passi);
        Statics.clickEffect.play();
    }
    @FXML
    public void signUp()
    {
        Statics.clickEffect.play();
        if(!capText.equals(captchaF.getText()))
        {
            error.setText("Captcha is incorrect");
            updateCaptcha();
            return;
        }
        int id;
        if(q1.isSelected())id=1;
        else if(q2.isSelected())id=2;
        else id=3;
        Auth.register(username.getText(),pass.getText(),confirm_pass.getText(),nickname.getText(),id,answer.getText(),email.getText());
        Outputs o=Session.getInstance().getOutput();
        if(o==Outputs.SUCCESS_CREATE_USER)
        {
            try {
                Statics.changeScene("firstMenu.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            error.setText(o.get());
        }

    }
    final ToggleGroup group = new ToggleGroup();
    @FXML
    public void back()
    {
        Statics.clickEffect.play();
        try {
            Statics.changeScene("firstMenu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        q1.setToggleGroup(group);
        q1.setSelected(true);
        q2.setToggleGroup(group);
        q3.setToggleGroup(group);
        updateCaptcha();

    }
    String capText;
    public void updateCaptcha()
    {
        capText=Utils.generateRandomString(4);
        captcha.setDisable(false);
        captcha.setText(Utils.convertStringToAsciiArt(capText));
        captcha.setDisable(true);
    }
}
