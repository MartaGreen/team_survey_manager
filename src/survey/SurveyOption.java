package survey;

import employees.Employee;

import java.util.ArrayList;

public class SurveyOption {
   private final ArrayList<Employee> voters = new ArrayList<>();
    private String text;

    private String name;
    private String type;

    public SurveyOption(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void addVoter(Employee voter) {
        voters.add(voter);
    }

    public void removeVoter(Employee voterToRemove) {
        voters.remove(voterToRemove);
    }
}
