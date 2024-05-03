package pages.validation;

import javafx.scene.control.TextField;

public class EmptyFieldHandler implements ErrorHandler{
    @Override
    public void validate(TextField field, String fieldName) {
        String text = field.getText();
        if (text.isEmpty()) throw new EmptyFieldException(fieldName, field);
    }
}
