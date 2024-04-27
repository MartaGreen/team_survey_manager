package employees;

import survey.SurveyManager;

public class BackendDeveloper extends Developer{
    public BackendDeveloper(String name, String surname, float experience, String team) {
        super(name, surname, experience, team);
    }

    public BackendDeveloper(String name, String surname, float experience, String team, SurveyManager surveyManager) {
        super(name, surname, experience, team, surveyManager);
    }
}
