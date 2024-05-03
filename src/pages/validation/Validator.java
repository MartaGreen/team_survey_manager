package pages.validation;

import javafx.scene.control.TextField;

public class Validator {
    ErrorHandler handler;
    public Validator(ErrorHandler handler) {
        this.handler = handler;
    }

    public void validate(TextField field, String fieldName) {
        handler.validate(field, fieldName);
    }
}
