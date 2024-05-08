package validation;

import validation.components.ValidationComponent;

public class EmptyException extends CustomFieldException {
    public EmptyException(String errMsg, ValidationComponent emptyField) {
        super(errMsg + " is empty", emptyField);
    }
}
