package survey;

import employees.Employee;

import java.util.ArrayList;

public class SingleChoiceSurvey extends Survey {
    SingleChoiceSurvey(String name, ArrayList<String> teams, ArrayList<String> options, Employee owner) {
        super(name, teams, options, owner);
    }

    @Override
    public void vote(Employee user, ArrayList<String> optionsId) {
        String optionId = optionsId.getFirst();

        int votesNumber = 0;
        for (SurveyOption option: options) {
            if (option.getOptionId().equals(optionId)) option.addVoter(user);
            else option.removeVoter(user);
            votesNumber += option.getVotersSize();
        }

        for (SurveyOption option: options) {
            option.recalculate(votesNumber);
        }
    }
}
