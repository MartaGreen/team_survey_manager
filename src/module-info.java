module main.Main {
    requires javafx.fxml;
    requires  javafx.controls;
    requires  javafx.graphics;

    opens pages to javafx.fxml;
    opens validation to javafx.fxml;
    exports pages;
    exports main;
    exports corporation;
    exports employees;
    exports survey;
    exports validation.components;
}