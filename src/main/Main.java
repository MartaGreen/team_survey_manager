package main;

import corporation.Corporation;
import employees.*;
import javafx.application.Application;
import javafx.stage.Stage;
import pages.Router;
import survey.Survey;
import survey.SurveyManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class of the application responsible for storing information about app state
 */
public class Main extends Application {
    /**
     * The corporation instance representing the organization.
     */
    private Corporation corporation;

    /**
     * The currently logged-in(chosen) user.
     */
    private Employee currentUser;

    /**
     * The manager responsible for handling surveys in current corporation.
     */
    private SurveyManager surveyManager;

    /**
     * The current survey that was opened by user.
     */
    public Survey currentSurvey;

    /**
     * The router for managing page navigation.
     */
    public Router router;

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application by loading the initial page.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void start(Stage stage) throws IOException {
        generateDefaultCorp();
        this.router = new Router(this);
        this.router.loadInitialPage(stage);
    }

    /**
     * Generates a default corporation with sample employees.
     */
    private void generateDefaultCorp() {
        ArrayList<Employee> employees = new ArrayList<>();

        // create some basic users
        employees.add(new BackendDeveloper("John", "Doe", 5.5f, "backend"));
        employees.add(new FrontendDeveloper("Jane", "Smith", 3.0f, "frontend"));
        employees.add(new Designer("Michael", "Johnson", 7.2f, "design"));
        employees.add(new Product("Emily", "Williams", 4.8f, "product"));
        employees.add(new Tester("David", "Brown", 10.0f, "tester"));
        employees.add(new BackendDeveloper("Sarah", "Anderson", 2.3f, "backend"));
        employees.add(new FrontendDeveloper("Alex", "Martinez", 6.7f, "frontend"));
        employees.add(new Designer("Olivia", "Garcia", 8.9f, "design"));
        employees.add(new Product("William", "Rodriguez", 1.2f, "product"));
        employees.add(new Manager("Sophia", "Lopez", 9.4f, "manager"));
        employees.add(new BackendDeveloper("Ethan", "Thomas", 4.0f, "backend"));
        employees.add(new FrontendDeveloper("Ava", "Lee", 6.5f, "frontend"));
        employees.add(new Designer("Noah", "Harris", 7.8f, "design"));
        employees.add(new Product("Emma", "Clark", 3.6f, "product"));
        employees.add(new Tester("James", "Lewis", 11.2f, "tester"));
        employees.add(new BackendDeveloper("Mia", "Walker", 5.9f, "backend"));
        employees.add(new FrontendDeveloper("Liam", "Hall", 2.1f, "frontend"));
        employees.add(new Designer("Charlotte", "Allen", 9.3f, "design"));
        employees.add(new Product("Elijah", "Young", 4.7f, "product"));
        employees.add(new Manager("Amelia", "Wright", 8.0f, "manager"));
        employees.add(new BackendDeveloper("Benjamin", "King", 6.8f, "backend"));
        employees.add(new FrontendDeveloper("Harper", "Green", 1.5f, "frontend"));
        employees.add(new Designer("Lucas", "Baker", 7.6f, "design"));
        employees.add(new Product("Mia", "Carter", 2.9f, "product"));
        employees.add(new Manager("Evelyn", "Adams", 10.5f, "manager"));

        Corporation corp = new Corporation("apple", employees, this);
        setCorporation(corp);
        setCurrentUser(employees.getFirst());
    }
    /**
     * Sets the corporation for the application.
     *
     * @param corp The corporation to be set.
     */
    public void setCorporation(Corporation corp) {
        corporation = corp;
        surveyManager = corp.surveyManager;
    }

    /**
     * Sets the current user.
     *
     * @param employee The current user to be set.
     */
    public void setCurrentUser(Employee employee) {
        currentUser = employee;
    }

    /**
     * Sets the current user based on their full name.
     *
     * @param fullName The full name of the user.
     */
    public void setCurrentUser(String fullName) {
        for (Employee employee : corporation.getEmployees()) {
            if (employee.compareEmployee(fullName)) {
                currentUser = employee;
                return;
            }
        }
    }

    /**
     * Gets the current user.
     *
     * @return The current user.
     */
    public Employee getCurrentUser() {
        return currentUser;
    }

    /**
     * Gets the corporation.
     *
     * @return The corporation.
     */
    public Corporation getCorporation() {
        return corporation;
    }

    /**
     * Gets the survey manager.
     *
     * @return The survey manager.
     */
    public SurveyManager getSurveyManager() {
        return surveyManager;
    }
}