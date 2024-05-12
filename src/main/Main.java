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
    private Corporation corporation;
    private Employee currentUser;
    private SurveyManager surveyManager;
    public Survey currentSurvey;
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

        // Create some basic users
        employees.add(new BackendDeveloper("John", "Doe", 5.5f, "backend"));
        // Add more employees...

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