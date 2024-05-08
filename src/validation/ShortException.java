package validation;

import validation.components.ValidationComponent;

public class ShortException extends CustomFieldException{
    public ShortException(String errMsg, ValidationComponent shortField) {
        super(errMsg + " is too short. Name of the field should be understandable!", shortField);
    }
}
