package employees;

import survey.SurveyManager;

public class FrontendDeveloper extends Developer{
    public FrontendDeveloper(String name, String surname, float experience, String team) {
        super(name, surname, experience, team);
    }

    public FrontendDeveloper(String name, String surname, float experience, String team, SurveyManager surveyManager) {
        super(name, surname, experience, team, surveyManager);
    }
}
