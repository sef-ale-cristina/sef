package com.example.sef_project;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;


import java.io.IOException;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String app_role) {
        Parent root = null;

        if(username != null && app_role != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LogInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username, app_role);
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

    public static void signUpUser(ActionEvent event, String username, String password, String app_role) {
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
                psInsert = connection.prepareStatement("INSERT INTO users (username, password, app_role) VALUES (?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, app_role);
                psInsert.executeUpdate();

                changeScene(event, "logged-in.fxml", "Welcome!", username, app_role);
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
                    String retrievedChannel = resultSet.getString("app_role");

                    if(retrievedPassword.equals(password)) {
                        changeScene(event, "logged-in.fxml", "Welcome!", username, retrievedChannel);
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

    public void table(Stage primaryStage) {
        TableView<Object> tableView = new TableView<>();

        // Establish the database connection
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sef", "root", ")) {
            Statement statement = connection.createStatement();

             // Retrieve data from the database
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

             // Get column names
             String[] columnNames = getColumnNames(resultSet);

            // Create table columns dynamically
            for (int i = 0; i < columnNames.length; i++) {
                final int columnIndex = i;
                TableColumn<String[], String> tableColumn = new TableColumn<>(columnNames[i]);
                tableColumn.setCellValueFactory(cellData -> {
                    String cellValue = cellData.getValue()[columnIndex];
                    return new SimpleStringProperty(cellValue);
                });
                tableView.getColumns().add(tableColumn);
            }

            // Populate the table with data
            while (resultSet.next()) {
                String[] rowData = new String[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    rowData[i] = resultSet.getString(i + 1);
                }
                tableView.getItems().add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String[] getColumnNames(ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = resultSet.getMetaData().getColumnLabel(i + 1);
        }
        return columnNames;
    }
}