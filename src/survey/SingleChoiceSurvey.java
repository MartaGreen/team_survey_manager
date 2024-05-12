package survey;

import employees.Employee;

import java.util.ArrayList;

/**
 * Class representing a single-choice survey.
 */
public class SingleChoiceSurvey extends Survey {

    /**
     * Constructor for a single-choice survey with the specified parameters.
     *
     * @param name             The name of the survey.
     * @param teams The names of the teams eligible to participate in the survey.
     * @param options          The options available in the survey.
     * @param owner            The owner of the survey.
     */
    SingleChoiceSurvey(String name, ArrayList<String> teams, ArrayList<String> options, Employee owner) {
        super(name, teams, options, owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void vote(Employee user, ArrayList<String> optionsId) {
        String optionId = optionsId.getFirst();

        int votesNumber = 0;
        for (SurveyOption option : options) {
            if (option.getOptionId().equals(optionId)) option.addVoter(user);
            else option.removeVoter(user);
            votesNumber += option.getVotersSize();
        }

        for (SurveyOption option : options) {
            option.recalculate(votesNumber);
        }
    }
}