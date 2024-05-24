package validation.components;

/**
 * Interface for validation components.
 */
public interface ValidationComponent {
    /**
     * Sets the name of the validation component.
     *
     * @param name The name to be set.
     */
    void setName(String name);

    /**
     * Retrieves the name of the validation component.
     *
     * @return The name of the validation component.
     */
    String getName();

    /** Highlights the validation component if error occurred. */
    void highlight();

    /** Removes the highlight from the validation component. */
    void unhighlight();
}