package survey;

import employees.Employee;

import java.util.ArrayList;

public class MultipleChoiceSurvey extends Survey {

    MultipleChoiceSurvey(String name, ArrayList<String> teams, ArrayList<String> options, Employee owner) {
        super(name, teams, options, owner);
    }

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
