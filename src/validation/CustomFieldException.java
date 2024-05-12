package validation;

import validation.components.ValidationComponent;

/**
 * Custom abstract exception class for creation custom exceptions.
 */
public abstract class CustomFieldException extends Error {

    /**
     * Constructor for a CustomFieldException with an error message and highlighting of the empty field.
     *
     * @param errMsg      The error message to be displayed.
     * @param emptyField  The empty field causing the exception.
     */
    public CustomFieldException(String errMsg, ValidationComponent emptyField) {
        super(errMsg);
        emptyField.hightlight(); // Highlight the empty field
    }
}