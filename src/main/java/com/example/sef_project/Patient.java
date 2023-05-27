package com.example.sef_project;

public class Patient {
    private String username;
    private String name;
    private String last_name;

    public Patient(String username, String name, String last_name) {
        this.username= username;
        this.name = name;
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
