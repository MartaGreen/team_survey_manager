package validation;

/**
 * Interface for defining error validators.
 */
public interface ErrorHandler<T> {

    /**
     * Validates the fields.
     */
    void validate();

    /**
     * Adds a field to the validation fields array.
     *
     * @param field The field to be added.
     */
    void add(T field);

    /**
     * Deletes a field from the validation fields array.
     *
     * @param field The field to be deleted.
     */
    void delete(T field);
}