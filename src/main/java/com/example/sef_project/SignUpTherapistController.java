package com.example.sef_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
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
    private Label l_role;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!tf_name.getText().trim().isEmpty() && !tf_lastname.getText().trim().isEmpty() && !dp_birthdate.getValue().toString().trim().isEmpty() && !tf_phone.getText().trim().isEmpty() && !tf_price.getText().trim().isEmpty() && !tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event,tf_name.getText(), tf_lastname.getText(), dp_birthdate.getValue() , tf_phone.getText(), tf_price.getText(), tf_username.getText(), tf_password.getText(), l_role.getText());
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
