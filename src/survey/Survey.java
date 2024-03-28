package survey;

import account.User;
import employees.Employee;

import java.util.ArrayList;
import java.util.UUID;

public class Survey {
    private String surveyId;
    private ArrayList<Employee> participants;
    private String description;
    private String name;
    private User owner;
    private ArrayList<SurveyOption> options;

    Survey(String name, String description, ArrayList<Employee> participants) {
        this.name = name;
        this.description = description;
        this.participants = participants;
        this.surveyId = UUID.randomUUID().toString();
    }

    public Survey(String name, ArrayList<String> options, User owner) {
        this.name = name;
        this.owner = owner;
        this.surveyId = UUID.randomUUID().toString();
        this.options = new ArrayList<>();

        for (String option: options) {
            this.options.add(new SurveyOption(option, "singleChoice"));
        }
    }

    public boolean containEmployee(Employee employee) {
        if (participants == null) return false;
        return participants.contains(employee);
    }

    public boolean checkOwner(User checkedOwner) {
        return (owner.getName().equals(checkedOwner.getName()) && owner.getSurname().equals(checkedOwner.getSurname()));
    }

    public String getSurveyName() {
        return name;
    }
    public String getSurveyId() {return surveyId;}
}
