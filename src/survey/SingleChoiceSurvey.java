package survey;

import account.User;
import employees.Employee;

import java.util.ArrayList;

public class SingleChoiceSurvey extends Survey {
    SingleChoiceSurvey(String name, ArrayList<Employee> participants, ArrayList<String> optionss, User owner) {
        super(name, participants, optionss, owner);
    }

    public void vote(User user, String optionId) {
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
