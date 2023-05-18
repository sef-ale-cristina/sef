package com.example.sef_project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInPacient implements Initializable {
    @FXML
    private ListView<String> list_therapists;
    String[] therapist={"Therapist1","Therapist2", "Therapist3", "Therapist4", "Therapist5"};

    @FXML
    private Label selected_th;
    String currentTherapist;

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
                DBUtils.changeScene(event, "log-in.fxml", "Log In!", null, null);
            }
        });

        list_therapists.getItems().addAll(therapist);
        list_therapists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentTherapist=list_therapists.getSelectionModel().getSelectedItem();
                selected_th.setText(currentTherapist);
            }
        });
    }

    public void setUserInformation(String username, String app_role) throws IOException {
        label_welcome.setText("Welcome, " + username + "!");
    }
}
