package main;

import account.User;
import corporation.Corporation;
import employees.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static pages.Router.loadInitialPage;

public class Main extends Application {
    private static Corporation corporation;
    private static User currentUser;
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
        employees.add(new Manager("Sophia", "Lopez", 9.4f, "account"));

        Corporation corp = new Corporation("apple", employees);
        setCorporation(corp);
        setCurrentUser(employees.getFirst());
    }

    public static void setCorporation(Corporation corp) {
        corporation = corp;
    }

    public static void setCurrentUser(Employee employee) {
        currentUser = new User(employee, corporation.surveyManager);
    }
    public static void setCurrentUser(String fullName) {
        for (Employee employee: corporation.getEmployees()) {
            if (employee.compareEmployee(fullName)) {
                currentUser = new User(employee, corporation.surveyManager);
                System.out.println("USer was updated to " + currentUser.getName());
                return;
            };
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }
    public static Corporation getCorporation() {
        return corporation;
    }
}
