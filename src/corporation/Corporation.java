package corporation;

import employees.Employee;
import javafx.collections.ObservableList;
import survey.SurveyManager;

import java.util.ArrayList;

public class Corporation {
    private final String name;
    private final ArrayList<Employee> employees;
    public final SurveyManager surveyManager;

    public Corporation(String name, ArrayList<Employee> employees) {
        this.name = name;
        this.employees = employees;
        this.surveyManager = new SurveyManager();

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

    public ArrayList<Employee> findEmployee(ObservableList<String> teams) {
        ArrayList<Employee> chosenEmployees = new ArrayList<>();
        for (Employee empl: employees) {
            if (teams.contains(empl.getTeam())) chosenEmployees.add(empl);
        }

        return chosenEmployees;
    }
}
