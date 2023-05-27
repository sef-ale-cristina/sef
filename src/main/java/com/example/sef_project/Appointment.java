package com.example.sef_project;

public class Appointment {
    String beginTime;

    String endTime;

    String therapist_username;

    String pacient_username;

    String status;

    public Appointment(String beginTime, String endTime, String pacient_username) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        //this.therapist_username = therapist_username;
        this.pacient_username = pacient_username;
        //this.status = status;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTherapist_username() {
        return therapist_username;
    }

    public void setTherapist_username(String therapist_username) {
        this.therapist_username = therapist_username;
    }

    public String getPacient_username() {
        return pacient_username;
    }

    public void setPacient_username(String pacient_username) {
        this.pacient_username = pacient_username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
