package survey;

import account.User;
import employees.Employee;

import java.util.ArrayList;

public class SurveyManager {
    ArrayList<Survey> surveys = new ArrayList<>();

    public ArrayList<Survey> getEmployeeSurveys(User user) {
        if (surveys == null) return null;

        ArrayList<Survey> employeeSurveys = new ArrayList<>();

        for (Survey survey: surveys) {
            if (survey.containEmployee(user)) employeeSurveys.add(survey);
        }

        return employeeSurveys;
    }

    public ArrayList<Survey> getPersonalSurveys(User user) {
        if (surveys == null) return null;

        ArrayList<Survey> personalSurveys = new ArrayList<>();
        for (Survey survey: surveys) {
            if (survey.checkOwner(user)) {
                personalSurveys.add(survey);
            }
        }

        return personalSurveys;
    }

    public void createNewSurvey(String name, ArrayList<String> options, User owner) {
        Survey newSurvey = new Survey(name, options, owner);
        surveys.add(newSurvey);
    }
}
