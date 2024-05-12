package validation;

import validation.components.ValidationComponent;

/**
 * Custom empty exception class for creation empty field exceptions.
 */
public class EmptyException extends CustomFieldException {
    /**
     * Constructor for a EmptyException with an error message and highlighting of the empty field.
     *
     * @param errMsg      The error message to be displayed.
     * @param emptyField  The empty field causing the exception.
     */
    public EmptyException(String errMsg, ValidationComponent emptyField) {
        super(errMsg + " is empty", emptyField);
    }
}
