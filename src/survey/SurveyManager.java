package survey;

import account.User;
import employees.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import main.Main;

import java.util.ArrayList;

public class SurveyManager {
    private final ArrayList<Survey> surveysArr = new ArrayList<>();
    ObservableList<Survey> surveys = FXCollections.observableList(surveysArr);

    public SurveyManager() {
        surveys.addListener((ListChangeListener<? super survey.Survey>) change -> {
            System.out.print("Surveys was changed\nUpdate user\nUpdate current survey\n");
            Main.getCurrentUser().update();
            loadSurvey(Main.currentSurvey.getSurveyId());
        });
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

    public void createNewSurvey(String name, ObservableList<String> teams, ArrayList<String> options, boolean isMultipleChoice) {
        User owner = Main.getCurrentUser();
        ArrayList<Employee> participantsArr = Main.getCorporation().findEmployee(teams);
        Survey newSurvey;
        if (isMultipleChoice) newSurvey = new MultipleChoiceSurvey(name, participantsArr, options, owner);
        else newSurvey = new SingleChoiceSurvey(name, participantsArr, options, owner);
        surveys.add(newSurvey);
    }

    public void loadSurvey(String id) {
        surveys.forEach(survey -> {
            if (survey.getSurveyId().equals(id)) {
                Main.currentSurvey = survey;
            }
        });
    }

    public void voteInSurvey(String optionId) {
        User user = Main.getCurrentUser();
        Main.currentSurvey.vote(user, optionId);
    }
    public void voteInSurvey(ArrayList<String> ids) {
        User user = Main.getCurrentUser();
        ((MultipleChoiceSurvey)Main.currentSurvey).vote(user, ids);
    }
}
