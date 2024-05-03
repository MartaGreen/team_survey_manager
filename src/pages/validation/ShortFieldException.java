package pages.validation;

import javafx.scene.control.TextField;

public class ShortFieldException extends CustomFieldException{
    public ShortFieldException(String errMsg, TextField shortField) {
        super(errMsg + " is too short. Name of the field should be understandable!", shortField);
        setErrField(shortField);
    }
}
