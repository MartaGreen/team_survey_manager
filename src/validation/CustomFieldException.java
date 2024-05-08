package validation;

import javafx.scene.control.TextField;
import validation.components.ValidationComponent;

public class CustomFieldException extends Error{
    public CustomFieldException(String errMsg, ValidationComponent emptyField) {
        super(errMsg);
        emptyField.hightlight();
    }
}
