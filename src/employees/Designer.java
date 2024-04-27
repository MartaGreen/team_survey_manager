package employees;

import survey.SurveyManager;

public class Designer extends Employee{
    public Designer(String name, String surname, float experience, String team) {
        super(name, surname, experience, team);
    }

    public Designer(String name, String surname, float experience, String team, SurveyManager surveyManager) {
        super(name, surname, experience, team, surveyManager);
    }

    //some specific Designer's methods
}
