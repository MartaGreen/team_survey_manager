package employees;

import survey.Survey;
import survey.SurveyManager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Abstract class representing an employee.
 */
public abstract class Employee implements SurveyObserver {
    private String id;
    private String name;
    private String surname;
    private float experience;
    private String team;
    private ArrayList<Survey> surveys;
    private ArrayList<Survey> personalSurveys;
    private SurveyManager surveyManager;

    /**
     * Constructor for an Employee object with specified attributes.
     * @param name The employee's name.
     * @param surname The employee's surname.
     * @param experience The employee's experience.
     * @param team The employee's team.
     */
    protected Employee(String name, String surname, float experience, String team) {
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.team = team;
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Constructor for an Employee object with specified attributes and associates it with a SurveyManager.
     * @param name The employee's name.
     * @param surname The employee's surname.
     * @param experience The employee's experience.
     * @param team The employee's team.
     * @param surveyManager The SurveyManager to associate the employee with.
     */
    protected Employee(String name, String surname, float experience, String team, SurveyManager surveyManager) {
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.team = team;
        this.id = UUID.randomUUID().toString();
        setupSurveyManager(surveyManager);
    }

    /**
     * Associates the employee with a SurveyManager and subscribes to updates.
     * @param surveyManager The SurveyManager to associate the employee with.
     */
    public void setupSurveyManager(SurveyManager surveyManager) {
        this.surveyManager = surveyManager;
        this.surveyManager.subscribe(this);
        update();
    }

    /**
     * Retrieves the employee's ID.
     * @return The employee's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the employee's ID.
     * @param newId The new ID to set.
     */
    public void setId(String newId) {
        this.id = newId;
    }

    /**
     * Retrieves the employee's name.
     * @return The employee's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the employee's name.
     * @param newName The new name to set.
     */
    protected void setName(String newName) {
        name = newName;
    }

    /**
     * Retrieves the employee's surname.
     * @return The employee's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the employee's surname.
     * @param newSurname The new surname to set.
     */
    protected void setSurname(String newSurname) {
        surname = newSurname;
    }

    /**
     * Retrieves the employee's experience.
     * @return The employee's experience.
     */
    public float getExperience() {
        return experience;
    }

    /**
     * Sets the employee's experience.
     * @param newExperience The new experience to set.
     */
    protected void setExperience(float newExperience) {
        experience = newExperience;
    }

    /**
     * Retrieves the employee's team.
     * @return The employee's team.
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets the employee's team.
     * @param newTeam The new team to set.
     */
    protected void setTeam(String newTeam) {
        team = newTeam;
    }

    /**
     * Compares the employee's name and surname with specified name and surname.
     * @param name The name to compare.
     * @param surname The surname to compare.
     * @return True if the names and surnames match, false otherwise.
     */
    public boolean compareEmployee(String name, String surname) {
        return (this.getName().equals(name) && this.getSurname().equals(surname));
    }

    /**
     * Compares the employee's full name with the specified full name.
     * @param fullName The full name to compare.
     * @return True if the full names match, false otherwise.
     */
    public boolean compareEmployee(String fullName) {
        return ((this.getName() + " " + this.getSurname()).equals(fullName));
    }

    /**
     * Retrieves the personal surveys assigned to the employee.
     * @return The personal surveys assigned to the employee.
     */
    public ArrayList<Survey> getPersonalSurveys() {
        return personalSurveys;
    }

    /**
     * Retrieves the opened surveys assigned to the employee.
     * @return The opened surveys assigned to the employee.
     */
    public ArrayList<Survey> getOpenedSurveys() {
        return surveys;
    }

    /**
     * Sets up the opened surveys assigned to the employee.
     */
    public void setupOpenedSurveys() {
        this.surveys = surveyManager.getEmployeeSurveys(this);
    }

    /**
     * Sets up the personal surveys assigned to the employee.
     */
    public void setupPersonalSurveys() {
        this.personalSurveys = surveyManager.getPersonalSurveys(this);
    }

    /**
     * Updates the employee's surveys.
     */
    public void update() {
        setupOpenedSurveys();
        setupPersonalSurveys();
    }
}