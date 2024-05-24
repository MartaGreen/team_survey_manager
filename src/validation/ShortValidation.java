package validation;

import validation.components.CTextField;

import java.util.ArrayList;

/**
 * Class for validating field for short length.
 *
 * @param <T> The type of field to be validated.
 */
public class ShortValidation<T> implements ErrorHandler<T>{
    /** Array of fields for validation */
    private final ArrayList<T> validationFields;

    /**
     * Constructor for a ShortValidation class.
     */
    public ShortValidation() {
        this.validationFields = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate() {
        validationFields.forEach(this::startValidation);
    }

    /**
     * Validates provided field for short length.
     * @param field field of custom type to be validated
     */
    private void startValidation(T field) {
        System.out.print(field + "\n");

        if (field instanceof CTextField textField) {
            textField.unhighlight();
            String fieldText = textField.getText();
            if (fieldText.length() < 4) throw new ShortException(textField.getName(), textField);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T field) {
        validationFields.add(field);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T field) {
        validationFields.remove(field);
    }
}