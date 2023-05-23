package com.example.sef_project;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;

public class LoggedInPacient implements Initializable {
    @FXML
    private TableView<Therapist> table_therapists;

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

    @FXML
    private TableColumn<Therapist, String> t_username;

    ObservableList<Therapist> listview = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "log-in.fxml", "Log In!", null, null);
            }
        });

        t_username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));

        try {
            DBConnect cn = new DBConnect();
            Connection cn1 = cn.getConnection();

            String sql = "SELECT * FROM users WHERE app_role = 'therapist'";
            Statement s = cn1.createStatement();
            ResultSet r = s.executeQuery(sql);

            while(r.next()) {
                listview.add(new Therapist(
                        r.getString("username")
                ));
            }

            table_therapists.setItems(listview);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        table_therapists.getItems().addAll(therapist);
//        table_therapists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//                currentTherapist=table_therapists.getSelectionModel().getSelectedItem();
//                selected_th.setText(currentTherapist);
//            }
//        });
    }

    public void setUserInformation(String username, String app_role) throws IOException {
        label_welcome.setText("Welcome, " + username + "!");
    }
}
