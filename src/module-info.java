module main.Main {
    requires javafx.fxml;
    requires  javafx.controls;
    requires  javafx.graphics;

    opens main to javafx.fxml;
    exports main;
}