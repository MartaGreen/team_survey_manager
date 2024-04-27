package employees;

import survey.SurveyManager;

public class Product extends Employee{
    public Product(String name, String surname, float experience, String team) {
        super(name, surname, experience, team);
    }

    public Product(String name, String surname, float experience, String team, SurveyManager surveyManager) {
        super(name, surname, experience, team, surveyManager);
    }

    //some specific Product's methods
}
