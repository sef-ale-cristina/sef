package com.example.sef_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpPacientController implements Initializable {
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
    private Label l_role;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_username;

    public Button getButton_signup() {
        return button_signup;
    }

    public void setButton_signup(Button button_signup) {
        this.button_signup = button_signup;
    }

    public Button getButton_log_in() {
        return button_log_in;
    }

    public void setButton_log_in(Button button_log_in) {
        this.button_log_in = button_log_in;
    }

    public TextField getTf_name() {
        return tf_name;
    }

    public void setTf_name(TextField tf_name) {
        this.tf_name = tf_name;
    }

    public TextField getTf_lastname() {
        return tf_lastname;
    }

    public void setTf_lastname(TextField tf_lastname) {
        this.tf_lastname = tf_lastname;
    }

    public DatePicker getDp_birthdate() {
        return dp_birthdate;
    }

    public void setDp_birthdate(DatePicker dp_birthdate) {
        this.dp_birthdate = dp_birthdate;
    }

    public TextField getTf_phone() {
        return tf_phone;
    }

    public void setTf_phone(TextField tf_phone) {
        this.tf_phone = tf_phone;
    }

    public TextField getTf_other() {
        return tf_other;
    }

    public void setTf_other(TextField tf_other) {
        this.tf_other = tf_other;
    }

    public Label getL_role() {
        return l_role;
    }

    public void setL_role(Label l_role) {
        this.l_role = l_role;
    }

    public TextField getTf_password() {
        return tf_password;
    }

    public void setTf_password(TextField tf_password) {
        this.tf_password = tf_password;
    }

    public TextField getTf_username() {
        return tf_username;
    }

    public void setTf_username(TextField tf_username) {
        this.tf_username = tf_username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!tf_name.getText().trim().isEmpty() && !tf_lastname.getText().trim().isEmpty() && !dp_birthdate.getValue().toString().trim().isEmpty() && !tf_phone.getText().trim().isEmpty() && !tf_other.getText().trim().isEmpty() && !tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event,tf_name.getText(), tf_lastname.getText(), dp_birthdate.getValue() , tf_phone.getText(), tf_other.getText(), tf_username.getText(), tf_password.getText(), l_role.getText());
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
