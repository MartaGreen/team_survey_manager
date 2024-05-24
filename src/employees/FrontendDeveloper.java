package employees;

import survey.SurveyManager;

/**
 * Employee subclass representing a frontend team employee.
 */
public class FrontendDeveloper extends Developer{
    /**
     * Constructor for a FrontendDeveloper object with specified attributes.
     * @param name The employee's name.
     * @param surname The employee's surname.
     * @param experience The employee's experience.
     * @param team The employee's team.
     */
    public FrontendDeveloper(String name, String surname, float experience, String team) {
        super(name, surname, experience, team);
    }

    /**
     * Constructor for a FrontendDeveloper object with specified attributes and associates it with a SurveyManager.
     * @param name The employee's name.
     * @param surname The employee's surname.
     * @param experience The employee's experience.
     * @param team The employee's team.
     * @param surveyManager The SurveyManager to associate the employee with.
     */
    public FrontendDeveloper(String name, String surname, float experience, String team, SurveyManager surveyManager) {
        super(name, surname, experience, team, surveyManager);
    }
}
