package pages.validation;

import javafx.scene.control.TextField;

public class ShortFieldHandler implements ErrorHandler{

    @Override
    public void validate(TextField field, String fieldName) {
        String text = field.getText();
        if (text.length() < 4) throw new ShortFieldException(fieldName, field);
    }
}
