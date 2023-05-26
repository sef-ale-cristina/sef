package com.example.sef_project;

import java.time.LocalDate;
import java.util.ArrayList;

public class Therapist {
    String name;
    String last_name;
    LocalDate birthdate;
    String phone;
    float price;
    ArrayList<String> working_days;
    String username;

    public Therapist(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
