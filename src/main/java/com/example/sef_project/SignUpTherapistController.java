package com.example.sef_project;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpTherapistController implements Initializable {
    @FXML
    private Button button_signup;

    @FXML
    private Button button_log_in;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_lastname;

    @FXML
    private DatePicker dp_birthdate;

    @FXML
    private TextField tf_phone;

    @FXML
    private TextField tf_price;

    @FXML
    private ComboBox<String> cb_begin;

    @FXML
    private ComboBox<String> cb_end;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_username;

    @FXML
    private ListView<String> list_working_days;

    ObservableList<String> days = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        list_working_days.setItems(days);
        list_working_days.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        StringBuilder selected_days = new StringBuilder();

        list_working_days.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            ObservableList<String> selectedItems = list_working_days.getSelectionModel().getSelectedItems();

            for (String name : selectedItems) {
                selected_days.append(name + " ");
            }

            //System.out.println(selected_days);

        });

        cb_begin.getItems().addAll( "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00");

        cb_end.getItems().addAll("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00");

        cb_begin.setOnAction(comboBoxStartHourEvent -> {
            cb_end.setOnAction(comboBoxEndHourEvent -> {
                button_signup.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        if(!tf_name.getText().trim().isEmpty() && !tf_lastname.getText().trim().isEmpty() && !dp_birthdate.getValue().toString().trim().isEmpty() && !tf_phone.getText().trim().isEmpty() && !tf_price.getText().trim().isEmpty() && !cb_begin.getValue().isEmpty() && !cb_end.getValue().isEmpty() && !tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                            DBUtils.signUpTherapist(event, tf_name.getText(), tf_lastname.getText(), dp_birthdate.getValue() , tf_phone.getText(), tf_price.getText(), selected_days.toString(), cb_begin.getValue(), cb_end.getValue(), tf_username.getText(), tf_password.getText(), "therapist");
                        }
                        else {
                            System.out.println("Please fill in all the information!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Please fill in all the information to sign up!");
                            alert.show();
                        }
                    }
                });
            });
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "log-in.fxml", "Log In!", null, null);
            }
        });
    }
}
