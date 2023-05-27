package com.example.sef_project;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;

import static com.mchange.v2.codegen.bean.BeangenUtils.capitalize;

public class LoggedInPacient implements Initializable {
    @FXML
    private TableView<Therapist> table_therapists;

    @FXML
    private Label selected_th;
    String currentTherapist;

    @FXML
    private DatePicker date_appointment;

    @FXML
    private ComboBox<String> cb_time_begin;

    @FXML
    private ComboBox<String> cb_time_end;

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

    ArrayList<String> therapists = new ArrayList<String>();

    String days = new String("");
    String begin_time = new String("");
    String end_time = new String("");

    String therapist_username = new String("");

    private Patient pacient = new Patient(null, null, null);
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

            String sql = "SELECT * FROM therapist";
            Statement s = cn1.createStatement();
            ResultSet r = s.executeQuery(sql);

            while(r.next()) {
                String username = r.getString("username");
                listview.add(new Therapist(
                        username
                ));

                therapists.add(username);
            }

            table_therapists.setItems(listview);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(therapists);
        cb_therapist.getItems().addAll(therapists);

        cb_therapist.setOnAction(comboBoxTherapistEvent -> {
            try {
                DBConnect cn = new DBConnect();
                Connection cn1 = cn.getConnection();

                therapist_username = therapist_username.concat(cb_therapist.getValue());

                String sql = "SELECT * FROM therapist WHERE username = '" + therapist_username + "'";
                Statement s = cn1.createStatement();
                ResultSet r = s.executeQuery(sql);

                while(r.next()) {
                    days = days.concat(r.getString("days"));
                    begin_time = begin_time.concat(r.getString("start_time"));
                    end_time = end_time.concat(r.getString("end_time"));
                }
                System.out.println(days);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            date_appointment.setOnAction(datePickerEvent -> {
                LocalDate localDate = date_appointment.getValue();
                String week_day = localDate.getDayOfWeek().toString();
                week_day = week_day.toLowerCase();
                week_day = capitalize(week_day);
                //System.out.println(week_day);

                System.out.println(days.contains(week_day));
                if(days.contains(week_day)==false) {
                    System.out.println("The day you want to enter is outside the working program of the therapist!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The day you want to enter is outside the working program of the therapist!");
                    alert.show();
                }

                cb_time_begin.getItems().addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00");

                cb_time_end.getItems().addAll("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00");

                cb_time_begin.setOnAction(timeBeginEvent -> {
                    cb_time_end.setOnAction(timeEndEvent -> {
                        String begin = cb_time_begin.getValue();
                        String end = cb_time_end.getValue();

                        if(begin.compareTo(end)==1) {
                            System.out.println("Insert correct times for an appointment!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Insert correct times for an appointment!");
                            alert.show();
                        }

                        if(begin.compareTo(begin_time)==-1 || begin.compareTo(end_time)==1 || end.compareTo(begin_time)==-1 || end.compareTo(end_time)==1) {
                            System.out.println("The time you selected is outside of the therapists's working hours!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("The time you selected is outside of the therapists's working hours!");
                            alert.show();
                        }

                        b_schedule.setOnAction(scheduleEvent -> {
                            try {
                                Appointment_Creation.insertAppointment(pacient.getUsername(), therapist_username, localDate, begin, end);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    });
                });
            });
        });
    }

    public void setUserInformation(String username, String app_role) throws IOException {
        label_welcome.setText("Welcome, " + username + "!");
        pacient.setUsername(username);
    }
}
