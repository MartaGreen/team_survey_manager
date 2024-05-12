package survey;

import employees.Employee;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Abstract class representing a survey.
 */
abstract public class Survey {
    private String surveyId;
    private String description;
    private final String name;
    private final Employee owner;
    private final ArrayList<String> participantTeams;
    protected ArrayList<SurveyOption> options;

    /**
     * Constructor for a survey with the specified parameters.
     *
     * @param name             The name of the survey.
     * @param participantTeams The names of the teams eligible to participate in the survey.
     * @param options          The options available in the survey.
     * @param owner            The owner of the survey.
     */
    public Survey(String name, ArrayList<String> participantTeams, ArrayList<String> options, Employee owner) {
        this.name = name;
        this.owner = owner;
        this.surveyId = UUID.randomUUID().toString();
        this.participantTeams = participantTeams;

        this.options = new ArrayList<>();
        for (String opt : options) {
            this.options.add(new SurveyOption(opt));
        }
    }

    /**
     * Checks if the survey contains a specific employee based on their team.
     *
     * @param employee The employee to check.
     * @return True if the employee's team is eligible to participate in the survey, false otherwise.
     */
    public boolean containEmployee(Employee employee) {
        if (participantTeams == null) return false;
        for (String team : participantTeams) {
            if (team.equals(employee.getTeam())) return true;
        }
        return false;
    }

    /**
     * Checks if a specified employee is the owner of the survey.
     *
     * @param checkedOwner The employee to check against the owner.
     * @return True if the specified employee is the owner, false otherwise.
     */
    public boolean checkOwner(Employee checkedOwner) {
        return (owner.getName().equals(checkedOwner.getName()) && owner.getSurname().equals(checkedOwner.getSurname()));
    }

    /**
     * Retrieves the name of the survey.
     *
     * @return The name of the survey.
     */
    public String getSurveyName() {
        return name;
    }

    /**
     * Retrieves the unique ID of the survey.
     *
     * @return The ID of the survey.
     */
    public String getSurveyId() {
        return surveyId;
    }

    /**
     * Retrieves the options available in the survey.
     *
     * @return The options available in the survey.
     */
    public ArrayList<SurveyOption> getSurveyOption() {
        return this.options;
    }

    /**
     * Method to vote in the survey.
     *
     * @param user      The employee who is voting.
     * @param optionsId The IDs of the options selected by the employee.
     */
    abstract public void vote(Employee user, ArrayList<String> optionsId);
}