package survey;

import employees.Employee;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class representing an option in a survey.
 */
public class SurveyOption {
    /** The list of employees who has voted for this option. */
    private final ArrayList<Employee> voters = new ArrayList<>();

    /** The name of the survey option. */
    private String name;

    /** The unique ID of the survey option. */
    private String optionId;

    /** Calculated percentage of votes for this option. */
    public double percentage;

    /**
     * Constructor for a survey option with the specified name and type.
     *
     * @param name The name of the survey option.
     */
    public SurveyOption(String name) {
        this.name = name;
        this.optionId = UUID.randomUUID().toString();
    }

    /**
     * Retrieves the unique ID of the survey option.
     *
     * @return The ID of the survey option.
     */
    public String getOptionId() {
        return optionId;
    }

    /**
     * Retrieves the name of the survey option.
     *
     * @return The name of the survey option.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a voter to the option's voted users array.
     *
     * @param voter The employee who is voting for the option.
     */
    public void addVoter(Employee voter) {
        voters.add(voter);
    }

    /**
     * Removes a voter from the option's voted users array.
     *
     * @param voterToRemove The employee whose vote is to be removed.
     */
    public void removeVoter(Employee voterToRemove) {
        voters.removeIf(voter -> voter.getId().equals(voterToRemove.getId()));
    }

    /**
     * Retrieves the number of voters for the survey option.
     *
     * @return The number of voters for the survey option.
     */
    public int getVotersSize() {
        return voters.size();
    }

    /**
     * Recalculates the percentage of votes for the survey option.
     *
     * @param participantsCount The total number of participants in the survey.
     */
    public void recalculate(int participantsCount) {
        percentage = participantsCount == 0 ? 0 : (double) voters.size() / participantsCount;
    }

    /**
     * Checks if a specific user has voted for the survey option.
     *
     * @param user The user to check for.
     * @return True if the user has voted for the option, false otherwise.
     */
    public boolean containsVoter(Employee user) {
        for (Employee voter : voters) {
            if (voter.getId().equals(user.getId())) return true;
        }

        return false;
    }
}