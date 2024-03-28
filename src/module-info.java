module main.Main {
    requires javafx.fxml;
    requires  javafx.controls;
    requires  javafx.graphics;

    opens pages to javafx.fxml;
    exports pages;
    exports main;
    exports employees;
}