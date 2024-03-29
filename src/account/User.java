package account;

import employees.Employee;
import survey.Survey;
import survey.SurveyManager;

import java.util.ArrayList;

public class User extends Employee {
    private ArrayList<Survey> surveys;
    private ArrayList<Survey> personalSurveys;
    private SurveyManager surveyManager;
    private String userId;

    public User(Employee employee, SurveyManager surveyManager) {
        super(employee.getName(), employee.getSurname(), employee.getExperience(), employee.getTeam());
        this.surveyManager = surveyManager;
        this.setId(employee.getId());

        setupOpenedSurveys();
        setupPersonalSurveys();
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
