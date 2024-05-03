package pages.validation;

import javafx.scene.control.TextField;

public class EmptyFieldException extends CustomFieldException {
    public EmptyFieldException(String errMsg, TextField emptyField) {
        super(errMsg + " is empty", emptyField);
        setErrField(emptyField);
    }
}
