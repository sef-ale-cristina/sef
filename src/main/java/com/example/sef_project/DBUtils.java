package com.example.sef_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;


import java.io.IOException;
import java.time.LocalDate;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String app_role) {
        Parent root = null;

        if(username != null && app_role != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                if(app_role.equals("pacient")) {
                    LoggedInPacient loggedInPAcient = loader.getController();
                    loggedInPAcient.setUserInformation(username, app_role);
                }
                else {
                    LoggedInTherapist loggedInTherapist = loader.getController();
                    loggedInTherapist.setUserInformation(username, app_role);
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String name, String last_name, LocalDate birthdate, String phone, String other, String username, String password, String app_role) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sef", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            }
            else {
                psInsert = connection.prepareStatement("INSERT INTO users (name, last_name, birthdate, phone, other, username, password, app_role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                psInsert.setString(1, name);
                psInsert.setString(2, last_name);
                psInsert.setDate(3, Date.valueOf(birthdate));
                psInsert.setString(4, phone);
                psInsert.setString(5, other);
                psInsert.setString(6, username);
                psInsert.setString(7, password);
                psInsert.setString(8, app_role);
                psInsert.executeUpdate();

                if(app_role.equals("pacient")) {
                    changeScene(event, "logged-in-pacient.fxml", "Welcome!", username, app_role);
                }
                else {
                    changeScene(event, "logged-in-therapist.fxml", "Welcome!", username, app_role);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

            if(psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

            if(psInsert != null) {
                try {
                    psInsert.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sef", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password, app_role FROM users WHERE username =?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(("Provided credentials are incorrect!"));
                alert.show();
            }
            else {
                while(resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedAppRole = resultSet.getString("app_role");

                    if(retrievedPassword.equals(password)) {
                        if(retrievedAppRole.equals("pacient")) {
                            changeScene(event, "logged-in-pacient.fxml", "Welcome!", username, retrievedAppRole);
                        }
                        else {
                            changeScene(event, "logged-in-therapist.fxml", "Welcome!", username, retrievedAppRole);
                        }
                    }
                    else {
                        System.out.println("Passwords did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(("Provided credentials are incorrect!"));
                        alert.show();
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}