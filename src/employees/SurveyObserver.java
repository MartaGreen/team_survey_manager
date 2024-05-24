package employees;

/**
 * Interface for objects that observe surveys and need to be notified of updates.
 */
public interface SurveyObserver {
    /**
     * Method called when a survey update occurs.
     */
    void update();
}