package corporation;

import employees.Employee;
import main.Main;
import survey.SurveyManager;

import java.util.ArrayList;

/**
 * Represents a corporation class.
 */
public class Corporation {
    private final String name;
    private final ArrayList<Employee> employees;
    public final SurveyManager surveyManager;

    /**
     * Constructor for a Corporation with specified attributes.
     * @param name The name of the corporation.
     * @param employees The list of employees in the corporation.
     * @param main The Main class instance.
     */
    public Corporation(String name, ArrayList<Employee> employees, Main main) {
        this.name = name;
        this.employees = employees;
        this.surveyManager = new SurveyManager(main);

        for (Employee empl: employees) {
            empl.setupSurveyManager(surveyManager);
        }
    }

    /**
     * Retrieves the name of the corporation.
     * @return The name of the corporation.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the list of employees in the corporation.
     * @return The list of employees in the corporation.
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}