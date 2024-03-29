package survey;

import account.User;
import employees.Employee;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.UUID;

public class Survey {
    private String surveyId;
    private String description;
    private String name;
    private User owner;
    private ArrayList<Employee> participants;
    private ArrayList<String> options;

    public Survey(String name, ArrayList<Employee> participants, ArrayList<String> options, User owner) {
        this.name = name;
        this.owner = owner;
        this.surveyId = UUID.randomUUID().toString();
        this.participants = participants;
        this.options = options;
    }

    public boolean containEmployee(Employee employee) {
        if (participants == null) return false;
        for (Employee empl: participants) {
            if (empl.getId().equals(employee.getId())) return true;
        }
        return false;
    }

    public boolean checkOwner(User checkedOwner) {
        return (owner.getName().equals(checkedOwner.getName()) && owner.getSurname().equals(checkedOwner.getSurname()));
    }

    public String getSurveyName() {
        return name;
    }
    public String getSurveyId() {return surveyId;}

    public ArrayList<String> getSurveyOption() {
        return this.options;
    }
}
