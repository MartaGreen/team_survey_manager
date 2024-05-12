package validation;

import validation.components.ValidationComponent;

/**
 * Custom short length exception class for creation field with short length exceptions.
 */
public class ShortException extends CustomFieldException{
    /**
     * Constructor for a ShortException with an error message and highlighting of the empty field.
     *
     * @param errMsg      The error message to be displayed.
     * @param shortField  The empty field causing the exception.
     */
    public ShortException(String errMsg, ValidationComponent shortField) {
        super(errMsg + " is too short. Name of the field should be understandable!", shortField);
    }
}
