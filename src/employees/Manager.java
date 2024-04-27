package employees;

import survey.SurveyManager;

public class Manager extends Employee{
    public Manager(String name, String surname, float experience, String team) {
        super(name, surname, experience, team);
    }

    public Manager(String name, String surname, float experience, String team, SurveyManager surveyManager) {
        super(name, surname, experience, team, surveyManager);
    }

    //some specific Manager's methods
}
