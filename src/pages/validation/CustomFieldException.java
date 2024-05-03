package pages.validation;

import javafx.scene.control.TextField;

public class CustomFieldException extends Error{
    public TextField errField;
    public CustomFieldException(String errMsg, TextField emptyField) {
        super(errMsg);
        setErrField(emptyField);
    }
    public void setErrField(TextField field) {
        errField = field;
    }
    public TextField getErrField() {
        return this.errField;
    }
}
