package com.example.sef_project;

import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.LocalDate;

public class Appointment_Creation {
    String therapist_username;
    String patient_username;
    String date;
    String begin;
    String end;

    public Appointment_Creation(String therapist_username, String patient_username, String date, String begin, String end) {
        this.therapist_username = therapist_username;
        this.patient_username = patient_username;
        this.date = date;
        this.begin = begin;
        this.end = end;
    }

    public String getTherapist_username() {
        return therapist_username;
    }

    public void setTherapist_username(String therapist_username) {
        this.therapist_username = therapist_username;
    }

    public String getPatient_username() {
        return patient_username;
    }

    public void setPatient_username(String patient_username) {
        this.patient_username = patient_username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public static void insertAppointment (String therapist_username, String pacient_username, LocalDate date, String begin, String end) throws SQLException, ClassNotFoundException {
        String insertStmt = "INSERT INTO appointments(therapist_username, pacient_username, date, begin_time, end_time, status) VALUES ('"+ therapist_username +"','"+ pacient_username +"','"+ date +"','"+begin+"','"+end+"','pending')";
        try {
            DBUtils.dbExecuteUpdate(insertStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
}

