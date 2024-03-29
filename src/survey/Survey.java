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

    public Survey(String name, ArrayList<Employee> participants, User owner) {
        this.name = name;
        this.owner = owner;
        this.surveyId = UUID.randomUUID().toString();
        this.participants = participants;
    }

    public boolean containEmployee(Employee employee) {
        System.out.print("adfdf" + this.participants + " " + "\n");
        if (participants == null) return false;
        for (Employee empl: participants) {
            System.out.printf("%s %s %s %s\n", empl.getId(), empl.getName(), employee.getId(), employee.getName());
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
}
