package com.example.sef_project;

public class Appointment_History {
    String pacient_username;

    String date;

    public Appointment_History(String pacient_username, String date) {
        this.pacient_username = pacient_username;
        this.date = date;
    }

    public String getPacient_username() {
        return pacient_username;
    }

    public void setPacient_username(String pacient_username) {
        this.pacient_username = pacient_username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
