package main;

import corporation.Corporation;
import employees.*;
import javafx.application.Application;
import javafx.stage.Stage;
import survey.Survey;
import survey.SurveyManager;

import java.io.IOException;
import java.util.ArrayList;

import static pages.Router.loadInitialPage;

public class Main extends Application {
    private static Corporation corporation;
    private static Employee currentUser;
    private static SurveyManager surveyManager;
    public static Survey currentSurvey;

    public static void main(String[] args) {
        generateDefaultCorp();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        loadInitialPage(stage);
    }

    private static void generateDefaultCorp() {
        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new BackendDeveloper("John", "Doe", 5.5f, "backend"));
        employees.add(new FrontendDeveloper("Jane", "Smith", 3.0f, "frontend"));
        employees.add(new Designer("Michael", "Johnson", 7.2f, "design"));
        employees.add(new Product("Emily", "Williams", 4.8f, "product"));
        employees.add(new Manager("David", "Brown", 10.0f, "account"));
        employees.add(new BackendDeveloper("Sarah", "Anderson", 2.3f, "backend"));
        employees.add(new FrontendDeveloper("Alex", "Martinez", 6.7f, "frontend"));
        employees.add(new Designer("Olivia", "Garcia", 8.9f, "design"));
        employees.add(new Product("William", "Rodriguez", 1.2f, "product"));
        employees.add(new Manager("Sophia", "Lopez", 9.4f, "manager"));
        employees.add(new BackendDeveloper("Ethan", "Thomas", 4.0f, "backend"));
        employees.add(new FrontendDeveloper("Ava", "Lee", 6.5f, "frontend"));
        employees.add(new Designer("Noah", "Harris", 7.8f, "design"));
        employees.add(new Product("Emma", "Clark", 3.6f, "product"));
        employees.add(new Manager("James", "Lewis", 11.2f, "account"));
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

        Corporation corp = new Corporation("apple", employees);
        setCorporation(corp);
        setCurrentUser(employees.getFirst());
    }

    public static void setCorporation(Corporation corp) {
        corporation = corp;
        surveyManager = corp.surveyManager;
    }
    public static void setCurrentUser(Employee employee) {
        currentUser = employee;
    }
    public static void setCurrentUser(String fullName) {
        for (Employee employee: corporation.getEmployees()) {
            if (employee.compareEmployee(fullName)) {
                currentUser = employee;
                return;
            }
        }
    }

    public static Employee getCurrentUser() {
        return currentUser;
    }
    public static Corporation getCorporation() {
        return corporation;
    }
    public static SurveyManager getSurveyManager() {
        return surveyManager;
    }
}
