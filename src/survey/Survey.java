package survey;

import employees.Employee;

import java.util.ArrayList;
import java.util.UUID;

abstract public class Survey {
    private String surveyId;
    private String description;
    private final String name;
    private final Employee owner;
    private final ArrayList<String> participantTeams;
    protected ArrayList<SurveyOption> options;

    public Survey(String name, ArrayList<String> participantTeams, ArrayList<String> options, Employee owner) {
        this.name = name;
        this.owner = owner;
        this.surveyId = UUID.randomUUID().toString();
        this.participantTeams = participantTeams;

        this.options = new ArrayList<>();
        for (String opt: options) {
            this.options.add(new SurveyOption(opt, "single"));
        }
    }

    public boolean containEmployee(Employee employee) {
        if (participantTeams == null) return false;
        for (String team: participantTeams) {
            if (team.equals(employee.getTeam())) return true;
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

    public void vote(Employee user, ArrayList<String> optionsId) {}
}
