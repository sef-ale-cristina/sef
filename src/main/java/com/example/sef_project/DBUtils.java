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
                    LoggedInPacient loggedInPacient = loader.getController();
                    loggedInPacient.setUserInformation(username, app_role);
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
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String name, String last_name, LocalDate birthdate, String phone, String other, String username, String password, String app_role) {
        Connection connection = null;
        PreparedStatement psInsertUser = null;
        PreparedStatement psInsertPacient = null;
        PreparedStatement psInsertTherapist = null;
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
                psInsertUser = connection.prepareStatement("INSERT INTO users (username, password, app_role) VALUES (?, ?, ?)");
                psInsertUser.setString(1, username);
                psInsertUser.setString(2, password);
                psInsertUser.setString(3, app_role);
                psInsertUser.executeUpdate();
//                psInsert.setDate(3, Date.valueOf(birthdate));
//                psInsert.setString(4, phone);
//                psInsert.setString(5, other);
//                psInsert.setString(6, username);
//                psInsert.setString(7, password);
//                psInsert.setString(8, app_role);

                if(app_role.equals("pacient")) {
                    psInsertPacient = connection.prepareStatement("INSERT INTO patient (name, last_name, birthdate, phone, history) VALUES (?, ?, ?, ?, ?)");
                    psInsertPacient.setString(1, name);
                    psInsertPacient.setString(2, last_name);
                    psInsertPacient.setDate(3, Date.valueOf(birthdate));
                    psInsertPacient.setString(4, phone);
                    psInsertPacient.setString(5, other);
                    psInsertPacient.executeUpdate();
                    changeScene(event, "logged-in-pacient.fxml", "Welcome!", username, app_role);
                }
                else {
                    psInsertTherapist = connection.prepareStatement("INSERT INTO therapist (name, last_name, birthdate, phone, price) VALUES (?, ?, ?, ?, ?)");
                    psInsertTherapist.setString(1, name);
                    psInsertTherapist.setString(2, last_name);
                    psInsertTherapist.setDate(3, Date.valueOf(birthdate));
                    psInsertTherapist.setString(4, phone);
                    psInsertTherapist.setFloat(5, Float.valueOf(other));
                    psInsertTherapist.executeUpdate();
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

            if(psInsertUser != null) {
                try {
                    psInsertUser.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

            if(psInsertPacient != null) {
                try {
                    psInsertPacient.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

            if(psInsertTherapist != null) {
                try {
                    psInsertTherapist.close();
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

    public static void signUpTherapist(ActionEvent event, String name, String last_name, LocalDate birthdate, String phone, String price, String days, String start_time, String end_time, String username, String password, String app_role) {
        Connection connection = null;
        PreparedStatement psInsertUser = null;
        PreparedStatement psInsertTherapist = null;
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
                psInsertUser = connection.prepareStatement("INSERT INTO users (username, password, app_role) VALUES (?, ?, ?)");
                psInsertUser.setString(1, username);
                psInsertUser.setString(2, password);
                psInsertUser.setString(3, app_role);
                psInsertUser.executeUpdate();

                psInsertTherapist = connection.prepareStatement("INSERT INTO therapist (username, name, last_name, birthdate, phone, price, days, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                psInsertTherapist.setString(1, username);
                psInsertTherapist.setString(2, name);
                psInsertTherapist.setString(3, last_name);
                psInsertTherapist.setDate(4, Date.valueOf(birthdate));
                psInsertTherapist.setString(5, phone);
                psInsertTherapist.setFloat(6, Float.valueOf(price));
                psInsertTherapist.setString(7, days);
                psInsertTherapist.setString(8, start_time);
                psInsertTherapist.setString(9, end_time);
                psInsertTherapist.executeUpdate();

                changeScene(event, "logged-in-therapist.fxml", "Welcome!", username, app_role);
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

            if(psInsertUser != null) {
                try {
                    psInsertUser.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

            if(psInsertTherapist != null) {
                try {
                    psInsertTherapist.close();
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

    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sef", "root", "");
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
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