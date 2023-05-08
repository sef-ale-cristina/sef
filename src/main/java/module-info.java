module com.example.sef_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sef_project to javafx.fxml;
    exports com.example.sef_project;
}