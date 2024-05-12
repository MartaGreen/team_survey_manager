package survey;

import employees.Employee;

import java.util.ArrayList;

/**
 * Class representing a multiple-choice survey.
 */
public class MultipleChoiceSurvey extends Survey {

    /**
     * Constructor for a multiple-choice survey with the specified parameters.
     *
     * @param name             The name of the survey.
     * @param teams The names of the teams eligible to participate in the survey.
     * @param options          The options available in the survey.
     * @param owner            The owner of the survey.
     */
    MultipleChoiceSurvey(String name, ArrayList<String> teams, ArrayList<String> options, Employee owner) {
        super(name, teams, options, owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void vote(Employee user, ArrayList<String> optionsId) {
        int votesNumber = 0;
        for (SurveyOption option: options) {
            option.removeVoter(user);
            if (optionsId.contains(option.getOptionId())) option.addVoter(user);
            votesNumber += option.getVotersSize();
        }

        for (SurveyOption option: options) {
            option.recalculate(votesNumber);
        }
    }
}
