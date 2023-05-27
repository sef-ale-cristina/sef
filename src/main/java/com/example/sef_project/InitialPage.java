package com.example.sef_project;

import DataBase.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class InitialPage implements Initializable {
    @FXML
    private Button b_signup_pacient;

    @FXML
    private Button b_signup_therapist;

    @FXML
    private Button b_login;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "log-in.fxml", "Log In!", null, null);
            }
        });

        b_signup_pacient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sign-up-pacient.fxml", "Sign Up!", null, null);
            }
        });

        b_signup_therapist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sign-up-therapist.fxml", "Sign Up!", null, null);
            }
        });
    }
}
