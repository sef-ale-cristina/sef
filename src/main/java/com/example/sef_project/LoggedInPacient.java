package com.example.sef_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInPacient implements Initializable {
    @FXML
    private ListView<> list_therapists;

    @FXML
    private DatePicker date_appointment;

    @FXML
    private ComboBox<String> cb_time;

    @FXML
    private ComboBox<String> cb_therapist;

    @FXML
    private Button b_schedule;

    @FXML
    private Button b_logout;

    @FXML
    private Label label_welcome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Log In!", null, null);
            }
        });
    }
}
