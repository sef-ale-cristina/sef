package com.example.sef_project;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoggedInTherapist implements Initializable {
    @FXML
    private Label l_welcome;

    @FXML
    private Button b_logout;

    @FXML
    private TableView<Appointment> t_today;

    @FXML
    private TableColumn<Appointment, String> t_start;

    @FXML
    private TableColumn<Appointment, String> t_end;

    @FXML
    private TableColumn<Appointment, String> t_patient;

    @FXML
    private Button b_today_app;

    @FXML
    private TableView<Appointment_History> t_history;

    @FXML
    private TableColumn<Appointment_History, String> t_date;

    @FXML
    private TableColumn<Appointment_History, String> t_pacient;

    @FXML
    private Button b_history;

    ObservableList<Appointment> listview_app = FXCollections.observableArrayList();

    ObservableList<Appointment_History> listview_hist = FXCollections.observableArrayList();

    private Therapist therapist = new Therapist();


    public void setUserInformation(String username, String app_role) throws IOException {
        l_welcome.setText("Welcome, " + username + "!");
        therapist.setUsername(username);
        System.out.println(therapist.getUsername());
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        b_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "log-in.fxml", "Log In!", null, null);
            }
        });

        b_today_app.setOnAction(event -> {
            t_start.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBeginTime()));
            t_end.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndTime()));
            t_patient.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPacient_username()));

            try {
                t_today.setVisible(true);
                DBConnect cn = new DBConnect();
                Connection cn1 = cn.getConnection();

                String therapist_username = therapist.getUsername();

                String sql = "SELECT * FROM appointments WHERE therapist_username = '" + therapist_username + "' AND date = CAST( NOW() AS Date )";
                Statement s = cn1.createStatement();
                ResultSet r = s.executeQuery(sql);

                while(r.next()) {
                    listview_app.add(new Appointment(
                            r.getString("begin_time"),
                            r.getString("end_time"),
                            r.getString("pacient_username")
                    ));
                }

                t_today.setItems(listview_app);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        b_history.setOnAction(event -> {
            t_date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
            t_pacient.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPacient_username()));

            try {
                t_history.setVisible(true);
                DBConnect cn = new DBConnect();
                Connection cn1 = cn.getConnection();

                String therapist_username = therapist.getUsername();

                String sql = "SELECT * FROM appointments WHERE therapist_username = '" + therapist_username + "' AND date < CAST( NOW() AS Date )";
                Statement s = cn1.createStatement();
                ResultSet r = s.executeQuery(sql);

                while(r.next()) {
                    listview_hist.add(new Appointment_History(
                            r.getString("pacient_username"),
                            r.getString("date")
                    ));
                }

                t_history.setItems(listview_hist);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
