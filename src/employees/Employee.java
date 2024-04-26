package employees;

import survey.Survey;
import survey.SurveyManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public abstract class Employee{
    private String id;
    private String name;
    private String surname;
    private float experience;
    private String team;
    private ArrayList<Survey> surveys;
    private ArrayList<Survey> personalSurveys;
    private SurveyManager surveyManager;

    protected Employee(String name, String surname, float experience, String team) {
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.team = team;
        this.id = UUID.randomUUID().toString();
    }

    public void setupSurveyManager(SurveyManager surveyManager) {
        this.surveyManager = surveyManager;

        setupOpenedSurveys();
        setupPersonalSurveys();
    }

    public String getId() {
        return id;
    }
    public void setId(String newId) {
        this.id = newId;
    }

    public String getName() {
        return name;
    }
    protected void setName(String newName) {
        name = newName;
    }

    public String getSurname() {
        return surname;
    }
    protected void setSurname(String newSurname) {
        surname = newSurname;
    }

    public float getExperience() {
        return experience;
    }
    protected void setExperience(float newExperience) {
        experience = newExperience;
    }

    public String getTeam() {
        return team;
    }
    protected void setTeam(String newTeam){
        team = newTeam;
    }

    //return 1 if it is current employee
    // return 0 if it isn't current employee
    public boolean compareEmployee(String name, String surname) {
        return (this.getName().equals(name) && this.getSurname().equals(surname));
    }
    public boolean compareEmployee(String fullName) {
        return ((this.getName() + " " + this.getSurname()).equals(fullName));
    }

    public ArrayList<Survey> getPersonalSurveys() {
        return personalSurveys;
    }
    public ArrayList<Survey> getOpenedSurveys() { return surveys; }

    public void setupOpenedSurveys() {
        this.surveys = surveyManager.getEmployeeSurveys(this);
    }

    public void setupPersonalSurveys() {
        this.personalSurveys = surveyManager.getPersonalSurveys(this);
    }

    public void update() {
        setupOpenedSurveys();
        setupPersonalSurveys();
    }
}
