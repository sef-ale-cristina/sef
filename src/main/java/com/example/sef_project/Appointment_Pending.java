package com.example.sef_project;

import java.sql.SQLException;

public class Appointment_Pending {
    int id;
    String patient_username;
    String date;
    String begin;
    String end;

    public Appointment_Pending(int id, String patient_username, String date, String begin, String end) {
        this.id = id;
        this.patient_username = patient_username;
        this.date = date;
        this.begin = begin;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static void updateStatus (int id, String new_status) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE appointments SET status='" + new_status + "' WHERE id = '" + id + "'";
        try {
            DBUtils.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
}
