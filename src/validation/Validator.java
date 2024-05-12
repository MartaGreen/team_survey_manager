package validation;

/**
 * Strategy class to chose validation strategy.
 *
 * @param <T> The type of field to be validated.
 */
public class Validator<T> {
    private final ErrorHandler<T> validator;

    /**
     * Constructor for a Validator Validator.
     *
     * @param handler Validation strategy to be used for validation.
     */
    public Validator(ErrorHandler<T> handler) {
        this.validator = handler;
    }

    /**
     * Validates the fields using the chosen strategy.
     */
    public void validate() {
        validator.validate();
    }

    /**
     * Adds a field to the validation fields array.
     *
     * @param field The field to be added for validation.
     */
    public void add(T field) {
        this.validator.add(field);
    }

    /**
     * Deletes a field from the validation fields array.
     *
     * @param field The field to be removed from validation.
     */
    public void delete(T field) {
        this.validator.delete(field);
    }
}