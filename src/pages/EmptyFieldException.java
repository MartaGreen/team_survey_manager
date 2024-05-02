package pages;

import javafx.scene.control.TextField;

public class EmptyFieldException extends Exception {
    private TextField emptyField;

    EmptyFieldException(String errMsg, TextField emptyField) {
        super(errMsg + "is empty");
        setupEmptyField(emptyField);
    }

    public void setupEmptyField(TextField field) {
        emptyField = field;
    }
    public TextField getEmptyField() {
        return this.emptyField;
    }
}
