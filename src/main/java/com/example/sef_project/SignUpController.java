package com.example.sef_project;

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

                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event, tf_username.getText(), tf_password.getText(), toggleName);
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
