package employees;

import survey.SurveyManager;

public class Developer extends Employee{
    Developer(String name, String surname, float experience, String team) {
        super(name, surname, experience, team);
    }

    public Developer(String name, String surname, float experience, String team, SurveyManager surveyManager) {
        super(name, surname, experience, team, surveyManager);
    }

    //some specific Developer's methods
}
