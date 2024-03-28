package corporation;

import employees.Employee;
import survey.SurveyManager;

import java.util.ArrayList;

import static employees.Employee.compareEmployee;

public class Corporation {
    private final String name;
    private final ArrayList<Employee> employees;
    public final SurveyManager surveyManager;

    public Corporation(String name, ArrayList<Employee> employees) {
        this.name = name;
        this.employees = employees;
        this.surveyManager = new SurveyManager();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public Employee findEmployee(String name, String surname) {
        for (Employee employee: this.getEmployees()) {
            if (compareEmployee(employee, name, surname)) {
                return employee;
            }
        }

        return null;
    }
}
