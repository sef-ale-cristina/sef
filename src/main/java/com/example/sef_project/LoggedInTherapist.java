package com.example.sef_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInTherapist implements Initializable {
    @FXML
    private Label l_welcome;

    @FXML
    private Button b_logout;

    public void setUserInformation(String username, String app_role) throws IOException {
        l_welcome.setText("Welcome, " + username + "!");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "log-in.fxml", "Log In!", null, null);
            }
        });
    }
}
