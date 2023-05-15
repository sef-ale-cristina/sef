package com.example.sef_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class LoggedInTherapist {
    @FXML
    private Label l_welcome;

    public void setUserInformation(String username, String app_role) throws IOException {
        l_welcome.setText("Welcome, " + username + "!");
    }
}
