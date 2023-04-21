module com.example.monty_hall_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.monty_hall_project to javafx.fxml;
    exports com.example.monty_hall_project;
}