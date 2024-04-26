package survey;

import employees.Employee;

import java.util.ArrayList;
import java.util.UUID;

abstract public class Survey {
    private String surveyId;
    private String description;
    private String name;
    private Employee owner;
    private ArrayList<Employee> participants;
    protected ArrayList<SurveyOption> options;

    public Survey(String name, ArrayList<Employee> participants, ArrayList<String> optionss, Employee owner) {
        this.name = name;
        this.owner = owner;
        this.surveyId = UUID.randomUUID().toString();
        this.participants = participants;

        this.options = new ArrayList<>();
        for (String opt: optionss) {
            this.options.add(new SurveyOption(opt, "single"));
        }
    }

    public boolean containEmployee(Employee employee) {
        if (participants == null) return false;
        for (Employee empl: participants) {
            if (empl.getId().equals(employee.getId())) return true;
        }
        return false;
    }

    public boolean checkOwner(Employee checkedOwner) {
        return (owner.getName().equals(checkedOwner.getName()) && owner.getSurname().equals(checkedOwner.getSurname()));
    }

    public String getSurveyName() {
        return name;
    }
    public String getSurveyId() {return surveyId;}

    public ArrayList<SurveyOption> getSurveyOption() {
        return this.options;
    }

    public void vote(Employee user, String optionId) {}
}
