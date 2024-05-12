package corporation;

import employees.Employee;
import main.Main;
import survey.SurveyManager;

import java.util.ArrayList;

public class Corporation {
    private final String name;
    private final ArrayList<Employee> employees;
    public final SurveyManager surveyManager;

    public Corporation(String name, ArrayList<Employee> employees, Main main) {
        this.name = name;
        this.employees = employees;
        this.surveyManager = new SurveyManager(main);

        for (Employee empl: employees) {
            empl.setupSurveyManager(surveyManager);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
