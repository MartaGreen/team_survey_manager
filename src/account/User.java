package account;

import employees.Employee;
import survey.Survey;
import survey.SurveyManager;

import java.util.ArrayList;
import java.util.UUID;

public class User extends Employee {
    private ArrayList<Survey> surveys;
    private ArrayList<Survey> personalSurveys;
    private SurveyManager surveyManager;
    private final String userId;

    public User(Employee employee, SurveyManager surveyManager) {
        super(employee.getName(), employee.getSurname(), employee.getExperience(), employee.getTeam());
        this.surveyManager = surveyManager;
        this.userId = UUID.randomUUID().toString();

        setupSurveys();
    }

    public ArrayList<Survey> getPersonalSurveys() {
        return personalSurveys;
    }

    public void setupSurveys() {
        this.surveys = surveyManager.getEmployeeSurveys(this);
    }

    public void setupPersonalSurveys() {
        this.personalSurveys = surveyManager.getPersonalSurveys(this);
    }

    public void update() {
        setupSurveys();
        setupPersonalSurveys();
    }
}
