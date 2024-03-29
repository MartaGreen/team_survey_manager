package survey;

import account.User;
import employees.Employee;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import main.Main;

import java.util.ArrayList;

public class SurveyManager {
    private ArrayList<Survey> surveysArr = new ArrayList<>();;
    ObservableList<Survey> surveys = FXCollections.observableList(surveysArr);

    public SurveyManager() {
        surveys.addListener((ListChangeListener<? super survey.Survey>) change -> Main.getCurrentUser().update());
    }

    public ArrayList<Survey> getEmployeeSurveys(User user) {
        if (surveys == null) return null;

        ArrayList<Survey> employeeSurveys = new ArrayList<>();

        for (Survey survey: surveys) {
            if (survey.containEmployee(user)) employeeSurveys.add(survey);
        }

        return employeeSurveys;
    }

    public ArrayList<Survey> getPersonalSurveys(User user) {
        if (surveys == null) return null;

        ArrayList<Survey> personalSurveys = new ArrayList<>();
        for (Survey survey: surveys) {
            if (survey.checkOwner(user)) {
                personalSurveys.add(survey);
            }
        }

        return personalSurveys;
    }

    public void createNewSurvey(String name, ObservableList<String> teams, User owner) {
        System.out.print(teams);
        ArrayList<Employee> participantsArr = Main.getCorporation().findEmployee(teams);
        Survey newSurvey = new Survey(name, participantsArr, owner);
        surveys.add(newSurvey);
    }
}
