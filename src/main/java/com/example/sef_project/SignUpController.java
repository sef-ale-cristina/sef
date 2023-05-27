package com.example.sef_project;

import DataBase.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
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
    private TextField tf_other;

    @FXML
    private RadioButton rb_therapist;

    @FXML
    private RadioButton rb_pacient;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_therapist.setToggleGroup(toggleGroup);
        rb_pacient.setToggleGroup(toggleGroup);

        rb_therapist.setSelected(true);

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                if(!tf_name.getText().trim().isEmpty() && !tf_lastname.getText().trim().isEmpty() && !dp_birthdate.getValue().toString().trim().isEmpty() && !tf_phone.getText().trim().isEmpty() && !tf_other.getText().trim().isEmpty() && !tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event,tf_name.getText(), tf_lastname.getText(), dp_birthdate.getValue() , tf_phone.getText(), tf_other.getText(), tf_username.getText(), tf_password.getText(), toggleName);
                }
                else {
                    System.out.println("Please fill in all the information!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all the information to sign up!");
                    alert.show();
                }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "log-in.fxml", "Log In!", null, null);
            }
        });
    }
}
