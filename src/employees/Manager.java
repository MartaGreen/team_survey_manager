package employees;

import survey.SurveyManager;

/**
 * Employee subclass representing a manager team employee.
 */
public class Manager extends Employee{
    /**
     * Constructor for a Manager object with specified attributes.
     * @param name The employee's name.
     * @param surname The employee's surname.
     * @param experience The employee's experience.
     * @param team The employee's team.
     */
    public Manager(String name, String surname, float experience, String team) {
        super(name, surname, experience, team);
    }

    /**
     * Constructor for a Manager object with specified attributes and associates it with a SurveyManager.
     * @param name The employee's name.
     * @param surname The employee's surname.
     * @param experience The employee's experience.
     * @param team The employee's team.
     * @param surveyManager The SurveyManager to associate the employee with.
     */
    public Manager(String name, String surname, float experience, String team, SurveyManager surveyManager) {
        super(name, surname, experience, team, surveyManager);
    }

    //some specific Manager's methods
}
