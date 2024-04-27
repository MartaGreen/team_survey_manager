package employees;

import survey.SurveyManager;

public class Tester extends Employee{
    public Tester(String name, String surname, float experience, String team) {
        super(name, surname, experience, team);
    }

    Tester (String name, String surname, float experience, String team, SurveyManager surveyManager) {
        super(name, surname, experience, team, surveyManager);
    }
}
